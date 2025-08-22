import java.util.*;

public class Jackie {
    public static void main(String[] args) throws InterruptedException {
        String logo = """
                 _____           _
                |__ __|__   ___ | | __ *  ___
                  | |/ _  |/ ,_\\| |/ /| |/ _ \\
                ,_| | |_| |  |_ |   < | |  __/
                \\___/\\__,_|\\___/|_|\\_\\|_|\\___/
                """;

        Scanner sc = new Scanner(System.in);
        ArrayList<Task> history = new ArrayList<>(List.of());
        String input;
        String additional;
        Task task;
        int index;

        System.out.println("Welcome! I am...\n" + logo + "______________________________");
        System.out.println("Add tasks to your list!\n");
        input = sc.nextLine();

        while(!Objects.equals(input, "bye")) {
            if (Objects.equals(input, "list")) {
                System.out.println("\tThese are your tasks:");
                for (index = 0; index < history.size(); index++) {
                    task = history.get(index);
                    System.out.println("\t" + (index + 1) + "." + task);
                }
                System.out.println();

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
                    System.out.println("\tGood work! I have marked this task as done:");
                    System.out.println("\t\t" + task + "\n");
                } catch (JackieExceptions.InvalidInputException e) {
                    System.out.println("\tERROR: " + e.getMessage() + "\n");
                } catch (NumberFormatException e) {
                    System.out.println("\tERROR: Invalid task index.\n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tERROR: Task does not exist.\n");
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
                    System.out.println("\tI have marked this task as not done:");
                    System.out.println("\t\t" + task + "\n");
                } catch (JackieExceptions.InvalidInputException e) {
                    System.out.println("\tERROR: " + e.getMessage() + "\n");
                } catch (NumberFormatException e) {
                    System.out.println("\tERROR: Invalid task index.\n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tERROR: Task does not exist.\n");
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
                    System.out.println("\tNew task added:");
                    System.out.println("\t\t" + task);
                    System.out.println("\tYou have " + history.size() + " tasks in the list.\n");
                } catch (JackieExceptions.InvalidInputException e) {
                    System.out.println("\tERROR: " + e.getMessage() + "\n");
                }

            } else if (input.startsWith("deadline ")){
                try {
                    if (!input.contains(" /by ")) {
                        throw new JackieExceptions.InvalidInputException(
                                "Deadline task must have due date. Use the /by keyword."
                        );
                    } else if (input.substring(0, input.indexOf("/") - 1).strip().length() < 10) {
                        throw new JackieExceptions.InvalidInputException(
                                "Deadline task cannot be blank. Do not use \"/\" other than as a keyword."
                        );
                    }
                    additional = input.substring(input.indexOf("/") + 4);
                    task = new Deadline(
                            input.substring(9, input.indexOf("/") - 1),
                            additional
                    );
                    history.add(task);
                    System.out.println("\tNew task added:");
                    System.out.println("\t\t" + task);
                    System.out.println("\tYou have " + history.size() + " tasks in the list.\n");
                } catch (JackieExceptions.InvalidInputException e) {
                    System.out.println("\tERROR: " + e.getMessage() + "\n");
                }

            } else if (input.startsWith("event ")){
                try {
                    if (!input.contains(" /from ") || !input.contains(" /to ")) {
                        throw new JackieExceptions.InvalidInputException(
                                "Event task must have from and to date. Use the /from and /to keywords."
                        );
                    } else if (input.substring(0, input.indexOf("/") - 1).strip().length() < 7) {
                        throw new JackieExceptions.InvalidInputException(
                                "Event task cannot be blank. Do not use \"/\" other than as a keyword."
                        );
                    }
                    additional = input.substring(input.indexOf("/") + 6);
                    task = new Event(
                            input.substring(6, input.indexOf("/") - 1),
                            additional.substring(0, additional.indexOf("/") - 1),
                            additional.substring(additional.indexOf("/") + 4)
                    );
                    history.add(task);
                    System.out.println("\tNew task added:");
                    System.out.println("\t\t" + task);
                    System.out.println("\tYou have " + history.size() + " tasks in the list.\n");
                } catch (JackieExceptions.InvalidInputException e) {
                    System.out.println("\tERROR: " + e.getMessage() + "\n");
                }

            } else {
                System.out.println("\tERROR: Sorry, not sure what \"" + input + "\" means...");
                System.out.println(
                        """
                        \tTry using the following keywords:
                        \t"todo *task*"
                        \t"deadline *task* /by *date*"
                        \t"event *task* /from *date* /to *date*"
                        """
                );
            }
            input = sc.nextLine();
        }

        System.out.println("______________________________\nGoodbye!");
    }
}