package controller;

import command.Command;

import java.util.Map;

public class Invoker {
    private Map<String, Command> commands;

    public Invoker(Map<String, Command> commands) {
        this.commands = commands;
    }

    public void executeCommand(String commandNameAndInfo) {
        String parameters = "";
        String[] parsedLine = commandNameAndInfo.split(" ");
        Command command = commands.get(parsedLine[0]);
        if (parsedLine.length > 1) {
            for (int i = 1; i < parsedLine.length; i++) {
                parameters += parsedLine[i] + " ";
            }
            command.execute(parameters.strip());
        } else {
            command.execute("");
        }
    }
}
