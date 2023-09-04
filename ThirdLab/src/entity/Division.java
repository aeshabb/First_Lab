package entity;

public class Division {
    private String name;

    public Division(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return "Division{" +
                "Имя образовательной программы='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
