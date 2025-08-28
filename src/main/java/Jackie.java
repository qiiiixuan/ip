import java.io.IOException;
import java.util.Scanner;

public class Jackie {

    private final Database db;
    private TaskList tasks;
    private final UserInterface ui;
    private final Parser parser;
    String path = "./data/Tasks.txt";

    public Jackie() {
        this.db = new Database(path);
        this.tasks = new TaskList(db.loadFile());
        this.ui = new UserInterface();
        this.parser = new Parser();
    }

    public void run() {
        ui.greeting();
        String input;
        Scanner inputSc = new Scanner(System.in);

        while(parser.notBye) {
            try {
                input = inputSc.nextLine();
                Command command = parser.parse(input);
                command.execute(ui, tasks);
                db.writeToFile(tasks);
            } catch (IOException | IndexOutOfBoundsException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Jackie().run();
    }
}