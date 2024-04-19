package org.itmo.server.collection;


import org.itmo.entity.Route;
import org.itmo.server.command.RouteParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Receiver {
    private final RouteStorage routeStorage;

    public Receiver(RouteStorage routeStorage) {
        this.routeStorage = routeStorage;
    }

//    public boolean addIfMin(Route route) {
//
//        if (routeStorage.getRouteSet().isEmpty() || route.compareTo(routeStorage.getRouteSet().iterator().next()) < 0) {
//            add(route);
//            return true;
//        }
//        return false;
//    }
//
//    public void add(Route route) {
//        RouteParser routeParser = new RouteParser(receiver, printer, parameters);
//        receiver.addRouteToTreeSet(routeParser.parseRouteFromConsole());
//
//        route.setId(routeStorage.getNewId());
//
//        routeStorage.add(route);
//    }
//
//    public route getLabById(int id) {
//        List<route> result = routeStorage.getRouteSet().stream().filter(lab -> lab.getId() == id).toList();
//        return result.isEmpty() ? null : result.get(0);
//    }
//
//    public boolean update(int id, route newLab) {
//
//        route oldLab = getLabById(id);
//        if (oldLab == null)
//            return false;
//
//        newLab.setId(id);
//        newLab.setCreationDate(LocalDateTime.now().toLocalDate());
//
//        routeStorage.delete(oldLab);
//        routeStorage.add(newLab);
//        return true;
//    }
//
//    public boolean removeById(int id) {
//        route lab = getLabById(id);
//        if (lab == null)
//            return false;
//        routeStorage.delete(lab);
//        return true;
//    }

    public void clear() {
        routeStorage.clear();
    }

//    public boolean executeScript(PrintWriter infoPrinter, File file) throws FileNotFoundException {
//        List<File> recursion = historyCall.stream().filter(previous -> previous.getName().equals(file.getName())).toList();
//        if (!recursion.isEmpty()){
//            infoPrinter.println("Обнаружена рекурсия " + recursion);
//            return false;
//        }
//        PrintWriter scriptPrinter = new PrintWriter("/dev/null");
//        BufferedReader scriptReader = new BufferedReader(new FileReader(file));
//        Runner scriptRunner = new Runner(infoPrinter, file, scriptPrinter, scriptReader);
//        scriptRunner.run(routeStorage.getRouteSet(), fileName);
//        historyCall.remove(historyCall.size() - 1);
//        return true;
//    }

//    public List<Route> getGroupCountingByCreationDate() {
//        List<LocalDate> allDates = new ArrayList<>();
//        List<route> result = new ArrayList<>();
//
//        for (route lab : routeStorage.getRouteSet()) {
//            allDates.add(lab.getCreationDate());
//        }
//        for (LocalDate date : allDates)
//            result = Stream.concat(result.stream(), routeStorage.getRouteSet().stream().filter(lab -> lab.getCreationDate() == date)).toList();
//        return result;
//    }
//
//    public String printGroupCountingByCreationDate() {
//        List<route> labs = getGroupCountingByCreationDate();
//        StringBuilder sb = new StringBuilder();
//        for (route lab : labs) {
//            sb.append("\n=================\n").append(lab);
//        }
//        return sb.toString();
//    }

//    public boolean saveCollection(String filePath) {
//        try(FileWriter writer = new FileWriter(filePath)) {
//            CsvHandler.writeRows(writer, new ArrayList<>(routeStorage.getRouteSet()));
//        } catch (IOException e) {
//            return false;
//        } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
//            throw new RuntimeException(e);
//        }
//        return true; // успешно записали
//    }


//    public String show() {
//        StringBuilder sb = new StringBuilder();
//        for (route lab : routeStorage.getRouteSet()) {
//            sb.append("\n===============\n").append(lab);
//        }
//        return sb.toString();
//    }

    public String info() {
        return routeStorage.getInfo();
    }

//    public String printUniqueDifficulty() {
//        // вывести уникальные значения поля difficulty всех элементов в коллекции
//        List<Difficulty> result = new ArrayList<>();
//        for (Difficulty cur : Difficulty.values())
//            if (!routeStorage.getRouteSet().stream().filter(route -> route.getDifficulty() == cur).toList().isEmpty()) {
//                result.add(cur);
//            }
//        StringBuilder sb = new StringBuilder();
//        for (var difficulty : result) {
//            sb.append(difficulty).append("\n");
//        }
//        return sb.toString();
//    }
//
//    public List<Person> getAuthors() {
//        List<Person> result = new ArrayList<>();
//        for (route lab : routeStorage.getRouteSet())
//            if (lab.getAuthor() != null)
//                result.add(lab.getAuthor());
//        Collections.sort(result);
//        return result;
//    }
//
//    public String printFieldAscendingAuthors() {
//        StringBuilder sb = new StringBuilder();
//        for (var man : getAuthors()) {
//            sb.append("\n============\n").append(man);
//        }
//        return sb.toString();
//    }
}