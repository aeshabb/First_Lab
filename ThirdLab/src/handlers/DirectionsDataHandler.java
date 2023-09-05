package handlers;

import entity.Direction;

import java.io.IOException;
import java.util.List;

public interface DirectionsDataHandler {
    List<Direction> handle(List<String> list) throws IOException;
}
