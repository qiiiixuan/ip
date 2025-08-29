package jackie;

import jackie.task.Task;

import java.time.format.DateTimeParseException;

public class UserInterface {

    public UserInterface() {}

    public void greeting() {
        String logo = """
                 _____           _
                |__ __|__   ___ | | __ *  ___
                  | |/ _  |/ ,_\\| |/ /| |/ _ \\
                ,_| | |_| |  |_ |   < | |  __/
                \\___/\\__,_|\\___/|_|\\_\\|_|\\___/
                """;
        System.out.println("Welcome! I am...\n" + logo +
                "______________________________\nAdd tasks to your list!\n");
    }

    public void reply(String output) {
        for (String line : output.split("\n")) {
            System.out.println("\t" + line);
        }
        System.out.println();
    }

    public void markReply(Task task) {
        reply("Good work! I have marked this task as done:\n\t" +
                task + "\n"
        );
    }

    public void unmarkReply(Task task) {
        reply("I have marked this task as not done:\n\t" +
                task + "\n"
        );
    }

    public void taskReply(Task task, int size) {
        reply("New task added:\n\t" +
                task + "\nYou have " +
                size + " tasks in the list.\n"
        );
    }

    public void deleteReply(Task task, int size) {
        reply("I have removed this task from the list:\n\t" +
                task + "\nYou have " +
                size + " tasks in the list.\n"
        );
    }

    public void defaultReply(String input) {
        reply("Sorry, I'm not sure what \"" + input + "\" means...\n" +
                """
                Try using the following keywords:
                \t"todo *task*"
                \t"deadline *task* /by *date*"
                \t"event *task* /from *date* /to *date*"
                \t"list"
                \t"mark *number*"
                \t"unmark *number*"
                """
        );
    }

    public void showError(Exception e) {
        String message = e.getMessage();
        if (e instanceof IndexOutOfBoundsException) {
            message = "Task does not exist.";
        } else if (e instanceof NumberFormatException) {
            message = "Invalid task index.";
        } else if (e instanceof DateTimeParseException) {
            message = "Wrong formatting of date. Use (yyyy-mm-dd)";
        }
        System.out.println("\tOops: " + message + "\n");
    }

    public void exit() {
        System.out.println("______________________________\nGoodbye!");
    }
}