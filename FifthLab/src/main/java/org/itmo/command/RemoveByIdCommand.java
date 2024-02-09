package org.itmo.command;

import org.itmo.output.ConsolePrinter;

public class RemoveByIdCommand extends Command {

    public RemoveByIdCommand(Receiver receiver, String description, ConsolePrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public void execute(String[] parameters) {
        receiver.deleteRouteById(Integer.parseInt(parameters[0]));
        printer.printLine("Route удален.");
    }
}
