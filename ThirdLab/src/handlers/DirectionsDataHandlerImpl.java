package handlers;

import entity.Direction;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirectionsDataHandlerImpl implements DirectionsDataHandler{
    private List<Direction> directions = new ArrayList<>();
    @Override
    public List<Direction> handle(List<String> list) throws IOException {
        return directions;
    }
}
