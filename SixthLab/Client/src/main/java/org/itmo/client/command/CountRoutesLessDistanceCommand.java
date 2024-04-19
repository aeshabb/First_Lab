package org.itmo.client.command;

import org.itmo.client.output.InfoPrinter;

import java.io.InputStreamReader;

public class CountRoutesLessDistanceCommand extends Command {

    public CountRoutesLessDistanceCommand(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);


    }

    @Override
    public void execute(String[] parameters) {
//        if (parameters.length != 1) {
//            printer.printLine("Неверный ввод аргументов");
//        } else {
//            try {
//                int i;
//                i = receiver.countRoutesLessDistance(Integer.parseInt(parameters[0]));
//                printer.printLine("Количество Route с меньшим полем distance: " + i);
//            } catch (NumberFormatException num) {
//                printer.printLine("Неверный ввод!");
//            }
//
//        }

    }
}
