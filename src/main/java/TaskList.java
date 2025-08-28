import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.Scanner;

public class TaskList {
    protected ArrayList<Task> taskList;

    public TaskList(String path) {
        File file = new File(path);
        taskList = new ArrayList<>();
        try {
            taskList = readFile(path);
        } catch (FileNotFoundException e) {
            try {
                file.createNewFile(); // output not required
            } catch (IOException ex) {
                try {
                    Files.createDirectory(Path.of(path.substring(0, path.indexOf("/", 3))));
                    file.createNewFile(); // output not required
                } catch (IOException exc) {
                    System.out.println("\nERROR LOADING FILE: " + exc.getMessage() + "\n");
                }
            }
        } catch (JackieExceptions.InvalidInputException e) {
            System.out.println("\nERROR LOADING FILE: " + e.getMessage() + "\n");
        }
    }

    public String toString() {
        String str = "These are your tasks:\n";
        Task task;
        for (int i = 0; i < this.taskList.size(); i++) {
            task = this.taskList.get(i);
            str = str.concat((i + 1) + "." + task + "\n");
        }
        return str;
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public int size() {
        return this.taskList.size();
    }

    public void writeToFile(String path) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (int i = 0; i < this.taskList.size(); i++) {
            fw.write((i + 1) + "." + this.taskList.get(i) + System.lineSeparator());
        }
        fw.close();
    }

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
                            LocalDate.parse(line.substring(
                                    line.lastIndexOf(" (by: ") + 6,
                                    line.lastIndexOf(')')
                            ), DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    ));

                } else if (type == 'E') {
                    if (!line.contains(" (from: ") || !line.contains(" to: ")) {
                        throw new JackieExceptions.InvalidInputException(
                                "Wrong formatting in ./data/Tasks.txt at line " + counter
                        );
                    }
                    list.add(new Event(
                            line.substring(9, line.lastIndexOf(" (from: ")),
                            LocalDate.parse(line.substring(
                                    line.lastIndexOf(" (from: ") + 8,
                                    line.lastIndexOf(" to: ")
                            ), DateTimeFormatter.ofPattern("dd MMM yyyy")),
                            LocalDate.parse(line.substring(
                                    line.lastIndexOf(" to: ") + 5,
                                    line.lastIndexOf(')')
                            ))
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
        } catch (DateTimeParseException e) {
            throw new JackieExceptions.InvalidInputException(
                    "Wrong date formatting in ./data/Tasks.txt at line " + counter
            );
        }
        return list;
    }
}