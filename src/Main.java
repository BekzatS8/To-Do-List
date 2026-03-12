import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "tasks.txt";

    public static void main(String[] args) {
        FileStorage storage = new FileStorage(FILE_NAME);
        TaskManager manager = new TaskManager(storage.loadTasks());

        System.out.println("Tasks loaded from: " + FILE_NAME);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;

            while (running) {
                printMenu();
                int choice = readInt(scanner, "Choose option: ");
                System.out.println();

                switch (choice) {
                    case 1:
                        showAllTasks(manager);
                        break;
                    case 2:
                        addTask(scanner, manager, storage);
                        break;
                    case 3:
                        changeTaskStatus(scanner, manager, storage);
                        break;
                    case 4:
                        deleteTask(scanner, manager, storage);
                        break;
                    case 5:
                        storage.saveTasks(manager.getTasksForSave());
                        System.out.println("Tasks saved.");
                        break;
                    case 0:
                        storage.saveTasks(manager.getTasksForSave());
                        System.out.println("Tasks saved. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid menu option. Please try again.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("==============================");
        System.out.println("       TO-DO LIST MENU");
        System.out.println("==============================");
        System.out.println("1. Show all tasks");
        System.out.println("2. Add task");
        System.out.println("3. Change task status");
        System.out.println("4. Delete task");
        System.out.println("5. Save tasks");
        System.out.println("0. Exit");
    }

    private static void showAllTasks(TaskManager manager) {
        List<Task> tasks = manager.getAllTasks();
        if (tasks.isEmpty()) {
            System.out.println("Task list is empty.");
            return;
        }

        System.out.println("ID | Title | Status");
        System.out.println("--------------------");
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

    private static void addTask(Scanner scanner, TaskManager manager, FileStorage storage) {
        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Task title cannot be empty.");
            return;
        }

        Task createdTask = manager.addTask(title);
        storage.saveTasks(manager.getTasksForSave());
        System.out.println("Task added: " + createdTask);
    }

    private static void changeTaskStatus(Scanner scanner, TaskManager manager, FileStorage storage) {
        int id = readInt(scanner, "Enter task id: ");

        System.out.println("Available statuses: NEW, IN_PROGRESS, DONE");
        System.out.print("Enter new status: ");
        String statusText = scanner.nextLine().trim();

        TaskStatus newStatus;
        try {
            newStatus = TaskStatus.parse(statusText);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid status. Nothing changed.");
            return;
        }

        boolean updated = manager.updateTaskStatus(id, newStatus);
        if (updated) {
            storage.saveTasks(manager.getTasksForSave());
            System.out.println("Task status updated.");
        } else {
            System.out.println("Task with this id was not found.");
        }
    }

    private static void deleteTask(Scanner scanner, TaskManager manager, FileStorage storage) {
        int id = readInt(scanner, "Enter task id to delete: ");

        boolean deleted = manager.deleteTask(id);
        if (deleted) {
            storage.saveTasks(manager.getTasksForSave());
            System.out.println("Task deleted.");
        } else {
            System.out.println("Task with this id was not found.");
        }
    }

    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String value = scanner.nextLine().trim();

            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number. Please enter an integer.");
            }
        }
    }
}
