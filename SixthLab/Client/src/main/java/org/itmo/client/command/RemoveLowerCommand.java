package org.itmo.client.command;

import org.itmo.client.output.InfoPrinter;

import java.io.InputStreamReader;

public class RemoveLowerCommand extends Command {

    public RemoveLowerCommand(Receiver receiver, InfoPrinter printer, InputStreamReader inputStreamReader) {
        super(receiver, printer, inputStreamReader);


    }

    @Override
    public void execute(String[] parameters) {
//        if (parameters.length != 1) {
//            printer.printLine("Неверный ввод аргументов");
//        } else {
//            try {
//                receiver.removeLowerDistance(Integer.parseInt(parameters[0]));
//            } catch (NumberFormatException num) {
//                printer.printLine("Неверный ввод!");
//            }
//        }
    }
}
