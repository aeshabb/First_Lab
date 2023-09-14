package command;

import entity.Enrollee;
import logger.Logger;

public class CompareScoreCommand extends Command {
    private final Receiver receiver;
    private String description;
    private final Logger logger;

    public CompareScoreCommand(Receiver receiver, String description, Logger logger) {
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
        Enrollee enrollee1 = receiver.getEnrolleeById(Integer.parseInt(parameters[0]));
        Enrollee enrollee2 = receiver.getEnrolleeById(Integer.parseInt(parameters[1]));
        int enrolleeScore1 = receiver.getSubjectScore(enrollee1, parameters[2]);
        int enrolleeScore2 = receiver.getSubjectScore(enrollee2, parameters[2]);
        logger.printLine("У первого абитуриента: " + enrolleeScore1 + " баллов. По предмету: " + parameters[2]);
        logger.printLine("У второго абитуриента: " + enrolleeScore2 + " баллов. По предмету: " + parameters[2]);
    }

}
