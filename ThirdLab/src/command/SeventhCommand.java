package command;

import entity.Data;
import entity.Direction;

public class SeventhCommand implements Command {
    private final Receiver receiver;


    public SeventhCommand(Receiver receiver) {

        this.receiver = receiver;
    }

    @Override
    public void execute(Data data) {

    }
}
