package org.itmo.runner;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;
import org.itmo.command.*;
import org.itmo.controller.Invoker;
import org.itmo.database.RouteStorage;
import org.itmo.entity.Coordinates;
import org.itmo.entity.LocationFrom;
import org.itmo.entity.LocationTo;
import org.itmo.entity.Route;
import org.itmo.output.ConsolePrinter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.*;

public class Runner {
    private Invoker invoker;
    private Map<String, List<String>> stringListMap;
    private Set<Route> routeSet;
    private int size;
    private RouteStorage routeStorage;
    private ConsolePrinter consolePrinter;

    public void runMethods() throws IOException, CsvException {
        readCSV();
        fillRouteSet(stringListMap, size);
        initStorage();
        initInvoker();
        runCommands();
    }

    private void readCSV() throws IOException, CsvException {
        CSVReader reader = new CSVReaderBuilder(new FileReader(System.getProperty("path"))).build();
        String[] line;
        int size = 0;

        Map<String, List<String>> stringListMap = new HashMap<>();
        String[] headers = reader.readNext();
        for (String cur : headers)
            stringListMap.put(cur, new ArrayList<String>());

        for (String[] row : reader.readAll()) {
            for (int i = 0; i < row.length; i++) {
                stringListMap.get(headers[i]).add(row[i]);

            }
            size++;
        }
        this.stringListMap = stringListMap;
        this.size = size;
    }

    private void fillRouteSet(Map<String, List<String>> stringListMap, int size) {
        Set<Route> routeSet = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            String name = stringListMap.get("StringR").get(i);
            Coordinates coordinates = new Coordinates(Double.parseDouble(stringListMap.get("DoubleC").get(i)), Integer.parseInt(stringListMap.get("intC").get(i)));
            LocationFrom locationFrom = new LocationFrom(Long.parseLong(stringListMap.get("longFrom").get(i)), Double.parseDouble(stringListMap.get("doubleFrom").get(i)), Float.parseFloat(stringListMap.get("floatFrom").get(i)), stringListMap.get("StringFrom").get(i));
            LocalDateTime date = LocalDateTime.now();
            LocationTo locationTo = new LocationTo(Double.parseDouble(stringListMap.get("DoubleTo").get(i)), Long.parseLong(stringListMap.get("LongTo").get(i)), Integer.parseInt(stringListMap.get("IntegerTo").get(i)));
            Integer distance = Integer.parseInt(stringListMap.get("IntegerR").get(i));
            routeSet.add(new Route(i + 1, name, coordinates, date, locationFrom, locationTo, distance));

        }
        this.routeSet = routeSet;


    }

    private void initStorage() {
        this.routeStorage = new RouteStorage((TreeSet<Route>) routeSet);

    }

    private void initInvoker() {
        Receiver receiver = new Receiver(routeStorage);
        ConsolePrinter printer = new ConsolePrinter();
        this.consolePrinter = printer;
        Map<String, Command> commandMap = fillCommandMap(receiver, printer);
        invoker = new Invoker(commandMap);
    }

    private Map<String, Command> fillCommandMap(Receiver receiver, ConsolePrinter printer) {

        Command infoCommand = new InfoCommand(receiver, "Информация о коллекции: \"info\"", printer);
        Command showCommand = new ShowCommand(receiver, "Вывести коллекцию: \"show\"", printer);
        Command addCommand = new AddCommand(receiver, "Добавить элемент в коллекцию: \"add\"", printer);
        Command updateCommand = new UpdateCommand(receiver, "Заменить элемент в колекции по id: \"update + (id)\"", printer);
        Command removeByIdCommand = new RemoveByIdCommand(receiver, "Удалить Route по id из коллекции: \"remove_by_id + (id)\"", printer);
        Command clearCommand = new ClearCommand(receiver, "Очистить коллекцию: \"clear\"", printer);

        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("info", infoCommand);
        commandMap.put("show", showCommand);
        commandMap.put("add", addCommand);
        commandMap.put("update", updateCommand);
        commandMap.put("remove_by_id", removeByIdCommand);
        commandMap.put("clear", clearCommand);

        Command help = new HelpCommand(receiver, "Вывести список команд: \"help\"", printer, createDescriptionMap(commandMap));
        commandMap.put("help", help);

        return commandMap;
    }

    private void runCommands() throws IOException {
        invoker.executeCommand("help");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (!(line = br.readLine()).equals("exit")) {
            if (!invoker.executeCommand(line)) {
                consolePrinter.printLine("Неправильная команда");
            }
        }
        br.close();
    }


    private List<String> createDescriptionMap(Map<String, Command> commandMap) {
        List<String> descriptionList = new ArrayList<>();
        for (String key : commandMap.keySet()) {
            descriptionList.add(commandMap.get(key).getDescription());
        }
        return descriptionList;
    }
}
