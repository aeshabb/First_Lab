package runner;

import database.DirectionStorage;
import database.EnrolleeStorage;
import entity.Direction;
import entity.Enrollee;
import handlers.DirectionsDataHandler;
import handlers.DirectionsDataHandlerImpl;
import handlers.EnrolleeDataHandler;
import handlers.EnrolleeDataHandlerImpl;
import reader.FileDataReader;

import java.io.IOException;
import java.util.List;

public class Runner {
    public void fillStorages() throws IOException {
        FileDataReader fileDataReader = new FileDataReader();
        List<String> stringEnrollee = fileDataReader.readFromDatabase("resources/Abitura.txt");
        List<String> stringDirections = fileDataReader.readFromDatabase("resources/Directions.txt");

        EnrolleeDataHandler enrolleeDataHandler = new EnrolleeDataHandlerImpl();
        DirectionsDataHandler directionsDataHandler = new DirectionsDataHandlerImpl();

        List<Enrollee> enrolleeList = enrolleeDataHandler.handle(stringEnrollee);
        List<Direction> directionList = directionsDataHandler.handle(stringDirections);

        EnrolleeStorage enrolleeStorage = new EnrolleeStorage(enrolleeList);
        DirectionStorage directionStorage = new DirectionStorage(directionList);
    }
}
