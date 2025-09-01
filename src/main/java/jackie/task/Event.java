package jackie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String name, LocalDate from, LocalDate to) {
        super(name);
        this.from = from;
        this.to = to;
    }

    public String toString () {
        String datePattern = "dd MMM yyyy";
        return "[E]" + super.toString() + " (from: " +
                this.from.format(DateTimeFormatter.ofPattern(datePattern)) + " to: " +
                this.to.format(DateTimeFormatter.ofPattern(datePattern)) + ")";
    }
}