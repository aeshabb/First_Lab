package entity;
public class Enrollee {
    private final int id;
    private Subject[] subjects;
    private Division[] division;
    private boolean privileges;
    private boolean target;
    private Division originalsToDivision;
    public Enrollee(int id, Subject[] subjects, Division[] division, boolean privileges, boolean target, Division originalsToDivision) {
        this.id = id;
        this.setSubjects(subjects);
        this.setDivision(division);
        this.setPrivileges(privileges);
        this.setTarget(target);
        this.setOriginals(originalsToDivision);
    }
    public Subject[] getSubjects() {
        return subjects;
    }
    public Division[] getDivision() {
        return division;
    }
    public int getId() {
        return id;
    }
    public boolean isPrivileges() {
        return privileges;
    }
    public boolean isTarget() {
        return target;
    }
    public Division isOriginals() {
        return originalsToDivision;
    }
    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }
    public void setDivision(Division[] division) {
        this.division = division;
    }
    public void setPrivileges(boolean privileges) {
        this.privileges = privileges;
    }
    public void setTarget(boolean target) {
        this.target = target;
    }
    public void setOriginals(Division originals) {
        this.originalsToDivision = originals;
    }
}
