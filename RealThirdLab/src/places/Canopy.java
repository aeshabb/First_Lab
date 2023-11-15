package places;

import java.util.ArrayList;
import java.util.List;

public class Canopy extends Place implements Complementable{
    private final List<Place> buildingsUnderCanopy = new ArrayList<>();
    public Canopy(String name, String material, double size) {
        super(name, material, size);
    }

    public void addNewBuilding(Place place) {
        buildingsUnderCanopy.add(place);
    }

    public List<Place> getBuildingsUnderCanopy() {
        return buildingsUnderCanopy;
    }
}
