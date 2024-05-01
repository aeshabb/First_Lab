package org.itmo.server.collection;


import org.itmo.entity.Route;
import org.itmo.parser.ParseCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;


public class Receiver {
    private final RouteStorage routeStorage;

    public Receiver(RouteStorage routeStorage) {
        this.routeStorage = routeStorage;
    }

    protected int getFreeId() {
        List<Integer> deletedRoute = routeStorage.getDeletedRoute();
        int id;
        if (!deletedRoute.isEmpty()) {
            id = deletedRoute.get(0);
            deletedRoute.remove(0);
        } else {
            id = routeStorage.getRouteSet().size() + 1;
        }
        return id;
    }

    public boolean addIfMin(Route route) {
        if (routeStorage.getRouteSet().isEmpty() || route.compareTo(routeStorage.getRouteSet().iterator().next()) < 0) {
            add(route);
            return true;
        }
        return false;
    }

    public void add(Route route) {
        if (route.getId() == -1) {
            route.setId(getFreeId());
        }
        route.setCreationDate(LocalDateTime.now());
        routeStorage.addRoute(route);
    }

    public Route getRouteById(int id) {
        Route result = routeStorage.getRouteSet().stream().filter(route -> route.getId() == id).toList().get(0);
        return result;
    }
    public Route minByFrom() {
        Route result = routeStorage.getRouteSet()
                .stream()
                .min(Comparator.comparing(route -> route.getLocationFrom().getxLF())).get();
        return result;
    }

    public boolean update(int id, Route route) {

        Route oldRoute = getRouteById(id);
        if (oldRoute == null)
            return false;

        route.setId(id);
        route.setCreationDate(LocalDateTime.now());

        routeStorage.deleteRoute(route);
        routeStorage.addRoute(route);
        return true;
    }

    public boolean removeById(int id) {
        Route route = getRouteById(id);
        if (route == null)
            return false;
        routeStorage.deleteRoute(route);
        return true;
    }

    public void clear() {
        routeStorage.clear();
    }
    public void saveCollection() {
        try {
            TreeSet<Route> set = routeStorage.getRouteSet();
            FileWriter csvWriter = new FileWriter(System.getProperty("CSVPATH"));
            csvWriter.append(ParseCSV.getHeaders()[0]);
            for (int i = 1; i < ParseCSV.getHeaders().length; i++) {
                csvWriter.append(",");
                csvWriter.append(ParseCSV.getHeaders()[i]);

            }
            csvWriter.append("\n");
            for (Route route : set) {
                csvWriter.append(route.getName()).append(",");
                csvWriter.append(String.valueOf(route.getCoordinates().getxC())).append(",");
                csvWriter.append(String.valueOf(route.getCoordinates().getyC())).append(",");
                if(route.getLocationFrom() == null) {
                    csvWriter.append(null).append(",");
                    csvWriter.append(null).append(",");
                    csvWriter.append(null).append(",");
                    csvWriter.append(null).append(",");
                } else {
                    csvWriter.append(String.valueOf(route.getLocationFrom().getxLF())).append(",");
                    csvWriter.append(String.valueOf(route.getLocationFrom().getyLF())).append(",");
                    csvWriter.append(String.valueOf(route.getLocationFrom().getzLF())).append(",");
                    csvWriter.append(String.valueOf(route.getLocationFrom().getNameLF())).append(",");
                }
                csvWriter.append(String.valueOf(route.getLocationTo().getxLT())).append(",");
                csvWriter.append(String.valueOf(route.getLocationTo().getyLT())).append(",");
                csvWriter.append(String.valueOf(route.getLocationTo().getzLT())).append(",");
                csvWriter.append(String.valueOf(route.getDistance()));
                csvWriter.append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
        } catch (IOException e) {
            System.out.println("Возникла ошибка во время записи, проверьте данные.");
        }

    }

    public boolean removeLower(int distance) {
        List<Route> result = routeStorage.getRouteSet().stream().filter(route -> route.getDistance() < distance).toList();
        result.forEach(routeStorage.getRouteSet()::remove);
        return !result.isEmpty();
    }

    public String filterLessThanDistance(int distance) {
        List<Route> result = routeStorage.getRouteSet().stream().filter(route -> route.getDistance() < distance).toList();
        StringBuilder stringBuilder = new StringBuilder();
        int cnt = 1;
        for (Route route : result) {
            stringBuilder.append(cnt).append(". ").append(route.toString());
            stringBuilder.append("\n");
            cnt++;
        }
        return stringBuilder.toString();
    }

    public long countLessThanDistance(int distance) {
        long result = routeStorage.getRouteSet().stream().filter(route -> route.getDistance() < distance).count();
        return result;
    }




    public String info() {
        return routeStorage.getInfo();
    }
    public String help(List<String> commandList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String command : commandList) {
            stringBuilder.append(command);
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    public String show() {
        StringBuilder stringBuilder = new StringBuilder();
        int cnt = 1;
        for (Route route : routeStorage.getRouteSet()) {
            stringBuilder.append(cnt).append(". ").append(route.toString());
            stringBuilder.append("\n");
            cnt++;
        }
        return stringBuilder.toString();
    }

}