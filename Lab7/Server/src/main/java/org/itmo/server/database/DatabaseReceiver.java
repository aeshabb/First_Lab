package org.itmo.server.database;

import lombok.SneakyThrows;
import org.itmo.entity.Coordinates;
import org.itmo.entity.LocationFrom;
import org.itmo.entity.LocationTo;
import org.itmo.entity.Route;
import org.itmo.server.collection.RouteStorage;
import org.itmo.server.hasher.SHA1Hasher;
import org.itmo.server.util.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.TreeSet;

public class DatabaseReceiver {

    public DatabaseReceiver() {
        createStartTables();
    }

    public int findUserByNameAndPassword(String username, String password) {
        int user_id = -1;
        String sql = """
                SELECT * FROM users;
                """;
        try (var statement = ConnectionManager.get().createStatement()) {
            var executeResult = statement.executeQuery(sql);
            while (executeResult.next()) {
                if (executeResult.getString("name").equals(username)) {
                    if (executeResult.getString("password").equals(SHA1Hasher.toSHA1(password))) {
                        user_id = executeResult.getInt("id");
                        return user_id;
                    }
                }
            }
            return user_id;
        } catch (SQLException e) {
            System.out.println("Данные не корректны");
            return user_id;
        }
    }

    public boolean loginUser(String username, String password) {
        return findUserByNameAndPassword(username, password) != -1;
    }

    public boolean registerUser(String username, String password) {
        if (findUserByNameAndPassword(username, password) == -1) {
            String sql1 = """
                        INSERT INTO users(name, password)
                        VALUES (?, ?);
                        """;
            try (Connection connection = ConnectionManager.get();
                 var statement1 = connection.prepareStatement(sql1)) {
                statement1.setString(1, username);
                statement1.setString(2, SHA1Hasher.toSHA1(password));
                statement1.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Данные не корректны");
                return false;
            }
        }
        return false;
    }

