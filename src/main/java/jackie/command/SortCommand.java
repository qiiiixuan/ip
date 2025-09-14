package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;

public class SortCommand extends Command {

    public SortCommand() {
        super();
    }

    @Override
    public void execute(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        taskList.sort();
        System.out.println(ui.sortReply());
    }

    public String toString(UserInterface ui, TaskList taskList) {
        assert ui != null : "User Interface not initialized";
        assert taskList != null : "Task List not initialized";
        taskList.sort();
        return ui.sortReply();
    }
}
