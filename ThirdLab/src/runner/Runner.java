package runner;

import command.*;
import controller.Invoker;
import database.DirectionStorage;
import database.EnrolleeStorage;
import entity.Direction;
import entity.Enrollee;
import handlers.DirectionsDataHandler;
import handlers.DirectionsDataHandlerImpl;
import handlers.EnrolleeDataHandler;
import handlers.EnrolleeDataHandlerImpl;
import logger.Logger;
import reader.FileDataReader;
import reader.InputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    private List<Enrollee> enrolleeList;
    private List<Direction> directionList;
    private EnrolleeStorage enrolleeStorage;
    private DirectionStorage directionStorage;
    private Invoker invoker;

    public void runMethods() throws IOException {
        fillEnrAndDirLists();
        initStorages();
        initInvoker();
        runCommands();
    }

    private void fillEnrAndDirLists() throws IOException {
        FileDataReader fileDataReader = new FileDataReader();
        List<String> stringEnrollee = fileDataReader.readFromDatabase("resources/Abitura.txt");
        List<String> stringDirections = fileDataReader.readFromDatabase("resources/Directions.txt");

        EnrolleeDataHandler enrolleeDataHandler = new EnrolleeDataHandlerImpl();
        DirectionsDataHandler directionsDataHandler = new DirectionsDataHandlerImpl();

        enrolleeList = enrolleeDataHandler.handle(stringEnrollee);
        directionList = directionsDataHandler.handle(stringDirections);
    }

    private void initStorages() {
        enrolleeStorage = new EnrolleeStorage(enrolleeList);
        directionStorage = new DirectionStorage(directionList);
    }

    private void initInvoker() throws IOException {
        Receiver receiver = new Receiver(enrolleeStorage, directionStorage);
        Logger logger = new Logger();
        Map<String, Command> commandMap = fillCommandMap(receiver, logger);
        invoker = new Invoker(commandMap);
    }

    private Map<String, Command> fillCommandMap(Receiver receiver, Logger logger) {
        Command changeDirName = new ChangeDirNameCommand(receiver, "Изменить название направления: \"changeDirName + (старое название) + > + (новое название)\"", logger);
        Command update = new UpdateScoreCommand(receiver, "Изменить балл ЕГЭ абитуриента: \"updateScore + (id абитуриента) + (название предмета) + (балл предмета)\"", logger);
        Command compare = new CompareScoreCommand(receiver, "Сравнить баллы двух абитуриентов: \"compare + (id первого) + (id второго) + (название предмета)\"", logger);
        Command delete = new DeleteByIdCommand(receiver, "Удалить абитуриента по id: \"delete + (id абитуриента)\"", logger);
        Command showOriginals = new ShowEnrolleesWithOriginalsCommand(receiver, "Вывести количество абитуриентов с оригиналами на направлении: \"showOriginals + (название направления)\"", logger);
        Command showEnrollees = new ShowAllEnrolleesCommand(receiver, "Вывести список абитуриентов: \"showAllEnrollees\"", logger);
        Command showDirections = new ShowAllDirectionsCommand(receiver, "Вывести список направлений: \"showAllDirections\"", logger);

        Map<String, Command> commandMap = new HashMap<>();

        commandMap.put("changeDirName", changeDirName);
        commandMap.put("delete", delete);
        commandMap.put("updateScore", update);
        commandMap.put("compare", compare);
        commandMap.put("showOriginals", showOriginals);
        commandMap.put("showAllEnrollees", showEnrollees);
        commandMap.put("showAllDirections", showDirections);

        Command help = new HelpCommand(receiver, "Вывести список команд: \"help\"", logger, createDescriptionMap(commandMap));
        commandMap.put("help", help);

        return commandMap;
    }

    private void runCommands() throws IOException {
        invoker.executeCommand("help");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        InputStream inputStream = new InputStream(br);
        String line;
        while (!(line = inputStream.readConsoleString()).equals("quit")) {
            invoker.executeCommand(line);
        }
        inputStream.stopStream();
    }

    private List<String> createDescriptionMap(Map<String, Command> commandMap) {
        List<String> descriptionList = new ArrayList<>();
        for (String key : commandMap.keySet()) {
            descriptionList.add(commandMap.get(key).getDescription());
        }
        return descriptionList;
    }
}
