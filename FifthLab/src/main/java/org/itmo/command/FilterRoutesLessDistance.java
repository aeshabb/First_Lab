package org.itmo.command;

import org.itmo.output.CommandPrinter;

public class FilterRoutesLessDistance extends Command {

    public FilterRoutesLessDistance(Receiver receiver, String description, CommandPrinter printer) {
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
