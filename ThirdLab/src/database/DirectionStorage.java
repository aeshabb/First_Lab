package database;

import entity.Direction;
import entity.Enrollee;

import java.util.ArrayList;
import java.util.List;

public class DirectionStorage {
    private List<Direction> directionList;

    public DirectionStorage() {
    }

    public DirectionStorage(List<Direction> directionList) {
        this.directionList = directionList;
    }

    public void createDirection(Direction direction) {
        directionList.add(direction);
    }

    public void readDirection(Direction direction) {
    }

    public void updateDirection(Direction direction) {
    }

    public void deleteDirection(Direction direction) {
        directionList.remove(direction);
    }

    public Direction getDirectionByName(String name) {
        return directionList.get(1);
    }
}
