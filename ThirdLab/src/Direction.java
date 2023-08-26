public class Direction {
    int id;
    String summary;
    String direction, privileges, target, originals;
    public Direction(int id, String summary, String direction, String privileges, String target, String originals) {
        this.id = id;
        this.summary = summary;
        this.direction = direction;
        this.privileges = privileges;
        this.target = target;
        this.originals = originals;
    }
}
