import java.io.IOException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import java.util.Objects;
import java.util.Scanner;

public class Jackie {
    public static void main(String[] args) {

        UserInterface UI = new UserInterface();
        UI.greeting();

        // Initialize values
        String path = "./data/Tasks.txt";
        Scanner inputSc = new Scanner(System.in);
        TaskList history = new TaskList(path);
        String input = "";
        String additional = "";
        Task task = null;
        int index = 0;

        // Await user input
        UI.reply("Add tasks to your list!\n");
        input = inputSc.nextLine();

        // Loop until 'bye' keyword
        while(!Objects.equals(input, "bye")) {
            if (Objects.equals(input, "list")) {
                UI.reply(history.toString());

            } else if (input.startsWith("mark ")) {
                try {
                    if (input.strip().length() < 6) {
                        throw new JackieExceptions.InvalidInputException(
                                "Task index missing."
                        );
                    }
                    index = Integer.parseInt(input.substring(5)) - 1;
                    task = history.get(index);
                    task.markAsDone();
                    history.writeToFile(path);
                    UI.markReply(task);
                } catch (JackieExceptions.InvalidInputException |
                         IOException |
                         NumberFormatException |
                         IndexOutOfBoundsException e) {
                    UI.showError(e);
                }

            } else if (input.startsWith("unmark ")) {
                try {
                    if (input.strip().length() < 8) {
                        throw new JackieExceptions.InvalidInputException(
                                "Task index missing."
                        );
                    }
                    index = Integer.parseInt(input.substring(7)) - 1;
                    task = history.get(index);
                    task.markAsNotDone();
                    history.writeToFile(path);
                    UI.unmarkReply(task);
                } catch (JackieExceptions.InvalidInputException |
                         IOException |
                         NumberFormatException |
                         IndexOutOfBoundsException e) {
                    UI.showError(e);
                }

            } else if (input.startsWith("todo ")){
                try {
                    if (input.strip().length() < 6) {
                        throw new JackieExceptions.InvalidInputException(
                                "Todo task cannot be blank."
                        );
                    }
                    task = new Todo(input.substring(5));
                    history.add(task);
                    history.writeToFile(path);
                    UI.taskReply(task, history.size());
                } catch (JackieExceptions.InvalidInputException | IOException e) {
                    UI.showError(e);
                }

            } else if (input.startsWith("deadline ")){
                try {
                    if (!input.contains(" /by ")) {
                        throw new JackieExceptions.InvalidInputException(
                                "Deadline task must have due date. Use the /by keyword."
                        );
                    } else if (input.substring(0, input.indexOf(" /by ")).strip().length() < 10) {
                        throw new JackieExceptions.InvalidInputException(
                                "Deadline task cannot be blank."
                        );
                    }
                    additional = input.substring(input.indexOf(" /by ") + 5);
                    task = new Deadline(
                            input.substring(9, input.indexOf(" /by ")),
                            LocalDate.parse(additional)
                    );
                    history.add(task);
                    history.writeToFile(path);
                    UI.taskReply(task, history.size());
                } catch (JackieExceptions.InvalidInputException |
                         IOException |
                         DateTimeParseException e) {
                    UI.showError(e);
                }

            } else if (input.startsWith("event ")) {
                try {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new JackieExceptions.InvalidInputException(
                                "Event task must have from and to date. Use the /from and /to keywords."
                        );
                    } else if (input.substring(0, input.indexOf(" /from ")).strip().length() < 7) {
                        throw new JackieExceptions.InvalidInputException(
                                "Event task cannot be blank."
                        );
                    }
                    additional = input.substring(input.indexOf("/from ") + 6);
                    task = new Event(
                            input.substring(6, input.indexOf(" /from ")),
                            LocalDate.parse(additional.substring(0, additional.indexOf(" /to "))),
                            LocalDate.parse(additional.substring(additional.indexOf("/to ") + 4))
                    );
                    history.add(task);
                    history.writeToFile(path);
                    UI.taskReply(task, history.size());
                } catch (JackieExceptions.InvalidInputException |
                         IOException |
                         DateTimeParseException e) {
                    UI.showError(e);
                }

            } else if (input.startsWith("delete ")) {
                try {
                    if (input.strip().length() < 8) {
                        throw new JackieExceptions.InvalidInputException(
                                "Task index missing."
                        );
                    }
                    index = Integer.parseInt(input.substring(7)) - 1;
                    task = history.get(index);
                    history.remove(index);
                    history.writeToFile(path);
                    UI.deleteReply(task, history.size());
                } catch (JackieExceptions.InvalidInputException |
                         IOException |
                         NumberFormatException |
                         IndexOutOfBoundsException e) {
                    UI.showError(e);
                }

            } else {
                UI.defaultReply(input);
            }
            input = inputSc.nextLine();
        }

        UI.exit();
    }
}