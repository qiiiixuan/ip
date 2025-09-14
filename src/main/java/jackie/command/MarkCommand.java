package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;
import jackie.task.Task;

/**
 * Command that marks or unmarks the task.
 */
public class MarkCommand extends Command {

    private final boolean isDone;
    private final int index;

    public MarkCommand(boolean isDone, int index) {
        super();
        this.isDone = isDone;
        this.index = index;
    }

    /**
     * Executes the command to mark or unmark the task.
     *
     * @param ui The {@link UserInterface} of the program.
     * @param taskList The current {@link TaskList}.
     * @throws IndexOutOfBoundsException if index indicated to the task list is out of bounds.
     */
    @Override
    public void execute(UserInterface ui, TaskList taskList) throws IndexOutOfBoundsException {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        Task task = taskList.get(index);
        if (isDone) {
            task.markAsDone();
            System.out.println(ui.markReply(task));
        } else {
            task.markAsNotDone();
            System.out.println(ui.unmarkReply(task));
        }
    }

    public String toString(UserInterface ui, TaskList taskList) throws IndexOutOfBoundsException {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        Task task = taskList.get(index);
        if (isDone) {
            task.markAsDone();
            return ui.markReply(task);
        } else {
            task.markAsNotDone();
            return ui.unmarkReply(task);
        }
    }
}