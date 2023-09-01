package command;

import database.DirectionStorage;
import database.EnrolleeStorage;
import entity.Direction;

public class Receiver {
    private final EnrolleeStorage enrolleeStorage;
    private final DirectionStorage directionStorage;

    public Receiver(EnrolleeStorage enrolleeStorage, DirectionStorage directionStorage) {
        this.enrolleeStorage = enrolleeStorage;
        this.directionStorage = directionStorage;
    }

    public boolean someMethod(Direction direction) {
        return false;
    }

}
