package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;
import jackie.task.Task;

public class DeleteCommand extends Command {

    private final int index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public void execute(UserInterface ui, TaskList taskList) throws IndexOutOfBoundsException {
        Task task = taskList.get(index);
        taskList.remove(index);
        ui.deleteReply(task, taskList.size());
    }
}
