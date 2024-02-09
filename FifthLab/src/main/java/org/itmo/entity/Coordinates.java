package org.itmo.entity;

public class Coordinates {
    private Double x; //Максимальное значение поля: 660, Поле не может быть null
    private int y; //Максимальное значение поля: 909

    public Coordinates(Double x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
