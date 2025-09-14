package jackie;

import jackie.task.Task;

import java.time.format.DateTimeParseException;

/**
 * User interface of Jackie. Handles everything that the user sees including output and errors.
 */
public class UserInterface {

    public UserInterface() {}

    /**
     * Prints a welcome message on the terminal.
     */
    public String greeting() {
        String logo = """
                 _____           _
                |__ __|__   ___ | | __ *  ___
                  | |/ _  |/ ,_\\| |/ /| |/ _ \\
                ,_| | |_| |  |_ |   < | |  __/
                \\___/\\__,_|\\___/|_|\\_\\|_|\\___/
                """;
        return "Welcome! I am...\n" + logo +
                "______________________________\nAdd tasks to your list!\n";
    }

    public String guiGreeting() {
        String logo = """
                Jackie
                """;
        return "Welcome! I am...\n" + logo +
                "______________________________\nAdd tasks to your list!\n";
    }

    /**
     * Prints the output in the format of a reply on the terminal.
     *
     * @param output A {@link String} to be printed as a reply.
     */
    public String reply(String output) {
        for (String line : output.split("\n")) {
            line = "\t" + line;
        }
        return output;
    }

    /**
     * Prints a success message after marking a {@link Task}.
     *
     * @param task A {@link Task} to be marked as done.
     */
    public String markReply(Task task) {
        return reply("Good work! I have marked this task as done:\n\t" +
                task + "\n"
        );
    }

    /**
     * Prints a success message after unmarking a {@link Task}.
     *
     * @param task A {@link Task} to be marked as not done.
     */
    public String unmarkReply(Task task) {
        return reply("I have marked this task as not done:\n\t" +
                task + "\n"
        );
    }

    /**
     * Prints a success message after adding a new {@link Task}.
     *
     * @param task A {@link Task} to be added to the {@link TaskList}.
     * @param size The number of tasks left in the task list.
     */
    public String taskReply(Task task, int size) {
        return reply("New task added:\n\t" +
                task + "\nYou have " +
                size + " tasks in the list.\n"
        );
    }

    /**
     * Prints a success message after deleting a {@link Task}.
     *
     * @param task A {@link Task} to be removed from the {@link TaskList}.
     * @param size The number of tasks left in the task list.
     */
    public String deleteReply(Task task, int size) {
        return reply("I have removed this task from the list:\n\t" +
                task + "\nYou have " +
                size + " tasks in the list.\n"
        );
    }

    /**
     * Prints a default reply onto the terminal when an invalid user input is detected.
     *
     * @param input The invalid user input.
     */
    public String defaultReply(String input) {
        return reply("Sorry, I'm not sure what \"" + input + "\" means...\n" +
                """
                Try using the following keywords:
                \t"todo *task*"
                \t"deadline *task* /by *date*"
                \t"event *task* /from *date* /to *date*"
                \t"list"
                \t"mark *number*"
                \t"unmark *number*"
                \t"find *keyword*"
                """
        );
    }

    /**
     * Prints the error message onto the terminal, including custom messages.
     *
     * @param e The exception thrown and caught.
     */
    public String errorReply(Exception e) {
        String message = e.getMessage();
        if (e instanceof IndexOutOfBoundsException) {
            message = "Task does not exist.";
        } else if (e instanceof NumberFormatException) {
            message = "Invalid task index.";
        } else if (e instanceof DateTimeParseException) {
            message = "Wrong formatting of date. Use (yyyy-mm-dd)";
        }
        return "\tOops: " + message + "\n";
    }

    /**
     * Prints a goodbye message on the terminal.
     */
    public String exit() {
        return "______________________________\nGoodbye!";
    }
}