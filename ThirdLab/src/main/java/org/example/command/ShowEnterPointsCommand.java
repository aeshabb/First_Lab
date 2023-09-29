package org.example.command;

import org.example.output.Printer;

public class ShowEnterPointsCommand extends Command {

    public ShowEnterPointsCommand(Receiver receiver, String description, Printer printer) {
        super(receiver, description, printer);
    }

    @Override
    public void execute(String[] parameters) {
        String division = parameters[0];
        int points = receiver.getEnterPoints(division);
        if (points == 0) {
            printer.printLine("Невозможно определить");
        } else {
            printer.printLine(Integer.toString(points));
        }
    }
}
