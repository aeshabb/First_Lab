package places;

import java.util.ArrayList;
import java.util.List;

public class Seafront extends Place implements Complementable{
    private final List<Place> newBuildings = new ArrayList<>();

    public Seafront(String material, String name, double size) {
        super(material, name, size);
    }

    public void addNewBuilding(Place place) {
        newBuildings.add(place);
        System.out.println(place.getName() + " is built");
    }

    public List<Place> getNewBuildings() {
        return newBuildings;
    }
}
