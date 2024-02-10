package org.itmo.command;

import org.itmo.output.ConsolePrinter;

public class FilterRoutesLessDistance extends Command {

    public FilterRoutesLessDistance(Receiver receiver, String description, ConsolePrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 1) {
            printer.printLine("Неверный ввод аргументов");
        } else {
            printer.printList(receiver.getRoutesLessDistance(Integer.parseInt(parameters[0])));
        }

    }
}
