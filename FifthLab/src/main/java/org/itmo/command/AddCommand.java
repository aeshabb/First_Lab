package org.itmo.command;

import org.itmo.output.ConsolePrinter;

public class AddCommand extends Command {

    public AddCommand(Receiver receiver, String description, ConsolePrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public void execute(String[] parameters) {
        RouteParser routeParser = new RouteParser(receiver, printer, parameters);
        receiver.addRouteToTreeSet(routeParser.parseRouteFromConsole());
        printer.printLine("Route добавлен в коллекцию!");
    }
}

