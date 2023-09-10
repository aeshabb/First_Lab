package command;

import entity.Enrollee;

public class ShowEnrolleesWithOriginalsCommand implements Command {
    private final Receiver receiver;

    public ShowEnrolleesWithOriginalsCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        for (Enrollee enrollee : receiver.getEnrolleesWithOriginals(line)) {
            System.out.println(enrollee);
        }
    }
}
