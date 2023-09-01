package database;

import entity.Enrollee;
import handlers.EnrolleeDataHandler;

import java.util.ArrayList;
import java.util.List;

public class EnrolleeStorage {
    private List<Enrollee> enrolleeList;

    public EnrolleeStorage() {
        this.enrolleeList = new ArrayList<>();
    }
}
