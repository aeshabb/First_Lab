package entity;

import java.util.Arrays;

public class Direction {
    private String name;
    private int placesInCommon;
    private int privilegePlaces;
    private int targetPlaces;

    @Override
    public String toString() {
        return "Direction{" +
                "Название направления='" + name + '\'' +
                ", Всего мест=" + placesInCommon +
                ", Мест для квоты=" + privilegePlaces +
                ", Мест для целевого обучения=" + targetPlaces +
                ", Образовательные программы=" + Arrays.toString(divisions) +
                ", Предметы для поступления=" + Arrays.toString(subjects) +
                '}';
    }

    private Division[] divisions;
    private Subject[] subjects;

    public Direction(String name, int placesInCommon, int privilegePlaces, int targetPlaces, Division[] divisions, Subject[] subjects) {
        this.setDirection(name);
        this.setPlacesInCommon(placesInCommon);
        this.setPrivilagePlaces(privilegePlaces);
        this.setTergetPlaces(targetPlaces);
        this.setDivisions(divisions);
        this.setSubjects(subjects);
    }

    public String getDirection() {
        return name;
    }

    public void setDirection(String name) {
        this.name = name;
    }

    public int getPlacesInCommon() {
        return placesInCommon;
    }

    public void setPlacesInCommon(int placesInCommon) {
        this.placesInCommon = placesInCommon;
    }

    public int getPrivilagePlaces() {
        return privilegePlaces;
    }

    public void setPrivilagePlaces(int privilegePlaces) {
        this.privilegePlaces = privilegePlaces;
    }

    public int getTergetPlaces() {
        return targetPlaces;
    }

    public void setTergetPlaces(int targetPlaces) {
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
}
