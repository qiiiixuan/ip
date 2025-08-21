import java.awt.*;
import java.util.*;
import java.util.List;

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
                index = Integer.parseInt(input.substring(5)) - 1;
                task = history.get(index);
                task.markAsDone();
                System.out.println("\tGood work! I have marked this task as done:");
                System.out.println("\t\t" + task + "\n");

            } else if (input.startsWith("unmark ")) {
                index = Integer.parseInt(input.substring(7)) - 1;
                task = history.get(index);
                task.markAsNotDone();
                System.out.println("\tI have marked this task as not done:");
                System.out.println("\t\t" + task + "\n");

            } else if (input.startsWith("todo ")){
                task = new Todo(input.substring(5));
                history.add(task);
                System.out.println("\tNew task added:");
                System.out.println("\t\t" + task);
                System.out.println("\tYou have " + history.size() + " tasks in the list.\n");

            } else if (input.startsWith("deadline ") && input.contains(" /by ")){
                additional = input.substring(input.indexOf("/") + 4);
                task = new Deadline(
                        input.substring(9, input.indexOf("/") - 1),
                        additional
                );
                history.add(task);
                System.out.println("\tNew task added:");
                System.out.println("\t\t" + task);
                System.out.println("\tYou have " + history.size() + " tasks in the list.\n");

            } else if (input.startsWith("event ") && input.contains(" /from ") && input.contains(" /to ")){
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

            } else {
                history.add(new Task(input));
                System.out.println("\tadded: " + input + "\n");
            }
            input = sc.nextLine();
        }

        System.out.println("______________________________\nGoodbye!");
    }
}
