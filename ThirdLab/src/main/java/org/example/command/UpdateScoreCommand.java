package org.example.command;

import org.example.entity.Enrollee;
import org.example.output.Printer;

public class UpdateScoreCommand extends Command {
    public UpdateScoreCommand(Receiver receiver, String description, Printer printer) {
        super(receiver, description, printer);
    }

    @Override
    public void execute(String[] parameters) {
        Enrollee enrollee = receiver.getEnrolleeById(Integer.parseInt(parameters[0]));
        receiver.updateEnrolleeScore(parameters[1], Integer.parseInt(parameters[2]), enrollee);
    }
}
