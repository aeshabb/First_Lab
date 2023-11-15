package places;

import java.util.Objects;

public abstract class Place {
    private String material;
    private String name;
    private double size;

    public Place(String name, String material, double size) {
        this.name = name;
        this.material = material;
        this.size = size;
    }
    public Place() {}

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Place place)) return false;
        return Objects.equals(getMaterial(), place.getMaterial()) && Objects.equals(getName(), place.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaterial(), getName());
    }

    @Override
    public String toString() {
        return "Building{" +
                "material='" + material + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
