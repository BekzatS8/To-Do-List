# To-Do List (Console Java App)

## 1. Short description
This is a small console To-Do List application written in Java.
It allows a user to add tasks, view all tasks, change their status, and delete them.
Tasks are stored in a plain text file, so data is not lost after restarting the program.

## 2. Features
The program supports:
- showing all tasks;
- adding a new task;
- changing task status by id;
- deleting a task by id;
- saving tasks to file;
- loading tasks automatically on startup;
- saving tasks automatically on exit.

## 3. Project structure
```text
To-Do-List/
├── src/
│   ├── Main.java
│   ├── Task.java
│   ├── TaskStatus.java
│   ├── TaskManager.java
│   └── FileStorage.java
├── tasks.txt
└── README.md
```

## 4. How to run

Requirements:

```text
Java 8+
```

Compile:

```bash
javac src/*.java
```

Run:

```bash
java -cp src Main
```

## 5. Where data is stored

Tasks are stored in the tasks.txt file in the project root.

Storage format:

```text
id|title|status
```

Example:

```text
1|Buy groceries|NEW
2|Finish internship task|IN_PROGRESS
```

## 6. Supported statuses

The application supports three statuses:

- NEW
- IN_PROGRESS
- DONE

## 7. Possible future improvements

Possible future improvements:

- edit task title;
- filter tasks by status;
- add search by title;
- confirm deletion before removing a task;
- add simple unit tests for TaskManager and FileStorage.
