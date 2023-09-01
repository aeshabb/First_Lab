package handlers;

import entity.Direction;
import entity.Enrollee;
import reader.FileDataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirectionsDataHandler implements DataHandler{
    private List<Direction> directions = new ArrayList<>();
    @Override
    public List<Direction> handle() throws IOException {
        FileDataReader fileDataReader = new FileDataReader();
        List<String> stringDirections = fileDataReader.readFromDatabase("C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\ThirdLab\\src\\reader\\Directions.txt");
        return directions;
    }
}
