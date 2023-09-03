package command;
public class CompareScoreCommand implements Command {
    private final Receiver receiver;
    public CompareScoreCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(String line) {
    }
}
