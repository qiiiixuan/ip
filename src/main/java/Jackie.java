import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Jackie {
    public static void main(String[] args) {
        String logo = """
                 _____           _
                |__ __|__   ___ | | __ *  ___
                  | |/ _  |/ ,_\\| |/ /| |/ _ \\
                ,_| | |_| |  |_ |   < | |  __/
                \\___/\\__,_|\\___/|_|\\_\\|_|\\___/
                """;
        System.out.println("Welcome! I am...\n" + logo + "______________________________");

        // Initialize values
        String path = "./data/Tasks.txt";
        File file = new File(path);
        Scanner inputSc = new Scanner(System.in);
        ArrayList<Task> history = new ArrayList<>();
        String input = "";
        String additional = "";
        Task task = null;
        int index = 0;

        // Initialize history from hard disk
        try {
            history = readFile(path);
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile(); // output not required
            } catch (IOException ex) {
                try {
                    Files.createDirectory(Path.of("./data"));
                    file.createNewFile(); // output not required
                } catch (IOException exc) {
                    System.out.println("\tERROR: " + exc.getMessage() + "\n");
                }
            }
        } catch (JackieExceptions.InvalidInputException e) {
            System.out.println("\tERROR: " + e.getMessage() + "\n");
        }

        // Await user input
        System.out.println("Add tasks to your list!\n");
        input = inputSc.nextLine();

        // Loop until 'bye' keyword
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
                    writeToFile(path, history);
                    System.out.println("\tGood work! I have marked this task as done:");
                    System.out.println("\t\t" + task + "\n");
                } catch (JackieExceptions.InvalidInputException | IOException e) {
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
                    writeToFile(path, history);
                    System.out.println("\tI have marked this task as not done:");
                    System.out.println("\t\t" + task + "\n");
                } catch (JackieExceptions.InvalidInputException | IOException e) {
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
                    writeToFile(path, history);
                    System.out.println("\tNew task added:");
                    System.out.println("\t\t" + task);
                    System.out.println("\tYou have " + history.size() + " tasks in the list.\n");
                } catch (JackieExceptions.InvalidInputException | IOException e) {
                    System.out.println("\tERROR: " + e.getMessage() + "\n");
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
                            additional
                    );
                    history.add(task);
                    writeToFile(path, history);
                    System.out.println("\tNew task added:");
                    System.out.println("\t\t" + task);
                    System.out.println("\tYou have " + history.size() + " tasks in the list.\n");
                } catch (JackieExceptions.InvalidInputException | IOException e) {
                    System.out.println("\tERROR: " + e.getMessage() + "\n");
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
                            additional.substring(0, additional.indexOf(" /to ")),
                            additional.substring(additional.indexOf("/to ") + 4)
                    );
                    history.add(task);
                    writeToFile(path, history);
                    System.out.println("\tNew task added:");
                    System.out.println("\t\t" + task);
                    System.out.println("\tYou have " + history.size() + " tasks in the list.\n");
                } catch (JackieExceptions.InvalidInputException | IOException e) {
                    System.out.println("\tERROR: " + e.getMessage() + "\n");
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
                    writeToFile(path, history);
                    System.out.println("\tI have removed this task from the list:");
                    System.out.println("\t\t" + task);
                    System.out.println("\tYou have " + history.size() + " tasks in the list.\n");
                } catch (JackieExceptions.InvalidInputException | IOException e) {
                    System.out.println("\tERROR: " + e.getMessage() + "\n");
                } catch (NumberFormatException e) {
                    System.out.println("\tERROR: Invalid task index.\n");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("\tERROR: Task does not exist.\n");
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
            input = inputSc.nextLine();
        }

        System.out.println("______________________________\nGoodbye!");
    }



    // Return list of tasks to history
    private static ArrayList<Task> readFile(String path) throws
            FileNotFoundException,
            JackieExceptions.InvalidInputException {
        ArrayList<Task> list = new ArrayList<>();
        char type = ' ';
        String line = "";
        int counter = 0;
        File file = new File(path);
        Scanner fileSc = new Scanner(file);

        try {
            while (fileSc.hasNextLine()) {
                counter++;
                line = fileSc.nextLine();
                type = line.charAt(3);

                if (type == 'T') {
                    list.add(new Todo(line.substring(9)));

                } else if (type == 'D') {
                    if (!line.contains(" (by: ")) {
                        throw new JackieExceptions.InvalidInputException(
                                "Wrong formatting in ./data/Tasks.txt at line " + counter
                        );
                    }
                    list.add(new Deadline(
                            line.substring(9, line.lastIndexOf(" (by: ")),
                            line.substring(line.lastIndexOf(" (by: ") + 6, line.lastIndexOf(')'))
                    ));

                } else if (type == 'E') {
                    if (!line.contains(" (from: ") || !line.contains(" to: ")) {
                        throw new JackieExceptions.InvalidInputException(
                                "Wrong formatting in ./data/Tasks.txt at line " + counter
                        );
                    }
                    list.add(new Event(
                            line.substring(9, line.lastIndexOf(" (from: ")),
                            line.substring(line.lastIndexOf(" (from: ") + 8, line.lastIndexOf(" to: ")),
                            line.substring(line.lastIndexOf(" to: ") + 5, line.lastIndexOf(')'))
                    ));
                }

                if (line.charAt(6) == 'X') {
                    list.get(list.size() - 1).markAsDone();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new JackieExceptions.InvalidInputException(
                    "Wrong formatting in ./data/Tasks.txt at line " + counter
            );
        }
        return list;
    }



    // Update text file of tasks
    private static void writeToFile(String path, ArrayList<Task> history) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (int i = 0; i < history.size(); i++) {
            fw.write((i + 1) + "." + history.get(i) + System.lineSeparator());
        }
        fw.close();
    }
}