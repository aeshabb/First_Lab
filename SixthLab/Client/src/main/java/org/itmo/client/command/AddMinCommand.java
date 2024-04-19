package org.itmo.client.command;

import org.itmo.client.output.InfoPrinter;

import java.io.InputStreamReader;

public class AddMinCommand extends Command {

    public AddMinCommand(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);


    }

    @Override
    public void execute(String[] parameters) {
//        if (parameters.length != 1) {
//            printer.printLine("Неверный ввод аргументов");
//        } else {
//            if (receiver.checkIfMinDistance(Integer.parseInt(parameters[0]))) {
//                RouteParser routeParser = new RouteParser(receiver, printer, parameters);
//                receiver.addRouteToTreeSet(routeParser.parseRouteFromConsole());
//                printer.printLine("Route добавлен в коллекцию!");
//            } else {
//                printer.printLine("Distance не минимальный!");
//            }
//        }

    }
}
