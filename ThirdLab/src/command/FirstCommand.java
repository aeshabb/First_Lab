package command;

import entity.Data;
import entity.Direction;

public class FirstCommand implements Command {
    private final Receiver receiver;


    public FirstCommand(Receiver receiver) {
        this.receiver = receiver;
    }

    @Override
    public void execute(Data data) {

    }
}
