public class Enrollee {
    private int id;
    private String summary;
    private String direction, privileges, target, originals;
    public Enrollee(int id, String summary, String direction, String privileges, String target, String originals) {
        this.id = id;
        this.summary = summary;
        this.direction = direction;
        this.privileges = privileges;
        this.target = target;
        this.originals = originals;
    }

    public String getDirection() {
        return direction;
    }
    public int getId() {
        return id;
    }
    public String getOriginals() {
        return originals;
    }
    public String getPrivileges() {
        return privileges;
    }
    public String getSummary() {
        return summary;
    }
    public String getTarget() {
        return target;
    }
}
