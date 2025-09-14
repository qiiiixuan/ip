package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;
import jackie.task.Task;

/**
 * Command that adds new task to the task list.
 */
public class NewTaskCommand extends Command {

    private final Task task;

    public NewTaskCommand(Task task) {
        super();
        this.task = task;
    }

    /**
     * Executes the command to add new task to the task list.
     *
     * @param ui The {@link UserInterface} of the program.
     * @param taskList The current {@link TaskList}.
     */
    @Override
    public void execute(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        taskList.add(task);
        System.out.println(ui.taskReply(task, taskList.size()));
    }

    public String toString(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        taskList.add(task);
        return ui.taskReply(task, taskList.size());
    }
}