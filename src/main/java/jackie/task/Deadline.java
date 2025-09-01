package jackie.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Deadline task created by user, to be added to {@link jackie.TaskList}.
 */
public class Deadline extends Task {

    protected LocalDate by;

    public Deadline (String name, LocalDate by) {
        super(name);
        this.by = by;
    }

    /**
     * Returns a {@link String} of this task, including name, status, and deadline date.
     *
     * @return A String representing the current task.
     */
    public String toString () {
        String datePattern = "dd MMM yyyy";
        return "[D]" + super.toString() + " (by: " +
                this.by.format(DateTimeFormatter.ofPattern(datePattern)) + ")";
    }
}