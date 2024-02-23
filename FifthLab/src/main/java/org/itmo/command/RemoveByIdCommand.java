package org.itmo.command;

import org.itmo.output.CommandPrinter;

public class RemoveByIdCommand extends Command {

    public RemoveByIdCommand(Receiver receiver, String description, CommandPrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public void execute(String[] parameters) {
        receiver.deleteRouteById(Integer.parseInt(parameters[0]));
        printer.printLine("Route удален.");
    }
}
