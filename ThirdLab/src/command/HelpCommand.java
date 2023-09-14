package command;

import logger.Logger;

import java.util.List;

public class HelpCommand extends Command {
    private final Receiver receiver;
    private String description;
    private List<String> descriptionCommands;
    private final Logger logger;

    public HelpCommand(Receiver receiver, String description, Logger logger, List<String> descriptionCommands) {
        super(receiver, description, logger);
        this.receiver = receiver;
        this.description = description;
        this.logger = logger;
        descriptionCommands.add(description);
        descriptionCommands.add("Завершить работу: \"quit\"");
        this.descriptionCommands = descriptionCommands;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public void execute(String[] parameters) {
        logger.printList(descriptionCommands);
    }

}
