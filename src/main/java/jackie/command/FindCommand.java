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
        TaskList temp = new TaskList(new ArrayList<>());
        Task task;
        for (int i = 0; i < taskList.size(); i++) {
            task = taskList.get(i);
            if (task.toString().contains(keyword)) {
                temp.add(task);
            }
        }
        ui.reply(temp.toString());
    }
}