    public void createStartTables() {
        String sql = """
                DROP TABLE coordinates, location_from, location_to, users, route CASCADE;
                DROP SEQUENCE route_sequence CASCADE;
                CREATE TABLE IF NOT EXISTS coordinates (
                    id SERIAL PRIMARY KEY,
                    x DECIMAL NOT NULL CHECK (x <= 660),
                    y INT CHECK (y <= 909)
                );
                CREATE TABLE IF NOT EXISTS location_from (
                    id SERIAL PRIMARY KEY,
                    x BIGINT,
                    y DECIMAL,
                    z DECIMAL,
                    name TEXT
                );
                CREATE TABLE IF NOT EXISTS location_to (
                    id SERIAL PRIMARY KEY,
                    x DECIMAL,
                    y BIGINT,
                    z INT
                );
                CREATE TABLE IF NOT EXISTS users (
                    id SERIAL PRIMARY KEY,
                    name TEXT NOT NULL,
                    password TEXT
                );
                CREATE SEQUENCE IF NOT EXISTS route_sequence START WITH 1 INCREMENT BY 1;
                CREATE TABLE IF NOT EXISTS route (
                    id INT PRIMARY KEY,
                    name TEXT NOT NULL,
                    coordinates_id INT NOT NULL,
                    local_date_time TIMESTAMP NOT NULL,
                    location_from_id INT,
                    location_to_id INT NOT NULL,
                    distance INT CHECK (distance > 1) UNIQUE,
                    user_id INT NOT NULL,
                    CONSTRAINT fk_coordinates FOREIGN KEY
                    (coordinates_id) REFERENCES coordinates(id), CONSTRAINT fk_location_from
                    FOREIGN KEY (location_from_id) REFERENCES location_from(id), CONSTRAINT fk_location_to
                    FOREIGN KEY (location_to_id) REFERENCES location_to(id), CONSTRAINT fk_users
                    FOREIGN KEY (user_id) REFERENCES users(id)
                )
                """;
        try (var statement = ConnectionManager.get().createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public RouteStorage fillRouteStorage() {
        TreeSet<Route> treeSet = new TreeSet<>();
        String sql = """
                SELECT route.id, route.name, c.x, c.y, route.local_date_time, route.location_from_id, lf.x, lf.y, lf.z, lf.name, lt.x, lt.y, lt.z, route.distance
                FROM route
                         LEFT JOIN public.coordinates c on route.coordinates_id = c.id
                         LEFT JOIN public.location_from lf on route.location_from_id = lf.id
                         LEFT JOIN public.location_to lt on route.location_to_id = lt.id;
                """;
        try (var statement = ConnectionManager.get().createStatement()) {
            var resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                LocationFrom locationFrom = null;
                Coordinates coordinates = new Coordinates(
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                );
                if (resultSet.getObject(6) != null) {
                    locationFrom = new LocationFrom(
                            resultSet.getLong(7),
                            resultSet.getDouble(8),
                            resultSet.getFloat(9),
                            resultSet.getString(10)
                    );
                }
                LocationTo locationTo = new LocationTo(
                        resultSet.getDouble(11),
                        resultSet.getLong(12),
                        resultSet.getInt(13)
                );
                Route route = new Route(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        coordinates,
                        resultSet.getTimestamp("local_date_time").toLocalDateTime(),
                        locationFrom,
                        locationTo,
                        resultSet.getInt(14)
                );
                treeSet.add(route);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось заполнить коллекцию. Проверьте БД на наличие верных таблиц.");
        }
        return new RouteStorage(treeSet, LocalDateTime.now());
    }

    public int addRouteToDB(Route route, String username, String password) {
        Connection connection = null;
        int id = -1;
        route.setCreationDate(LocalDateTime.now());
        if (route.getLocationFrom() == null) {
            String sql1 = """
                        INSERT INTO coordinates (x, y)
                        VALUES (?, ?);
                    """;
            String sql2 = """
                        INSERT INTO location_to (x, y, z)
                        VALUES (?, ?, ?);
                    """;
            String sql3 = """
                        INSERT INTO route (id, name, coordinates_id, local_date_time, location_from_id, location_to_id, distance, user_id)
                        VALUES (nextval('route_sequence'), ?, currval('coordinates_id_seq'), ?, null, currval('location_to_id_seq'), ?, ?)
                    """;
            Coordinates coordinates = route.getCoordinates();
            LocationTo locationTo = route.getLocationTo();
            PreparedStatement preparedStatement1 = null;
            PreparedStatement preparedStatement2 = null;
            PreparedStatement preparedStatement3 = null;

            try {
                connection = ConnectionManager.get();

                preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement3 = connection.prepareStatement(sql3);
                connection.setAutoCommit(false);

                preparedStatement1.setDouble(1, coordinates.getxC());
                preparedStatement1.setInt(2, coordinates.getyC());
                preparedStatement1.executeUpdate();

                preparedStatement2.setDouble(1, locationTo.getxLT());
                preparedStatement2.setLong(2, locationTo.getyLT());
                preparedStatement2.setInt(3, locationTo.getzLT());
                preparedStatement2.executeUpdate();

                preparedStatement3.setString(1, route.getName());
                preparedStatement3.setTimestamp(2, Timestamp.valueOf(route.getCreationDate()));
                preparedStatement3.setInt(3, route.getDistance());
                preparedStatement3.setInt(4, id = findUserByNameAndPassword(username, password));
                preparedStatement3.executeUpdate();

                connection.commit();
                preparedStatement1.close();
                preparedStatement2.close();
                preparedStatement3.close();
                connection.close();
            } catch (Exception e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        System.out.println("Ошибка при добавлении объекта в БД");
                        return -1;
                    }
                }
                System.out.println("Ошибка при добавлении объекта в БД");
                return -1;
            }


        } else {
            String sql1 = """
                        INSERT INTO coordinates (x, y)
                        VALUES (?, ?);
                    """;
            String sql4 = """
                        INSERT INTO location_from (x, y, z, name)
                        VALUES (?, ?, ?, ?);
                    """;
            String sql2 = """
                        INSERT INTO location_to (x, y, z)
                        VALUES (?, ?, ?);
                    """;
            String sql3 = """
                        INSERT INTO route (id, name, coordinates_id, local_date_time, location_from_id, location_to_id, distance, user_id)
                        VALUES (nextval('route_sequence'), ?, currval('coordinates_id_seq'), ?, currval('location_from_id_seq'), currval('location_to_id_seq'), ?, ?)
                    """;
            Coordinates coordinates = route.getCoordinates();
            LocationFrom locationFrom = route.getLocationFrom();
            LocationTo locationTo = route.getLocationTo();

            PreparedStatement preparedStatement1 = null;
            PreparedStatement preparedStatement2 = null;
            PreparedStatement preparedStatement3 = null;
            PreparedStatement preparedStatement4 = null;
            try  {
                connection = ConnectionManager.get();
                preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement3 = connection.prepareStatement(sql3);
                preparedStatement4 = connection.prepareStatement(sql4);
                connection.setAutoCommit(false);

                preparedStatement1.setDouble(1, coordinates.getxC());
                preparedStatement1.setInt(2, coordinates.getyC());
                preparedStatement1.executeUpdate();

                preparedStatement4.setLong(1, locationFrom.getxLF());
                preparedStatement4.setDouble(2, locationFrom.getyLF());
                preparedStatement4.setFloat(3, locationFrom.getzLF());
                preparedStatement4.setString(4, locationFrom.getNameLF());
                preparedStatement4.executeUpdate();

                preparedStatement2.setDouble(1, locationTo.getxLT());
                preparedStatement2.setLong(2, locationTo.getyLT());
                preparedStatement2.setInt(3, locationTo.getzLT());
                preparedStatement2.executeUpdate();

                preparedStatement3.setString(1, route.getName());
                preparedStatement3.setTimestamp(2, Timestamp.valueOf(route.getCreationDate()));
                preparedStatement3.setInt(3, route.getDistance());
                preparedStatement3.setInt(4, id = findUserByNameAndPassword(username, password));
                preparedStatement3.executeUpdate();

                connection.commit();
                preparedStatement1.close();
                preparedStatement2.close();
                preparedStatement3.close();
                preparedStatement4.close();
                connection.close();
            } catch (SQLException e) {
                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        System.out.println("Ошибка при добавлении объекта в БД");
                        return -1;
                    }
                }
                System.out.println("Ошибка при добавлении объекта в БД");
                return -1;
            }
        }
        return id;
    }

}
