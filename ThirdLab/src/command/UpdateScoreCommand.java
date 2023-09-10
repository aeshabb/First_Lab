package command;

import entity.Enrollee;

public class UpdateScoreCommand implements Command {
    private final Receiver receiver;

    public UpdateScoreCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        String[] info = line.split(" ");
        Enrollee enrollee = receiver.getEnrolleeById(Integer.parseInt(info[0]));
        receiver.updateEnrolleeScore(info[1], Integer.parseInt(info[2]), enrollee);
    }
}
