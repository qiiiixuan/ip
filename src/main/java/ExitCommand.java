public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    @Override
    public void execute(UserInterface ui, TaskList taskList) {
        ui.exit();
    }
}
