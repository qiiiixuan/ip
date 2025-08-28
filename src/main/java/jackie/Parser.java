package jackie;

import jackie.command.*;

import jackie.task.Deadline;
import jackie.task.Event;
import jackie.task.Task;
import jackie.task.Todo;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class Parser {

    boolean notBye;

    public Parser() {
        this.notBye = true;
    }

    public Command parse(String input) {
        Command command;
        try {
            if (Objects.equals(input, "bye")) {
                this.notBye = false;
                command = new ExitCommand();

            } else if (Objects.equals(input, "list")) {
                command = new ListCommand();

            } else if (input.startsWith("mark ") || input.startsWith("unmark ")) {
                if (input.strip().length() < 6) {
                    throw new JackieExceptions.InvalidInputException(
                            "Task index missing."
                    );
                }
                if (input.startsWith("mark ")) {
                    command = new MarkCommand(true, Integer.parseInt(input.substring(5)) - 1);
                } else {
                    command = new MarkCommand(false, Integer.parseInt(input.substring(7)) - 1);
                }

            } else if (input.startsWith("todo ") || input.startsWith("deadline ") || input.startsWith("event ")) {
                command = new NewTaskCommand(getTask(input));

            } else if (input.startsWith("delete ")) {
                if (input.strip().length() < 8) {
                    throw new JackieExceptions.InvalidInputException(
                            "Task index missing."
                    );
                }
                command = new DeleteCommand(Integer.parseInt(input.substring(7)) - 1);

            } else {
                command = new Command(input);
            }
        } catch (JackieExceptions.InvalidInputException |
                 NumberFormatException |
                 IndexOutOfBoundsException |
                 DateTimeParseException e) {
            command = new ErrorCommand(e);
        }
        return command;
    }

    // Custom method to get task from input
    private static Task getTask(String input) throws JackieExceptions.InvalidInputException {
        Task task = null;
        if (input.startsWith("todo ")) {
            if (input.strip().length() < 6) {
                throw new JackieExceptions.InvalidInputException(
                        "Todo task cannot be blank."
                );
            }

            task = new Todo(input.substring(5));

        } else if (input.startsWith("deadline ")) {
            if (!input.contains(" /by ")) {
                throw new JackieExceptions.InvalidInputException(
                        "Deadline task must have due date. Use the /by keyword."
                );
            } else if (input.substring(0, input.indexOf(" /by ")).strip().length() < 10) {
                throw new JackieExceptions.InvalidInputException(
                        "Deadline task cannot be blank."
                );
            }

            String additional = input.substring(input.indexOf(" /by ") + 5);
            task = new Deadline(
                    input.substring(9, input.indexOf(" /by ")),
                    LocalDate.parse(additional)
            );

        } else if (input.startsWith("event ")) {
            if (!input.contains(" /from ") || !input.contains(" /to ")) {
                throw new JackieExceptions.InvalidInputException(
                        "Event task must have from and to date. Use the /from and /to keywords."
                );
            } else if (input.substring(0, input.indexOf(" /from ")).strip().length() < 7) {
                throw new JackieExceptions.InvalidInputException(
                        "Event task cannot be blank."
                );
            }

            String additional = input.substring(input.indexOf("/from ") + 6);
            task = new Event(
                    input.substring(6, input.indexOf(" /from ")),
                    LocalDate.parse(additional.substring(0, additional.indexOf(" /to "))),
                    LocalDate.parse(additional.substring(additional.indexOf("/to ") + 4))
            );
        }
        return task;
    }
}