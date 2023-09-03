package command;

public class UpdateCommand implements Command {
    private final Receiver receiver;

    public UpdateCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
    }
}
