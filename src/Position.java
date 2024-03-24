public class Position {
    public String data;
    Position next;

    public boolean hasGone;
    public Position(String data) {
        this.data = data;
        this.next = null;
        hasGone = true;
    }
}

