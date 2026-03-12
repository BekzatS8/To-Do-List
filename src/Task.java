public class Task {
    private final int id;
    private final String title;
    private TaskStatus status;

    public Task(int id, String title, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return id + " | " + title + " | " + status;
    }
}
