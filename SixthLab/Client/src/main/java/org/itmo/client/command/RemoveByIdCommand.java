package org.itmo.client.command;

import org.itmo.client.output.InfoPrinter;

import java.io.InputStreamReader;

public class RemoveByIdCommand extends Command {

    public RemoveByIdCommand(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);


    }

    @Override
    public void execute(String[] parameters) {
//        try {
//            receiver.deleteRouteById(Integer.parseInt(parameters[0]));
//            printer.printLine("Route удален.");
//        } catch (NumberFormatException num) {
//            printer.printLine("Число не int!");
//        } catch (NullPointerException nullPointerException) {
//            printer.printLine("Такого индекса нет!");
//        }
    }
}
