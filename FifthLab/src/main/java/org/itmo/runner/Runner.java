package org.itmo.runner;

import com.opencsv.exceptions.CsvException;
import org.itmo.command.*;
import org.itmo.controller.Invoker;
import org.itmo.database.RouteStorage;
import org.itmo.entity.Coordinates;
import org.itmo.entity.LocationFrom;
import org.itmo.entity.LocationTo;
import org.itmo.entity.Route;
import org.itmo.output.ConsolePrinter;
import org.itmo.reader.ReaderCSV;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;


public class Runner {
    private Invoker invoker;
    private Set<Route> routeSet;
    private RouteStorage routeStorage;
    private Map<String, List<String>> stringListMap;
    private ConsolePrinter consolePrinter;
    private int size;
    private LocalDateTime initTimeSet;

    public void runMethods() throws IOException, CsvException {
        readCSVFile();
        fillRouteSet(stringListMap, size);
        initStorage();
        initInvoker();
        runCommands();
    }

    private void readCSVFile() throws IOException, CsvException {
        ReaderCSV readerCSV = new ReaderCSV();
        stringListMap = readerCSV.readCSV(System.getProperty("pathToCSV"));
        size = stringListMap.get("name").size();
    }


    private void fillRouteSet(Map<String, List<String>> stringListMap, int size) {
        Set<Route> routeSet = new TreeSet<>();
        for (int i = 0; i < size; i++) {
            String name = stringListMap.get("name").get(i);
            Coordinates coordinates = new Coordinates(Double.parseDouble(stringListMap.get("xC").get(i)), Integer.parseInt(stringListMap.get("yC").get(i)));
            LocationFrom locationFrom = new LocationFrom(Long.parseLong(stringListMap.get("xLF").get(i)), Double.parseDouble(stringListMap.get("yLF").get(i)), Float.parseFloat(stringListMap.get("zLF").get(i)), stringListMap.get("nameLF").get(i));
            LocalDateTime date = LocalDateTime.now();
            LocationTo locationTo = new LocationTo(Double.parseDouble(stringListMap.get("xLT").get(i)), Long.parseLong(stringListMap.get("yLT").get(i)), Integer.parseInt(stringListMap.get("zLT").get(i)));
            Integer distance = Integer.parseInt(stringListMap.get("distance").get(i));
            routeSet.add(new Route(i + 1, name, coordinates, date, locationFrom, locationTo, distance));

        }
        this.routeSet = routeSet;
        this.initTimeSet = LocalDateTime.now();


    }

    private void initStorage() {
        this.routeStorage = new RouteStorage((TreeSet<Route>) routeSet, initTimeSet);

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
        Command saveCommand = new SaveCommand(receiver, "Сохранить коллекцию в файл \"save\"", printer);
        Command addMinCommand = new AddMinCommand(receiver, "Добавить элемент в коллекцию, если он минимальный: \"add_if_min\"", printer);
        Command removeLowerCommand = new RemoveLowerCommand(receiver, "Удалить все команды, меньше данной: \"remove_lower\"", printer);
        Command historyCommand = new HistoryCommand(receiver, "Вывести историю: \"history\"", printer);
        Command minByFromCommand = new MinByFromCommand(receiver, "Вывести Route с минимальным полем LocationFrom: \"min_by_from\"", printer);
        Command countRoutesLessDistanceCommand = new CountRoutesLessDistanceCommand(receiver, "Вывести количество Route с меньшим полем distance: \"count_less_than_distance\"", printer);
        Command filterRoutesLessDistanceCommand = new FilterRoutesLessDistance(receiver, "Вывести Route с меньшим полем distance: \"filter_less_than_distance\"", printer);


        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("info", infoCommand);
        commandMap.put("show", showCommand);
        commandMap.put("add", addCommand);
        commandMap.put("update", updateCommand);
        commandMap.put("remove_by_id", removeByIdCommand);
        commandMap.put("clear", clearCommand);
        commandMap.put("save", saveCommand);
        commandMap.put("add_if_min",addMinCommand);
        commandMap.put("remove_lower", removeLowerCommand);
        commandMap.put("history", historyCommand);
        commandMap.put("min_by_from", minByFromCommand);
        commandMap.put("count_less_than_distance", countRoutesLessDistanceCommand);
        commandMap.put("filter_less_than_distance", filterRoutesLessDistanceCommand);

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
