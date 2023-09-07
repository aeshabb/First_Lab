package command;

public class ShowEnrolleesWithOriginalsCommand implements Command {
    private final Receiver receiver;

    public ShowEnrolleesWithOriginalsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        receiver.showEnrolleesWithOriginals(line);
    }
}
