package command;

public class ShowAllDirections implements Command {
    private final Receiver receiver;

    public ShowAllDirections(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        System.out.println(receiver.getDirectionList());
    }
}
