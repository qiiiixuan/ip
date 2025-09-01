package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;

/**
 * Command that Jackie will execute after the parser parses the input.
 */
public class Command {

    private final String input;

    public Command() {
        this.input = "";
    }

    public Command(String input) {
        this.input = input;
    }

    /**
     * Executes the command and shows the output to user.
     *
     * @param ui The {@link UserInterface} of the program.
     * @param taskList The current {@link TaskList}.
     */
    public void execute(UserInterface ui, TaskList taskList) {
        ui.defaultReply(input);
    }
}