package jackie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;

    public Deadline (String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    public String toString () {
        String datePattern = "dd MMM yyyy";
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern(datePattern)) + ")";
    }
}