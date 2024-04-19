package org.itmo.client.controller;


import org.itmo.client.command.Command;

import java.util.Arrays;
import java.util.Map;

public class Invoker {
    private final Map<String, Command> commands;
    //private final HistoryCommand historyCmd;
    public Invoker(Map<String, Command> commands) {
        this.commands = commands;
        //this.historyCmd = (HistoryCommand) commands.get("history");
    }

    // Парсит команду с аргументами и делегирует выполнение классу Command
    public boolean executeCommand(String commandAndArgs) {
        commandAndArgs = commandAndArgs.trim();
        String[] parsed = commandAndArgs.split(" ");
        String[] args = Arrays.copyOfRange(parsed, 1, parsed.length);
        // проверка существования такой команды
        if (!commands.containsKey(parsed[0]))
            return false;

        Command command =  commands.get(parsed[0]);
        //historyCmd.add((AbstractCommand)command, args);
        command.execute(args);
        return true;
    }
}
