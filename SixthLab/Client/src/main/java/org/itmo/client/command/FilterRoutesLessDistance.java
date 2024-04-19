package org.itmo.client.command;

import org.itmo.client.output.InfoPrinter;
import org.itmo.entity.Route;

import java.io.InputStreamReader;
import java.util.List;

public class FilterRoutesLessDistance extends Command {

    public FilterRoutesLessDistance(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);


    }

    @Override
    public void execute(String[] parameters) {
//        if (parameters.length != 1) {
//            printer.printLine("Неверный ввод аргументов");
//        } else {
//            try {
//                List<Route> list = receiver.getRoutesLessDistance(Integer.parseInt(parameters[0]));
//                printer.printLine("Routes с меньшим полем distance: " + list);
//            } catch (NumberFormatException e) {
//                printer.printLine("Неверный ввод");
//            }
//        }

    }
}
