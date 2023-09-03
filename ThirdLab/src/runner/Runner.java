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
    public void fillStrorages() throws IOException {
        FileDataReader fileDataReader = new FileDataReader();
        List<String> stringEnrollee = fileDataReader.readFromDatabase("C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\ThirdLab\\resources\\Abitura.txt");
        List<String> stringDirections = fileDataReader.readFromDatabase("C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\ThirdLab\\resources\\Directions.txt");
        EnrolleeDataHandlerImpl enrolleeDataHandler = new EnrolleeDataHandlerImpl();
        DirectionsDataHandlerImpl directionsDataHandler = new DirectionsDataHandlerImpl();
        List<Enrollee> enrolleeList = enrolleeDataHandler.handle(stringEnrollee);
        List<Direction> directionList = directionsDataHandler.handle(stringDirections);
        EnrolleeStorage enrolleeStorage = new EnrolleeStorage(enrolleeList);
        DirectionStorage directionStorage = new DirectionStorage(directionList);
    }
}
