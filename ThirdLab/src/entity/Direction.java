package entity;

import java.util.Arrays;
import java.util.Objects;

public class Direction {
    private String name;
    private int placesInCommon;
    private int privilegePlaces;
    private int targetPlaces;
    private Division[] divisions;
    private Subject[] subjects;

    public Direction(String name, int placesInCommon, int privilegePlaces, int targetPlaces, Division[] divisions, Subject[] subjects) {
        this.name = name;
        this.placesInCommon = placesInCommon;
        this.privilegePlaces = privilegePlaces;
        this.targetPlaces = targetPlaces;
        this.divisions = divisions;
        this.subjects = subjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlacesInCommon() {
        return placesInCommon;
    }

    public void setPlacesInCommon(int placesInCommon) {
        this.placesInCommon = placesInCommon;
    }

    public int getPrivilegePlaces() {
        return privilegePlaces;
    }

    public void setPrivilegePlaces(int privilegePlaces) {
        this.privilegePlaces = privilegePlaces;
    }

    public int getTargetPlaces() {
        return targetPlaces;
    }

    public void setTargetPlaces(int targetPlaces) {
        this.targetPlaces = targetPlaces;
    }

    public Division[] getDivisions() {
        return divisions;
    }

    public void setDivisions(Division[] divisions) {
        this.divisions = divisions;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    @Override
    public String toString() {
        return "Direction{" +
                "name='" + name + '\'' +
                ", placesInCommon=" + placesInCommon +
                ", privilegePlaces=" + privilegePlaces +
                ", targetPlaces=" + targetPlaces +
                ", divisions=" + Arrays.toString(divisions) +
                ", subjects=" + Arrays.toString(subjects) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Direction direction)) return false;
        return getPlacesInCommon() == direction.getPlacesInCommon() && getPrivilegePlaces() == direction.getPrivilegePlaces() && getTargetPlaces() == direction.getTargetPlaces() && Objects.equals(name, direction.name) && Arrays.equals(getDivisions(), direction.getDivisions()) && Arrays.equals(getSubjects(), direction.getSubjects());
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(name, getPlacesInCommon(), getPrivilegePlaces(), getTargetPlaces());
        result = 31 * result + Arrays.hashCode(getDivisions());
        result = 31 * result + Arrays.hashCode(getSubjects());
        return result;
    }
}
