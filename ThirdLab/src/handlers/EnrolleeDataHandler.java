package handlers;

import entity.Enrollee;

import java.io.IOException;
import java.util.List;

public interface EnrolleeDataHandler {
    List<Enrollee> handle(List<String> list) throws IOException;
}
