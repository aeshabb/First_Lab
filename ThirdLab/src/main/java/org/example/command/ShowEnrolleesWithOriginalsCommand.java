package org.example.command;

import org.example.output.Printer;

public class ShowEnrolleesWithOriginalsCommand extends Command {
    public ShowEnrolleesWithOriginalsCommand(Receiver receiver, String description, Printer printer) {
        super(receiver, description, printer);
        this.receiver = receiver;
        this.description = description;
        this.printer = printer;
    }

    @Override
    public void execute(String[] parameters) {
        printer.printList(receiver.getEnrolleesWithOriginals(parameters[0]));
    }

}
