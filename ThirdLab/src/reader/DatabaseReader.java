package reader;

import java.io.IOException;
import java.util.List;

public interface DatabaseReader {
    List<String> readFromDatabase(String path) throws IOException;
}
