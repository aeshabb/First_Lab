package org.itmo.client.command;

import org.itmo.client.output.InfoPrinter;
import org.itmo.entity.Route;

import java.io.InputStreamReader;

public class MinByFromCommand extends Command {

    public MinByFromCommand(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);


    }

    @Override
    public void execute(String[] parameters) {
//        if (parameters.length != 0) {
//            printer.printLine("Неверный ввод аргументов");
//        } else {
//            Route route = receiver.getMinByFrom();
//            printer.printLine(route.toString());
//        }
    }
}
