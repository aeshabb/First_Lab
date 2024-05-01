package org.itmo.server;

import org.itmo.server.util.ConnectionManager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        String sql = """
                INSERT INTO route VALUES (nextval('route_seq'), 'shax')
                """;
        try (Connection connection = ConnectionManager.open();
        var statement = connection.createStatement()){
            System.out.println(statement.execute(sql));
        }

//        System.setProperty("CSVPATH", "C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\SixthLab\\Server\\src\\main\\resources\\db.csv");
//        ParseCSV.readCSV(System.getProperty("CSVPATH"));
//        Runner runner = new Runner(new InfoPrinter(new PrintStream(System.out)));
//        runner.start();

    }
}