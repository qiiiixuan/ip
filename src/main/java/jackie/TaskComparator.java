package jackie;

import jackie.task.Deadline;
import jackie.task.Event;
import jackie.task.Task;
import jackie.task.Todo;

import java.time.LocalDate;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        if (o1.getClass() == o2.getClass()) {
            if (o1.getClass() == Deadline.class) {
                return compareDeadline((Deadline) o1, (Deadline) o2);
            } else if (o1.getClass() == Event.class) {
                return compareEvent((Event) o1, (Event) o2);
            } else {
                return 0;
            }
        } else {
            return compareTask(o1, o2);
        }
    }

    private int compareTask(Task o1, Task o2) {
        if (o1.getClass() == Todo.class ||
                (o1.getClass() == Deadline.class && o2.getClass() == Event.class)
        ) {
            return -1;
        } else if (o1.getClass() == Event.class ||
                (o1.getClass() == Deadline.class && o2.getClass() == Todo.class)
        ) {
            return 1;
        } else {
            return 0;
        }
    }

    private int compareDeadline(Deadline o1, Deadline o2) {
        LocalDate by1 = o1.getBy();
        LocalDate by2 = o2.getBy();
        return by1.compareTo(by2);
    }

    private int compareEvent(Event o1, Event o2) {
        LocalDate from1 = o1.getFrom();
        LocalDate from2 = o2.getFrom();
        LocalDate to1 = o1.getTo();
        LocalDate to2 = o2.getTo();
        if (from1.isEqual(from2)) {
            return to1.compareTo(to2);
        } else {
            return from1.compareTo(from2);
        }
    }
}
