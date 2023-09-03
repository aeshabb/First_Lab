package command;
public class ShowEnrolleesCommand implements Command {
    private final Receiver receiver;
    public ShowEnrolleesCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String line) {
    }
}
