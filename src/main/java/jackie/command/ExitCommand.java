package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;

/**
 * Command that shows goodbye message and exits the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    /**
     * Executes the command to show goodbye message and exit program.
     *
     * @param ui The {@link UserInterface} of the program.
     * @param taskList The current {@link TaskList}.
     */
    @Override
    public void execute(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        System.out.println(ui.exit());
    }

    public String toString(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        return ui.exit();
    }
}