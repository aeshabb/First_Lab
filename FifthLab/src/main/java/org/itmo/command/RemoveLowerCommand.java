package org.itmo.command;

import org.itmo.output.ConsolePrinter;

public class RemoveLowerCommand extends Command {

    public RemoveLowerCommand(Receiver receiver, String description, ConsolePrinter printer) {
        super(receiver, description, printer);

    }

    @Override
    public void execute(String[] parameters) {
        if (parameters.length != 1) {
            printer.printLine("Неверный ввод аргументов");
        } else {
            receiver.removeLowerId(Integer.parseInt(parameters[0]));
        }
    }
}
