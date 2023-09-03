package command;
public class ChangeDirNameCommand implements Command {
    private final Receiver receiver;
    public ChangeDirNameCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String line) {
    }
}
