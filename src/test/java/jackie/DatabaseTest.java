package jackie;

import jackie.task.Task;
import jackie.task.Todo;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DatabaseTest {

    @TempDir
    Path tempDir;

    @Nested
    class loadFileTests {

        @Test
        public void loadFileTest() throws IOException {
            Path tempFile = tempDir.resolve("Test1.txt");
            String path = tempFile.toString();
            Database db = new Database(path);

            Files.writeString(tempFile, "1.[T][ ] task 1");
            ArrayList<Task> tasks = db.loadFile();

            ArrayList<Task> expected = new ArrayList<>();
            expected.add(new Todo("task 1"));

            assertEquals(expected.get(0).toString(), tasks.get(0).toString());
        }

        @Test
        public void wrongFormatTest() throws IOException {
            Path tempFile = tempDir.resolve("Test2.txt");
            String path = tempFile.toString();
            Database db = new Database(path);

            Files.writeString(tempFile, "1.[ ][ ] task 1");
            ArrayList<Task> tasks = db.loadFile();

            assertEquals(0, tasks.size());
        }

        @Test
        public void wrongDateFormatTest() throws IOException {
            Path tempFile = tempDir.resolve("Test3.txt");
            String path = tempFile.toString();
            Database db = new Database(path);

            Files.writeString(tempFile, "1.[D][ ] task 1 /by December");
            ArrayList<Task> tasks = db.loadFile();

            assertEquals(0, tasks.size());
        }
    }

    @Test
    public void writeToFileTest() throws IOException {
        Path tempFile = tempDir.resolve("Test4.txt");
        String path = tempFile.toString();
        Database db = new Database(path);
        TaskList tasks = new TaskList(new ArrayList<>());
        tasks.add(new Todo("task 1"));
        db.writeToFile(tasks);
        assertEquals("1.[T][ ] task 1", Files.readString(tempFile).strip());
    }
}
