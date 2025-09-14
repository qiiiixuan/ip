package jackie;

import jackie.task.Deadline;
import jackie.task.Event;
import jackie.task.Task;
import jackie.task.Todo;

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


/**
 * A link to a specific file on the hard drive.
 * Handles the logic of retrieving data from and writing data to the file.
 */
public class Database {

    private final String path;

    public Database(String path) {
        this.path = path;
    }

    /**
     * Loads file and returns an {@link ArrayList} of {@link Task}s read from the file.
     * If file or directory does not exist, a new one will be created at the specified path.
     *
     * @return A list of tasks stored in the hard drive if the formatting is right,
     * an empty {@link ArrayList} otherwise.
     */
    public ArrayList<Task> loadFile() {
        File file = new File(path);
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            tasks = readFile(path);
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
        return tasks;
    }

    /**
     * Writes tasks into the file in the supported format.
     *
     * @param tasks A {@link TaskList} with {@link Task}s to be written.
     * @throws IOException if the named file exists but is a directory rather than a regular file,
     * does not exist but cannot be created, or cannot be opened for any other reason
     */
    public void writeToFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(path);
        for (int i = 0; i < tasks.size(); i++) {
            fw.write((i + 1) + "." + tasks.get(i) + System.lineSeparator());
        }
        fw.close();
    }

    /**
     * Reads the file and returns an {@link ArrayList} of {@link Task} read from that file.
     *
     * @param path A {@link String} that indicates the path of the file.
     * @return A list of tasks stored in the hard drive.
     * @throws FileNotFoundException if source is not found
     * @throws JackieExceptions.InvalidInputException if tasks in the file is in the wrong format
     */
    private static ArrayList<Task> readFile(String path) throws
            FileNotFoundException,
            JackieExceptions.InvalidInputException {
        ArrayList<Task> list = new ArrayList<>();
        char taskType;
        String line;
        int lineCounter = 0;
        File file = new File(path);
        Scanner fileSc = new Scanner(file);

        try {
            while (fileSc.hasNextLine()) {
                lineCounter++;
                line = fileSc.nextLine();
                taskType = line.charAt(3);

                if (taskType == 'T') {
                    list.add(new Todo(line.substring(9)));

                } else if (taskType == 'D') {
                    if (!line.contains(" (by: ")) {
                        throw new JackieExceptions.InvalidInputException(
                                "Wrong formatting in ./data/Tasks.txt at line " + lineCounter
                        );
                    }
                    list.add(new Deadline(
                            line.substring(9, line.lastIndexOf(" (by: ")),
                            LocalDate.parse(line.substring(
                                    line.lastIndexOf(" (by: ") + 6,
                                    line.lastIndexOf(')')
                            ), DateTimeFormatter.ofPattern("dd MMM yyyy"))
                    ));

                } else if (taskType == 'E') {
                    if (!line.contains(" (from: ") || !line.contains(" to: ")) {
                        throw new JackieExceptions.InvalidInputException(
                                "Wrong formatting in ./data/Tasks.txt at line " + lineCounter
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
                } else {
                    throw new JackieExceptions.InvalidInputException(
                            "Wrong formatting in ./data/Tasks.txt at line " + lineCounter
                    );
                }

                if (line.charAt(6) == 'X') {
                    list.get(list.size() - 1).markAsDone();
                }
            }
        } catch (IndexOutOfBoundsException e) {
            throw new JackieExceptions.InvalidInputException(
                    "Wrong formatting in ./data/Tasks.txt at line " + lineCounter
            );
        } catch (DateTimeParseException e) {
            throw new JackieExceptions.InvalidInputException(
                    "Wrong date formatting in ./data/Tasks.txt at line " + lineCounter
            );
        } finally {
            fileSc.close();
        }
        return list;
    }
}