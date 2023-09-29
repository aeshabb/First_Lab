package org.example.command;

import org.example.output.Printer;

public class ShowAllDirectionsCommand extends Command {

    public ShowAllDirectionsCommand(Receiver receiver, String description, Printer printer) {
        super(receiver, description, printer);
    }


    @Override
    public void execute(String[] parameters) {
        printer.printList(receiver.getDirectionList());
    }

}
