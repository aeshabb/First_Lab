package org.itmo.command;

import org.itmo.output.ConsolePrinter;

public class InfoCommand extends Command {

    public InfoCommand(Receiver receiver, String description, ConsolePrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public void execute(String[] parameters) {
        printer.printLine(receiver.getCollectionClass().toString());

        printer.printLine("Время иницилизации: "+receiver.getInitTime().toString());

        int size = receiver.getCollectionSize();
        printer.printLine("Количество объектов в коллекции: " + size);
    }

}
