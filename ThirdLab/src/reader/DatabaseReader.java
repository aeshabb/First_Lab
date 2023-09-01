package reader;

import java.io.IOException;
import java.util.List;

public interface DatabaseReader {
    public List<String> readFromDatabase(String path) throws IOException;
}
