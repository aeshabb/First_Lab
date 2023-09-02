package command;

import entity.Data;
import entity.Direction;

public class FourthCommand implements Command {
    private final Receiver receiver;


    public FourthCommand(Receiver receiver) {

        this.receiver = receiver;
    }

    @Override
    public void execute(Data data) {

    }
}
