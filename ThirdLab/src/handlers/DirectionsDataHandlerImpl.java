package handlers;
import entity.Direction;
import entity.Division;
import entity.Subject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class DirectionsDataHandlerImpl implements DirectionsDataHandler {
    @Override
    public List<Direction> handle(List<String> list) throws IOException {
        List<Direction> directions = new ArrayList<>();
        for (String line : list) {
            String[] infoAboutDirections = line.split(" : ");
            String directionName = infoAboutDirections[0];
            String[] places = infoAboutDirections[1].split(" ");
            int placesInCommon = Integer.parseInt(places[0]);
            int privilegePlaces = Integer.parseInt(places[1]);
            int targetPlaces = Integer.parseInt(places[2]);
            String[] divisionsName = infoAboutDirections[2].split(", ");
            Division[] divisions = new Division[divisionsName.length];
            for (String name : divisionsName) {
                int i = 0;
                divisions[i] = new Division(name);
                i++;
            }
            String[] subjectsName = infoAboutDirections[3].split(", ");
            Subject[] subjects = new Subject[subjectsName.length];
            for (String name : subjectsName) {
                int i = 0;
                subjects[i] = new Subject(name);
                i++;
            }
            directions.add(new Direction(directionName, placesInCommon, privilegePlaces, targetPlaces, divisions, subjects));
        }
        return directions;
    }
}
