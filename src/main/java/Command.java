public class Command {

    private final String input;

    public Command() {
        this.input = "";
    }

    public Command(String input) {
        this.input = input;
    }

    public void execute(UserInterface ui, TaskList taskList) {
        ui.defaultReply(input);
    }
}
