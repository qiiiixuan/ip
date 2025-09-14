package jackie;

import jackie.task.Task;

import java.util.ArrayList;

/**
 * Contains and handles all logic relating to the {@link ArrayList} of tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns a {@link String} representation of the {@link TaskList}.
     *
     * @return A {@link String} that shows the list of tasks.
     */
    public String toString() {
        if (tasks.isEmpty()) {
            return "There are no tasks. ";
        }
        String str = "These are your tasks:\n";
        Task task;
        for (int i = 0; i < this.tasks.size(); i++) {
            task = this.tasks.get(i);
            str = str.concat((i + 1) + "." + task + "\n");
        }
        return str;
    }

    /**
     * Returns the list of tasks.
     *
     * @return The {@link ArrayList} of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return this.tasks;
    }

    /**
     * Adds a new {@link Task} to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Returns the {@link Task} in the list using its index.
     *
     * @param index The index of the task.
     * @return The task with the corresponding index.
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Deletes a {@link Task} from the list.
     *
     * @param index The index of the task to be deleted.
     */
    public void remove(int index) {
        this.tasks.remove(index);
    }

    /**
     * Returns the size of the task list.
     *
     * @return Size of task list.
     */
    public int size() {
        return this.tasks.size();
    }

    public void sort() {
        tasks.sort(new TaskComparator());
    }
}