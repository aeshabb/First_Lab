package command;

import entity.Data;
import entity.Direction;

public class EighthCommand implements Command {
    private final Receiver receiver;


    public EighthCommand(Receiver receiver) {
        this.receiver = receiver;
    }
    @Override
    public void execute(Data data) {
        receiver.someMethod(data.getDirection());
    }
}
