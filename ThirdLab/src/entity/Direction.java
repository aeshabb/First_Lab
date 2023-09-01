package entity;

public class Direction {
    private final String direction;
    private final int placesInCommon;
    private final int privilagePlaces;
    private final int tergetPlaces;
    private final Division[] divisions;
    private final Subject[] subjects;


    public Direction(String direction, int placesInCommon, int privilagePlaces, int tergetPlaces, Division[] divisions, Subject[] subjects) {
        this.direction = direction;
        this.placesInCommon = placesInCommon;
        this.privilagePlaces = privilagePlaces;
        this.tergetPlaces = tergetPlaces;
        this.divisions = divisions;
        this.subjects = subjects;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public Division[] getDivisions() {
        return divisions;
    }

    public int getPlacesInCommon() {
        return placesInCommon;
    }

    public int getPrivilagePlaces() {
        return privilagePlaces;
    }

    public int getTergetPlaces() {
        return tergetPlaces;
    }

    public String getDirection() {
        return direction;
    }
}
