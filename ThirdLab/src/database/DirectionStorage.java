package database;

import entity.Direction;

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

    public void changeDirName(String oldName, String newName) {
        for (Direction direction : directionList) {
            if (direction.getName().equals(oldName)) {
                direction.setName(newName);
            }
        }
    }

    public void deleteDirection(Direction direction) {
        directionList.remove(direction);
    }

    public List<Direction> getDirectionList() {
        return directionList;
    }
}
