package org.itmo.command;

import org.itmo.entity.Route;
import org.itmo.output.ConsolePrinter;

public class MinByFromCommand extends Command {

    public MinByFromCommand(Receiver receiver, String description, ConsolePrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 0) {
            printer.printLine("Неверный ввод аргументов");
        } else {
            Route route = receiver.getMinByFrom();
            printer.printLine(route.toString());
        }
    }
}
