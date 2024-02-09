package org.itmo.command;

import org.itmo.database.RouteStorage;
import org.itmo.entity.LocationFrom;
import org.itmo.entity.Route;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public class Receiver {
    private final RouteStorage routeStorage;

    public Receiver(RouteStorage routeStorage) {
        this.routeStorage = routeStorage;
    }

    protected int getCollectionSize() {
        return routeStorage.getRouteSet().size();
    }

    protected Class getCollectionClass() {
        return routeStorage.getRouteSet().getClass();
    }

    protected LocalDateTime getInitTime() {
        return routeStorage.getInitTime();
    }

    protected TreeSet<Route> getCollection() {
        return routeStorage.getRouteSet();
    }

    protected void addRouteToTreeSet(Route route) {
        routeStorage.createRoute(route);
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

    protected void updateRouteToTreeSet(Route route) {
        if (routeStorage.getDeletedRoute().contains(route.getId())) {
            routeStorage.createRoute(route);
        } else {
            routeStorage.deleteRoute(findRouteById(route.getId()));
            routeStorage.createRoute(route);
        }

    }

    protected Route findRouteById(int id) {
        for (Route route : routeStorage.getRouteSet()) {
            if (route.getId() == id) {
                return route;
            }
        }
        return null;
    }

    protected void deleteRouteById(int id) {
        routeStorage.deleteRoute(findRouteById(id));
    }

    protected void clearRouteTreeSet() {
        TreeSet<Route> set = routeStorage.getRouteSet();
        set.clear();
    }

}