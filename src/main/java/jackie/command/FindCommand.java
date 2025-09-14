package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;
import jackie.task.Task;

import java.util.ArrayList;

/**
 * Command that prints list of tasks based on keyword that user input.
 */
public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the command that prints a list of tasks the user searched for.
     *
     * @param ui The {@link UserInterface} used for this program.
     * @param taskList The current {@link TaskList}.
     */
    public void execute(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        TaskList temp = new TaskList(new ArrayList<>());
        filterTasks(taskList, temp);
        System.out.println(ui.reply(temp.toString()));
    }

    public String toString(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        TaskList temp = new TaskList(new ArrayList<>());
        filterTasks(taskList, temp);
        return ui.reply(temp.toString());
    }

    /**
     * Filters {@link TaskList} based on specified keyword.
     *
     * @param fullTaskList The current {@link TaskList}.
     * @param filteredTaskList The {@link TaskList} for filtered {@link Task} to be added.
     */
    private void filterTasks(TaskList fullTaskList, TaskList filteredTaskList) {
        Task task;
        for (int i = 0; i < fullTaskList.size(); i++) {
            task = fullTaskList.get(i);
            if (task.toString().contains(keyword)) {
                filteredTaskList.add(task);
            }
        }
    }
}
