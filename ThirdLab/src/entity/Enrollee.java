package entity;

public class Enrollee {
    private final int id;
    private Subject[] subjects;
    private int firstScore;
    private int secondScore;
    private int thirdScore;
    private Division division;
    private boolean privileges;
    private boolean target;
    private boolean originals;

    public Enrollee(int id, Subject[] subjects, int firstScore, int secondScore, int thirdScore, Division division, boolean privileges, boolean target, boolean originals) {
        this.id = id;
        this.firstScore = firstScore;
        this.secondScore = secondScore;
        this.thirdScore = thirdScore;
        this.setSubjects(subjects);
        this.setDivision(division);
        this.setPrivileges(privileges);
        this.setTarget(target);
        this.setOriginals(originals);
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

    public boolean isPrivileges() {
        return privileges;
    }

    public boolean isTarget() {
        return target;
    }

    public boolean isOriginals() {
        return originals;
    }

    public void setSubjects(Subject[] subjects) {
        this.subjects = subjects;
    }

    public int getFirstScore() {
        return firstScore;
    }

    public int getSecondScore() {
        return secondScore;
    }

    public int getThirdScore() {
        return thirdScore;
    }

    public void setDivision(Division division) {
        this.division = division;
    }

    public void setPrivileges(boolean privileges) {
        this.privileges = privileges;
    }

    public void setTarget(boolean target) {
        this.target = target;
    }

    public void setOriginals(boolean originals) {
        this.originals = originals;
    }

    public void setFirstScore(int firstScore) {
        this.firstScore = firstScore;
    }

    public void setSecondScore(int secondScore) {
        this.secondScore = secondScore;
    }

    public void setThirdScore(int thirdScore) {
        this.thirdScore = thirdScore;
    }
}
