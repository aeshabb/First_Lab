package command;

public class ShowAllEnrollees implements Command {
    private final Receiver receiver;

    public ShowAllEnrollees(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        System.out.println(receiver.getEnrolleeList());
    }
}
