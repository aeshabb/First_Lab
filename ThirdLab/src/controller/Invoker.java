package controller;

import command.Command;

import java.util.Map;

public class Invoker {
    private Map<String, Command> commands;

    public Invoker(Map<String, Command> commands) {
        this.commands = commands;
    }

    public void executeCommand(String commandNameAndInfo) {
        String[] parsedLine = commandNameAndInfo.split(" ");
        Command command = commands.get(parsedLine[0]);
        if (parsedLine.length > 1) {
            StringBuilder parameters = new StringBuilder();
            for (int i = 1; i < parsedLine.length; i++) {
                parameters.append(parsedLine[i]);
                parameters.append(" ");
            }
            command.execute(parameters.deleteCharAt(parameters.length() - 1).toString());
        } else {
            command.execute("");
        }
    }
}
