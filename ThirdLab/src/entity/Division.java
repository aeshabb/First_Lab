package entity;

import java.util.Objects;

public class Division {
    private String name;

    public Division(String name) {
        this.setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Division{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Division division)) return false;
        return Objects.equals(getName(), division.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
