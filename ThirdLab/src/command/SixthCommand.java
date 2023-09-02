package command;

import entity.Data;
import entity.Direction;

public class SixthCommand implements Command {
    private final Receiver receiver;


    public SixthCommand(Receiver receiver) {

        this.receiver = receiver;
    }

    @Override
    public void execute(Data data) {

    }
}
