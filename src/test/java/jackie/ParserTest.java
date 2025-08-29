package jackie;

import jackie.command.*;

import jackie.task.Task;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ParserTest {

    @Nested
    class invalidInputTests {

        @Test
        public void invalidKeywordTest() {
            Parser p = new Parser();
            String input = "hello";
            Command expected = new Command(input);
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }

        @Test
        public void emptyNameTest() {
            Parser p = new Parser();
            String input = "todo ";
            Command expected = new ErrorCommand(
                    new JackieExceptions.InvalidInputException("")
            );
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }

        @Test
        public void noByKeywordTest() {
            Parser p = new Parser();
            String input = "deadline task 1";
            Command expected = new ErrorCommand(
                    new JackieExceptions.InvalidInputException("")
            );
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }

        @Test
        public void missingDateTest() {
            Parser p = new Parser();
            String input = "deadline task 1 /by   ";
            Command expected = new ErrorCommand(
                    new JackieExceptions.InvalidInputException("")
            );
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }

        @Test
        public void missingIndexTest() {
            Parser p = new Parser();
            String input = "mark ";
            Command expected = new ErrorCommand(
                    new JackieExceptions.InvalidInputException("")
            );
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }

        @Test
        public void invalidIndexTest() {
            Parser p = new Parser();
            String input = "mark task";
            Command expected = new ErrorCommand(
                    new JackieExceptions.InvalidInputException("")
            );
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }
    }

    @Nested
    class validInputTests {

        @Test
        public void byeTest() {
            Parser p = new Parser();
            String input = "bye";
            Command expected = new ExitCommand();
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }

        @Test
        public void listTest() {
            Parser p = new Parser();
            String input = "list";
            Command expected = new ListCommand();
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }

        @Test
        public void newTaskTest() {
            Parser p = new Parser();
            String input = "todo Task 1";
            Command expected = new NewTaskCommand(new Task(""));
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }

        @Test
        public void markTest() {
            Parser p = new Parser();
            String input = "mark 1";
            Command expected = new MarkCommand(true, 0);
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }

        @Test
        public void deleteTest() {
            Parser p = new Parser();
            String input = "delete 1";
            Command expected = new DeleteCommand(0);
            assertEquals(expected.getClass(), p.parse(input).getClass());
        }
    }
}
