import database.DirectionStorage;
import database.EnrolleeStorage;
import entity.Direction;
import entity.Enrollee;
import runner.Runner;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Runner run = new Runner();
        run.fillStorages();
    }
}