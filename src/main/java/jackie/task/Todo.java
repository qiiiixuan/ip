package jackie.task;

/**
 * To-do task created by user, to be added to {@link jackie.TaskList}.
 */
public class Todo extends Task {

    public Todo(String name) {
        super(name);
    }

    /**
     * Returns a {@link String} of this task, including name and status.
     *
     * @return A String representing the current task.
     */
    public String toString() {
        return "[T]" + super.toString();
    }
}