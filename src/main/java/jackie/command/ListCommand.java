package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public void execute(UserInterface ui, TaskList taskList) {
        ui.reply(taskList.toString());
    }
}