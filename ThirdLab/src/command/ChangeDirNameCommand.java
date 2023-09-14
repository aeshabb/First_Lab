package command;

import logger.Logger;

public class ChangeDirNameCommand extends Command {
    private final Receiver receiver;
    private String description;
    private final Logger logger;

    public ChangeDirNameCommand(Receiver receiver, String description, Logger logger) {
        super(receiver, description, logger);
        this.receiver = receiver;
        this.description = description;
        this.logger = logger;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String usage) {
        this.description = description;
    }

    @Override
    public void execute(String[] parameters) {
        String stringParameters = String.join(" ", parameters);
        String[] dirNames = stringParameters.split(" > ");
        String oldName = dirNames[0];
        String newName = dirNames[1];
        receiver.changeDirName(oldName, newName);
        logger.printLine("Название успешно изменено.");
    }

}
