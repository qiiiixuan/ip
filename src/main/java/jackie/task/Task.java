package jackie.task;

/**
 * Task created by user, to be added to {@link jackie.TaskList}.
 */
public class Task {
    protected String name;
    protected Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.done = true;
    }

    /**
     * Marks this task as not done.
     */
    public void markAsNotDone() {
        this.done = false;
    }

    /**
     * Returns a {@link String} of this task, including name and status.
     *
     * @return A String representing the current task.
     */
    public String toString() {
        String status = done ? "[X] " : "[ ] ";
        return status + this.name;
    }
}
