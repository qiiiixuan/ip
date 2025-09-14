package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;

/**
 * Command that shows the error occurred from user input.
 */
public class ErrorCommand extends Command {

    private final Exception e;

    public ErrorCommand(Exception e) {
        super();
        this.e = e;
    }

    /**
     * Executes the command to show the error thrown and caught.
     *
     * @param ui The {@link UserInterface} of the program.
     * @param taskList The current {@link TaskList}.
     */
    @Override
    public void execute(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        System.out.println(ui.showError(e));
    }

    public String toString(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        return ui.showError(e);
    }
}