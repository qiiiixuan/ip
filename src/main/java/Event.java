public class Event extends Task{

    protected String from;
    protected String to;

    public Event(String name, String from, String to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String toString () {
        return "[E]" + super.toString() + " (from: " + this.from + " to: " + this.to + ")";
    }
}
