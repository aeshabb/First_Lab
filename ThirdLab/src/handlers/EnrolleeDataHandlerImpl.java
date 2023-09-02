package handlers;

import entity.Enrollee;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnrolleeDataHandlerImpl implements EnrolleeDataHandler {
    private List<Enrollee> enrollees = new ArrayList<>();
    @Override
    public List<Enrollee> handle(List<String> list) throws IOException {
        return enrollees;
    }
}
