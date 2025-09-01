package jackie.command;

import jackie.TaskList;
import jackie.UserInterface;
import jackie.task.Task;

public class MarkCommand extends Command {

    private final boolean isDone;
    private final int index;

    public MarkCommand(boolean isDone, int index) {
        super();
        this.isDone = isDone;
        this.index = index;
    }

    @Override
    public void execute(UserInterface ui, TaskList taskList) throws IndexOutOfBoundsException {
        Task task = taskList.get(index);
        if (isDone) {
            task.markAsDone();
            ui.markReply(task);
        } else {
            task.markAsNotDone();
            ui.unmarkReply(task);
        }
    }
}