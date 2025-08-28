package jackie;

import jackie.task.Task;

import java.util.ArrayList;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public String toString() {
        String str = "These are your tasks:\n";
        Task task;
        for (int i = 0; i < this.tasks.size(); i++) {
            task = this.tasks.get(i);
            str = str.concat((i + 1) + "." + task + "\n");
        }
        return str;
    }

    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }
}