package controller;

import command.Command;

import java.util.Map;

public class Invoker {
    private Map<String, Command> commands;

    public Invoker(Map<String, Command> commands) {
        this.commands = commands;
    }

    public void executeCommand(String commandNameAndInfo) {
        Command command = commands.get(commandNameAndInfo.split(" ")[0]);
        command.execute(commandNameAndInfo.split(" ")[1]);
    }
}
