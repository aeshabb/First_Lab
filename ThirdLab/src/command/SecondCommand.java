package command;

import entity.Data;
import entity.Direction;

public class SecondCommand implements Command {
    private final Receiver receiver;


    public SecondCommand(Receiver receiver) {

        this.receiver = receiver;
    }

    @Override
    public void execute(Data data) {

    }
}
