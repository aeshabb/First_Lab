package org.itmo.entity;

public class LocationFrom {
    private long x;
    private double y;
    private float z;
    private String name; //Поле может быть null

    public LocationFrom(long x, double y, float z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    @Override
    public String toString() {
        return "LocationFrom{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", name='" + name + '\'' +
                '}';
    }
}
