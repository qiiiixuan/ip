package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;
import jackie.task.Task;

/**
 * Command that deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * Executes the command to delete task from the task list.
     *
     * @param ui The {@link UserInterface} of the program.
     * @param taskList The current {@link TaskList}.
     * @throws IndexOutOfBoundsException if the index indicated to the task list is out of bounds.
     */
    @Override
    public void execute(UserInterface ui, TaskList taskList) throws IndexOutOfBoundsException {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        Task task = taskList.get(index);
        taskList.remove(index);
        System.out.println(ui.deleteReply(task, taskList.size()));
    }

    public String toString(UserInterface ui, TaskList taskList) throws IndexOutOfBoundsException {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        Task task = taskList.get(index);
        taskList.remove(index);
        return ui.deleteReply(task, taskList.size());
    }
}