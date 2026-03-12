import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    private int nextId;

    public TaskManager(List<Task> loadedTasks) {
        tasks = new ArrayList<>(loadedTasks);
        nextId = findNextId(tasks);
    }

    public Task addTask(String title) {
        Task task = new Task(nextId, title, TaskStatus.NEW);
        tasks.add(task);
        nextId++;
        return task;
    }

    public List<Task> getAllTasks() {
        List<Task> result = new ArrayList<>(tasks);
        Collections.sort(result, Comparator.comparingInt(Task::getId));
        return result;
    }

    public boolean updateTaskStatus(int id, TaskStatus status) {
        Task task = findTaskById(id);
        if (task == null) {
            return false;
        }

        task.setStatus(status);
        return true;
    }

    public boolean deleteTask(int id) {
        Task task = findTaskById(id);
        if (task == null) {
            return false;
        }

        tasks.remove(task);
        return true;
    }

    public List<Task> getTasksForSave() {
        return new ArrayList<>(tasks);
    }

    private Task findTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    private int findNextId(List<Task> tasks) {
        int maxId = 0;
        for (Task task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        return maxId + 1;
    }
}
