package handlers;

import entity.Enrollee;
import reader.FileDataReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnrolleeDataHandler implements DataHandler {
    private List<Enrollee> enrollees = new ArrayList<>();

    @Override
    public List<Enrollee> handle() throws IOException {
        FileDataReader fileDataReader = new FileDataReader();
        List<String> stringEnrollees = fileDataReader.readFromDatabase("C:\\Users\\Алексей\\OneDrive\\Рабочий стол\\codes\\Trashbox\\ThirdLab\\src\\reader\\Abitura.txt");
        return enrollees;
    }
}
