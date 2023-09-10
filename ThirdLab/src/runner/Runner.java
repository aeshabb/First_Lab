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
import reader.FileDataReader;
import reader.InputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        Map<String, Command> commandMap = fillCommandMap(receiver);
        invoker = new Invoker(commandMap);
    }

    private static Map<String, Command> fillCommandMap(Receiver receiver) {
        Command changeDirName = new ChangeDirNameCommand(receiver);
        Command update = new UpdateScoreCommand(receiver);
        Command compare = new CompareScoreCommand(receiver);
        Command delete = new DeleteByIdCommand(receiver);
        Command showOriginals = new ShowEnrolleesWithOriginalsCommand(receiver);
        Command help = new HelpCommand(receiver);
        Command showEnrollees = new ShowAllEnrollees(receiver);
        Command showDirections = new ShowAllDirections(receiver);
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("changeDirName", changeDirName);
        commandMap.put("delete", delete);
        commandMap.put("updateScore", update);
        commandMap.put("compare", compare);
        commandMap.put("showOriginals", showOriginals);
        commandMap.put("showAllEnrollees", showEnrollees);
        commandMap.put("showAllDirections", showDirections);
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
}
