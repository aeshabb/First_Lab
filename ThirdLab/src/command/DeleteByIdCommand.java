package command;
public class DeleteByIdCommand implements Command {
    private final Receiver receiver;
    public DeleteByIdCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String line) {
    }
}
