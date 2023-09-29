package org.example.entity;

import lombok.NoArgsConstructor;

import java.util.Objects;
@NoArgsConstructor
public class Division {
    private String name;

    public Division(String name) {
        this.name = name;
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
