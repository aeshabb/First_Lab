package handlers;

import java.io.IOException;
import java.util.List;

public interface DataHandler {
    public List<? extends Object> handle() throws IOException;
}
