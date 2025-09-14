package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;

/**
 * Command that prints out the list of tasks.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Executes the command to print our the list of tasks.
     *
     * @param ui The {@link UserInterface} of the program.
     * @param taskList The current {@link TaskList}.
     */
    @Override
    public void execute(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        System.out.println(ui.reply(taskList.toString()));
    }

    public String toString(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        return ui.reply(taskList.toString());
    }
}