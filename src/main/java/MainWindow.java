import jackie.Database;
import jackie.Parser;
import jackie.TaskList;
import jackie.UserInterface;
import jackie.command.Command;
import jackie.command.ErrorCommand;
import jackie.command.ExitCommand;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    String path = "./data/Tasks.txt";
    private final Parser parser = new Parser();
    private final UserInterface ui = new UserInterface();
    private final Database db = new Database(path);
    private TaskList tasks = new TaskList(db.loadFile());

    private Image jackieGreetingImage = new Image(this.getClass().getResourceAsStream(
            "/images/jackie_greeting.png"));
    private Image jackieValidImage = new Image(this.getClass().getResourceAsStream(
            "/images/jackie_valid.png"));
    private Image jackieInvalidImage = new Image(this.getClass().getResourceAsStream(
            "/images/jackie_invalid.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream(
            "/images/user.png"));

    @FXML
    public void initialize() {
        assert jackieGreetingImage != null &&
                jackieInvalidImage != null &&
                jackieValidImage != null &&
                userImage != null
                : "Images missing from resources";
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
        dialogContainer.getChildren().add(DialogBox.getJackieDialog(ui.guiGreeting(), jackieGreetingImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Jackie's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        Command command = parser.parse(input);
        Image jackieImage = command.getClass() == ExitCommand.class
                ? jackieGreetingImage
                : command.getClass() == ErrorCommand.class
                ? jackieInvalidImage
                : jackieValidImage;
        String response = command.toString(ui, tasks);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getJackieDialog(response, jackieImage)
        );
        userInput.clear();
    }
}