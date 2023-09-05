package command;

import database.DirectionStorage;
import database.EnrolleeStorage;
import entity.Direction;
import entity.Enrollee;

import java.util.List;

public class Receiver {
    private final EnrolleeStorage enrolleeStorage;
    private final DirectionStorage directionStorage;

    private List<Enrollee> enrolleeList;

    public Receiver(EnrolleeStorage enrolleeStorage, DirectionStorage directionStorage) {
        this.enrolleeStorage = enrolleeStorage;
        this.directionStorage = directionStorage;
        enrolleeList = enrolleeStorage.getEnrolleeList();
    }

    public void deleteById(int id) {
        for (int i = 0; i < enrolleeList.size(); i++) {
            if (enrolleeList.get(i).getId() == id) {
                deleteEnrollee(enrolleeList.get(i));
            }
        }
    }

    public void deleteEnrollee(Enrollee enrollee) {
        enrolleeStorage.deleteEnrollee(enrollee);
    }
}
