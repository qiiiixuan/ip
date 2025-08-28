public class Task {
    protected String name;
    protected Boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsNotDone() {
        this.done = false;
    }

    public String toString() {
        String status = done ? "[X] " : "[ ] ";
        return status + this.name;
    }
}
