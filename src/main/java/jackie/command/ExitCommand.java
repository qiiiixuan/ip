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
        System.out.println(ui.exit());
    }

    public String toString(UserInterface ui, TaskList taskList) {
        return ui.exit();
    }
}