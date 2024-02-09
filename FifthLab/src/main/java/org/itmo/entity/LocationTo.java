package org.itmo.entity;

public class LocationTo {
    private Double x; //Поле не может быть null
    private Long y; //Поле не может быть null
    private Integer z; //Поле не может быть null

    public LocationTo(Double x, Long y, Integer z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public String toString() {
        return "LocationTo{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }
}

