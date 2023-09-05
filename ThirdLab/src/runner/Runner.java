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

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Runner {
    private EnrolleeStorage enrolleeStorage;
    private DirectionStorage directionStorage;

    public void fillStorages() throws IOException {
        FileDataReader fileDataReader = new FileDataReader();
        List<String> stringEnrollee = fileDataReader.readFromDatabase("resources/Abitura.txt");
        List<String> stringDirections = fileDataReader.readFromDatabase("resources/Directions.txt");

        EnrolleeDataHandler enrolleeDataHandler = new EnrolleeDataHandlerImpl();
        DirectionsDataHandler directionsDataHandler = new DirectionsDataHandlerImpl();

        List<Enrollee> enrolleeList = enrolleeDataHandler.handle(stringEnrollee);
        List<Direction> directionList = directionsDataHandler.handle(stringDirections);

        enrolleeStorage = new EnrolleeStorage(enrolleeList);
        directionStorage = new DirectionStorage(directionList);
        System.out.println("Before: " + enrolleeStorage.getEnrolleeList());
    }

    public void runCommands() throws IOException {
        Receiver receiver = new Receiver(enrolleeStorage, directionStorage);
        Map<String, Command> commandMap = fillInvoker(receiver);
        Invoker invoker = new Invoker(commandMap);
        ReadConsoleCommand readConsoleCommand = new ReadConsoleCommand();
        invoker.executeCommand(readConsoleCommand.readConsoleString());
        System.out.println("After: " + enrolleeStorage.getEnrolleeList());
    }
    public static Map<String, Command> fillInvoker(Receiver receiver) {
        Command change = new ChangeDirNameCommand(receiver);
        Command update = new UpdateCommand(receiver);
        Command compare = new CompareScoreCommand(receiver);
        Command delete = new DeleteByIdCommand(receiver);
        Command show = new ShowEnrolleesCommand(receiver);
        Map<String, Command> commandMap = new HashMap<>();
        commandMap.put("change", change);
        commandMap.put("delete", delete);
        commandMap.put("update", update);
        commandMap.put("compare", compare);
        commandMap.put("show", show);
        return commandMap;
    }

}
