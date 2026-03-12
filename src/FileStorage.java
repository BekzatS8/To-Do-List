import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class FileStorage {
    private final File file;

    public FileStorage(String path) {
        this.file = new File(path);
    }

    public List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();

        if (!file.exists()) {
            return tasks;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseLine(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read tasks file: " + e.getMessage());
        }

        return tasks;
    }

    public void saveTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8))) {
            for (Task task : tasks) {
                writer.write(toLine(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Could not save tasks file: " + e.getMessage());
        }
    }

    private String toLine(Task task) {
        String title = task.getTitle().replace("|", "/").trim();
        return task.getId() + "|" + title + "|" + task.getStatus().name();
    }

    private Task parseLine(String line) {
        if (line.trim().isEmpty()) {
            return null;
        }

        String[] parts = line.split("\\|", 3);
        if (parts.length != 3) {
            System.out.println("Skipped invalid line in tasks file.");
            return null;
        }

        try {
            int id = Integer.parseInt(parts[0].trim());
            String title = parts[1].trim();
            TaskStatus status = TaskStatus.parse(parts[2].trim());
            return new Task(id, title, status);
        } catch (Exception e) {
            System.out.println("Skipped invalid line in tasks file.");
            return null;
        }
    }
}
