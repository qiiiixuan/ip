public class NewTaskCommand extends Command {

    private final Task task;

    public NewTaskCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(UserInterface ui, TaskList taskList) {
        taskList.add(task);
        ui.taskReply(task, taskList.size());
    }
}
