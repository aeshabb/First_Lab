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
import reader.ReadConsoleCommand;

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

    public void runCommands() throws IOException {
        fillLists();
        initStorages();
        startInvoker();
        parseInputLine();
    }

    public void fillLists() throws IOException {
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

    public void startInvoker() throws IOException {
        Receiver receiver = new Receiver(enrolleeStorage, directionStorage);
        Map<String, Command> commandMap = fillCommandMap(receiver);
        invoker = new Invoker(commandMap);
    }

    public static Map<String, Command> fillCommandMap(Receiver receiver) {
        Command change = new ChangeDirNameCommand(receiver);
        Command update = new UpdateScoreCommand(receiver);
        Command compare = new CompareScoreCommand(receiver);
        Command delete = new DeleteByIdCommand(receiver);
        Command showOriginals = new ShowEnrolleesWithOriginalsCommand(receiver);
        Command quit = new QuitCommand(receiver);
        Command help = new HelpCommand(receiver);
        Command showEnrollees = new ShowAllEnrollees(receiver);
        Command showDirections = new ShowAllDirections(receiver);
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("changeDirName", change);
        commandMap.put("delete", delete);
        commandMap.put("updateScore", update);
        commandMap.put("compare", compare);
        commandMap.put("showOriginals", showOriginals);
        commandMap.put("showAllEnrollees", showEnrollees);
        commandMap.put("showAllDirections", showDirections);
        commandMap.put("quit", quit);
        commandMap.put("help", help);
        return commandMap;
    }

    public void parseInputLine() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ReadConsoleCommand readConsoleCommand = new ReadConsoleCommand();
        String line;
        while (!((line = readConsoleCommand.readConsoleString(br)).equals("quit"))) {
            invoker.executeCommand(line);
        }
    }
}
