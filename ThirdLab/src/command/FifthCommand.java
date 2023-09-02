package command;

import entity.Data;
import entity.Direction;

public class FifthCommand implements Command {
    private final Receiver receiver;


    public FifthCommand(Receiver receiver) {

        this.receiver = receiver;
    }

    @Override
    public void execute(Data data) {

    }
}
