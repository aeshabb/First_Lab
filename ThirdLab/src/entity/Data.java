package entity;

import database.DirectionStorage;

public class Data {
    public Direction getDirection() {
        DirectionStorage directionStorage = new DirectionStorage();
        Direction direction = directionStorage.getDirectionByName("PI");
        return direction;
    }
}
