package runner;

import database.DirectionStorage;
import database.EnrolleeStorage;
import entity.Direction;
import entity.Enrollee;
import handlers.DirectionsDataHandlerImpl;
import handlers.EnrolleeDataHandlerImpl;
import reader.FileDataReader;

import java.io.IOException;
import java.util.List;

public class Runner {
    public void setStrorages() throws IOException {
        FileDataReader fileDataReader = new FileDataReader();

        List<String> stringEnrollee = fileDataReader.readFromDatabase("");
        List<String> stringDirections = fileDataReader.readFromDatabase("");

        EnrolleeDataHandlerImpl enrolleeDataHandler = new EnrolleeDataHandlerImpl();
        DirectionsDataHandlerImpl directionsDataHandler = new DirectionsDataHandlerImpl();

        List<Enrollee> enrolleeList = enrolleeDataHandler.handle(stringEnrollee);
        List<Direction> directionList = directionsDataHandler.handle(stringDirections);

        EnrolleeStorage enrolleeStorage = new EnrolleeStorage(enrolleeList);
        DirectionStorage directionStorage = new DirectionStorage(directionList);
    }
}
