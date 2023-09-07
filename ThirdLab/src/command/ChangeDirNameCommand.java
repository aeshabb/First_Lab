package command;

public class ChangeDirNameCommand implements Command {
    private final Receiver receiver;

    public ChangeDirNameCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        String oldName = line.split(" > ")[0];
        String newName = line.split(" > ")[1];
        receiver.changeDirName(oldName, newName);
    }
}
