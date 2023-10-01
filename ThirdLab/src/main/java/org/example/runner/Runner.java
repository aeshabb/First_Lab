package org.example.runner;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Main;
import org.example.command.*;
import org.example.controller.Invoker;
import org.example.database.DirectionStorage;
import org.example.database.EnrolleeStorage;
import org.example.entity.JsonObject;
import org.example.entity.Direction;
import org.example.entity.Enrollee;
import org.example.output.Printer;
import org.example.reader.InputStream;

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
        ObjectMapper mapper = new ObjectMapper();
        java.io.InputStream inputStream = Main.class.getResourceAsStream("/db.json");
        JsonObject jsonObject = mapper.readValue(inputStream, JsonObject.class);

        enrolleeList = jsonObject.getEnrollees();
        directionList = jsonObject.getDirections();
    }

    private void initStorages() {
        enrolleeStorage = new EnrolleeStorage(enrolleeList);
        directionStorage = new DirectionStorage(directionList);
    }

    private void initInvoker() throws IOException {
        Receiver receiver = new Receiver(enrolleeStorage, directionStorage);
        Printer printer = new Printer();
        Map<String, Command> commandMap = fillCommandMap(receiver, printer);
        invoker = new Invoker(commandMap);
    }

    private Map<String, Command> fillCommandMap(Receiver receiver, Printer printer) {
        Command changeDirName = new ChangeDirNameCommand(receiver, "Изменить название направления: \"changeDirName + (старое название) + > + (новое название)\"", printer);
        Command update = new UpdateScoreCommand(receiver, "Изменить балл ЕГЭ абитуриента: \"updateScore + (id абитуриента) + (название предмета) + (балл предмета)\"", printer);
        Command compare = new CompareScoreCommand(receiver, "Сравнить баллы двух абитуриентов: \"compare + (id первого) + (id второго) + (название предмета)\"", printer);
        Command delete = new DeleteByIdCommand(receiver, "Удалить абитуриента по id: \"delete + (id абитуриента)\"", printer);
        Command showOriginals = new ShowEnrolleesWithOriginalsCommand(receiver, "Вывести количество абитуриентов с оригиналами на направлении: \"showOriginals + (название направления)\"", printer);
        Command showEnrollees = new ShowAllEnrolleesCommand(receiver, "Вывести список абитуриентов: \"showAllEnrollees\"", printer);
        Command showDirections = new ShowAllDirectionsCommand(receiver, "Вывести список направлений: \"showAllDirections\"", printer);
        Command showEnterPoints = new ShowEnterPointsCommand(receiver, "Показать проходной балл на направлении: \"showEnterPoints + (название направления)\"", printer);

        Map<String, Command> commandMap = new HashMap<>();

        commandMap.put("changeDirName", changeDirName);
        commandMap.put("delete", delete);
        commandMap.put("updateScore", update);
        commandMap.put("compare", compare);
        commandMap.put("showOriginals", showOriginals);
        commandMap.put("showAllEnrollees", showEnrollees);
        commandMap.put("showAllDirections", showDirections);
        commandMap.put("showEnterPoints", showEnterPoints);

        Command help = new HelpCommand(receiver, "Вывести список команд: \"help\"", printer, createDescriptionMap(commandMap));
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
