package org.itmo.server.collection;


import org.itmo.entity.Route;
import org.itmo.parser.ParseCSV;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Receiver {
    private final RouteStorage routeStorage;

    private static final Map<String, BiPredicate<Route, String>> PREDICATE_MAP = new HashMap<>();

     static {
        PREDICATE_MAP.put("name", (route, value) -> route.getName().contains(value));
        PREDICATE_MAP.put("distance", (route, value) -> {
            Integer distance = route.getDistance();
            Integer filterValue = Integer.valueOf(value);
            return distance != null && distance > filterValue;
        });
        // ... добавить другие предикаты ...
    }


    public Receiver(RouteStorage routeStorage) {
        this.routeStorage = routeStorage;
    }

    public String getFilteredRoutes(Map<String, String> userFilters) {
        Stream<Route> routeStream = routeStorage.getRouteSet().stream();

        // Применение каждого фильтра в цикле
        for (Map.Entry<String, String> entry : userFilters.entrySet()) {
            BiPredicate<Route, String> predicate = PREDICATE_MAP.get(entry.getKey());
            String filterValue = entry.getValue();

            routeStream = routeStream.filter(route -> predicate.test(route, filterValue));
        }
        List<Route> routes = routeStream.toList();

        StringBuilder stringBuilder = new StringBuilder();
        int cnt = 1;
        for (Route route : routes) {
            stringBuilder.append(cnt).append(". ").append(route.toString());
            stringBuilder.append("\n");
            cnt++;
        }
        return stringBuilder.toString();
    }



        public void add(Route route) {
        routeStorage.addRoute(route);
    }

    public Route getRouteById(int id) {
        Route result = routeStorage.getRouteSet().stream().filter(route -> route.getId() == id).toList().get(0);
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
        return routeStorage.getRouteSet().remove(getRouteById(id));
    }

    public void fillStorage(RouteStorage routeStorage) {
        this.routeStorage.clear();
        for (Route route : routeStorage.getRouteSet()) {
            this.routeStorage.addRoute(route);
        }

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
    public Route minByFrom() {
        Route result = routeStorage.getRouteSet()
                .stream()
                .min(Comparator.comparing(route -> route.getLocationFrom().getxLF())).get();
        return result;
    }


}