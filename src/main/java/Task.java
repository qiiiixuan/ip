public class Task {
    protected String name;
    protected Boolean done;

    public Task (String name) {
        this.name = name;
        this.done = false;
    }

    public String status () {
        return done ? "[X]" : "[ ]";
    }

    public String getName () {
        return this.name;
    }

    public void markAsDone () {
        this.done = true;
    }

    public void markAsNotDone () {
        this.done = false;
    }
}
