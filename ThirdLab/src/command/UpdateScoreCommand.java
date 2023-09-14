package command;

import entity.Enrollee;
import logger.Logger;

public class UpdateScoreCommand extends Command {
    private final Receiver receiver;
    private String description;
    private final Logger logger;

    public UpdateScoreCommand(Receiver receiver, String description, Logger logger) {
        super(receiver, description, logger);
        this.receiver = receiver;
        this.description = description;
        this.logger = logger;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void execute(String[] parameters) {
        Enrollee enrollee = receiver.getEnrolleeById(Integer.parseInt(parameters[0]));
        receiver.updateEnrolleeScore(parameters[1], Integer.parseInt(parameters[2]), enrollee);
    }
}
