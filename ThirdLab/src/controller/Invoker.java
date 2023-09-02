package controller;

import command.Command;
import entity.Data;
import entity.Direction;

import java.util.Map;

public class Invoker {
    private Map<String, Command> commands;

    public  Invoker(Map<String, Command> commands) {

        this.commands = commands;
    }

    public void executeCommand(String commandName, Data data) {
        Command command = commands.get(commandName);
        command.execute(data);
    }
}
