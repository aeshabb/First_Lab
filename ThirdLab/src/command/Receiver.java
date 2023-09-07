package command;

import database.DirectionStorage;
import database.EnrolleeStorage;
import entity.Direction;
import entity.Enrollee;
import entity.Subject;

import java.util.List;

public class Receiver {
    private final EnrolleeStorage enrolleeStorage;
    private final DirectionStorage directionStorage;

    public Receiver(EnrolleeStorage enrolleeStorage, DirectionStorage directionStorage) {
        this.enrolleeStorage = enrolleeStorage;
        this.directionStorage = directionStorage;
    }

    public void deleteById(int id) {
        for (int i = 0; i < enrolleeStorage.getEnrolleeList().size(); i++) {
            if (enrolleeStorage.getEnrolleeList().get(i).getId() == id) {
                deleteEnrollee(enrolleeStorage.getEnrolleeList().get(i));
            }
        }
    }

    public void deleteEnrollee(Enrollee enrollee) {
        enrolleeStorage.deleteEnrollee(enrollee);
        System.out.println("After deleting: " + enrolleeStorage.getEnrolleeList());
    }

    public Enrollee getEnrolleeById(int id) {
        Enrollee enrolleeToSearch = null;
        for (Enrollee enrollee : enrolleeStorage.getEnrolleeList()) {
            if (enrollee.getId() == id) {
                enrolleeToSearch = enrollee;
            }
        }
        return enrolleeToSearch;
    }

    public void updateEnrolleeScore(String name, int score, Enrollee enrollee) {
        enrolleeStorage.updateEnrolleeScore(name, score, enrollee);
    }

    public void changeDirName(String oldName, String newName) {
        directionStorage.changeDirName(oldName, newName);
    }

    public List<Direction> getDirectionList() {
        return directionStorage.getDirectionList();
    }

    public List<Enrollee> getEnrolleeList() {
        return enrolleeStorage.getEnrolleeList();
    }

    public int getSubjectScore(Enrollee enrollee, String name) {
        int score = 0;
        Subject[] subjects = enrollee.getSubjects();
        for (Subject subject : subjects) {
            if (subject.getName().equals(name)) {
                score = subject.getScore();
            }
        }
        return score;
    }

    public void showEnrolleesWithOriginals(String division) {
        for (Enrollee enrollee : enrolleeStorage.getEnrolleeList()) {
            if ((enrollee.isOriginals()).getName().equals(division)) {
                System.out.println(enrollee);
            }
        }
    }
}
