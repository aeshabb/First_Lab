package command;

public class ChangeDirNameCommand implements Command {
    private final Receiver receiver;

    public ChangeDirNameCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        String[] dirNames = line.split(" > ");
        String oldName = dirNames[0];
        String newName = dirNames[1];
        receiver.changeDirName(oldName, newName);
    }
}
