package org.itmo.command;

import org.itmo.output.ConsolePrinter;

public class ClearCommand extends Command {

    public ClearCommand(Receiver receiver, String description, ConsolePrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public void execute(String[] parameters) {
        receiver.clearRouteTreeSet();
        printer.printLine("Все элементы коллекции удалены.");
    }
}
