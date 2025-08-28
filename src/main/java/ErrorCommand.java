public class ErrorCommand extends Command {

    private final Exception e;

    public ErrorCommand(Exception e) {
        super();
        this.e = e;
    }

    @Override
    public void execute(UserInterface ui, TaskList taskList) {
        ui.showError(e);
    }
}