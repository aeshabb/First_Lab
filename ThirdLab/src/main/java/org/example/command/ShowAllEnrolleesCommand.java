package org.example.command;

import org.example.output.Printer;

public class ShowAllEnrolleesCommand extends Command {

    public ShowAllEnrolleesCommand(Receiver receiver, String description, Printer printer) {
        super(receiver, description, printer);
    }

    @Override
    public void execute(String[] parameters) {
        printer.printList(receiver.getEnrolleeList());
    }

}
