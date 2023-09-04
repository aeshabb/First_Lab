package entity;

public class Subject {
    private String name;
    private int score;

    public Subject(String name) {
        this.setName(name);
    }

    @Override
    public String toString() {
        return "Subject{" +
                "name='" + name + '\'' +
                ", score=" + score +
                '}';
    }

    public Subject(String name, int score) {
        this.setName(name);
        this.setScore(score);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
