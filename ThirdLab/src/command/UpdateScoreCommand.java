package command;

import entity.Enrollee;

public class UpdateScoreCommand implements Command {
    private final Receiver receiver;

    public UpdateScoreCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(String line) {
        Enrollee enrollee = receiver.getEnrolleeById(Integer.parseInt(line.split(" ")[0]));
        receiver.updateEnrolleeScore(line.split(" ")[1], Integer.parseInt(line.split(" ")[2]), enrollee);
    }
}
