package org.itmo.controller;

import org.itmo.command.Command;
import org.itmo.command.HistoryCommand;
import org.itmo.newStructure.MyStack;

import java.util.Arrays;
import java.util.Map;

public class Invoker {
    private final Map<String, Command> commands;
    private final HistoryCommand commandsHistory;

    public Invoker(Map<String, Command> commands) {
        this.commands = commands;
        commandsHistory = (HistoryCommand) commands.get("history");
    }

    public boolean executeCommand(String commandNameAndInfo) {
        commandNameAndInfo = commandNameAndInfo.trim();
        String[] parsedLine = commandNameAndInfo.split(" ");
        String[] argsArray = Arrays.copyOfRange(parsedLine, 1, parsedLine.length);
        if (commands.get(parsedLine[0]) != null) {
            Command command = commands.get(parsedLine[0]);
            command.execute(argsArray);
            commandsHistory.addCommand(parsedLine[0]);
            return true;
        }
        return false;
    }
}
