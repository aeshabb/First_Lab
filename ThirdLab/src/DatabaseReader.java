import java.io.IOException;
import java.util.List;

public interface DatabaseReader {
    public List<String> readFromDatabase() throws IOException;
}
