package command;

import logger.Logger;

public class ShowAllEnrolleesCommand extends Command {
    private final Receiver receiver;
    private String description;
    private final Logger logger;

    public ShowAllEnrolleesCommand(Receiver receiver, String description, Logger logger) {
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
        logger.printList(receiver.getEnrolleeList());
    }

}
