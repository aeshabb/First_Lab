package entity;

public class Enrollee {
    private final int id;
    private final Subject[] subjects;
    private final String summary;
    private final Division division;
    private final boolean privileges, target, originals;

    public Enrollee(int id, Subject[] subjects, String summary, Division division, boolean privileges, boolean target, boolean originals) {
        this.id = id;
        this.subjects = subjects;
        this.summary = summary;
        this.division = division;
        this.privileges = privileges;
        this.target = target;
        this.originals = originals;
    }

    public Subject[] getSubjects() {
        return subjects;
    }

    public Division getDivision() {
        return division;
    }

    public int getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public boolean isPrivileges() {
        return privileges;
    }

    public boolean isTarget() {
        return target;
    }

    public boolean isOriginals() {
        return originals;
    }
}
