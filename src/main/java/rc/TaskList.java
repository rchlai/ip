package rc;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import rc.task.Task;

public class TaskList {
    private static ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    public void addTask(Task t) {
        // add task into arrayList
        tasks.add(t);
        // print added task
        UI.print("This task has been added: " +  "\n" + t);
    }

    public static void displayNumOfTasks() {
        UI.print("You have " + tasks.size() + " task(s) in the list");
    }

    public static void printAllTasks() {
        UI.print("Here are the tasks in your list:");

        int index = 0;
        for (Task task: tasks) {
            UI.print((index + UI.getOffset()) + "." + task);
            index++;
        }
    }

    public static void markTask(String line) throws DukeException {
        try {
            // extract digits from string to be converted into integer type
            int markIndex = extractIndex(line);
            // checks if index is negative, greater than, or equal to taskCount
            validateIndex(markIndex);

            Task markedTask = tasks.get(markIndex);
            // use markIndex to mark task from Task[] as done
            markedTask.markAsDone();

            UI.print("Good job! I've marked this task as done:");
            // display marked task
            UI.print((markIndex + UI.getOffset()) + "." + markedTask);
        } catch (NumberFormatException error) {
            throw new DukeException("Invalid mark format. Use: mark <task_number>");
        }
    }

    public static void unmarkTask(String line) throws DukeException {
        try {
            int unmarkIndex = extractIndex(line);
            validateIndex(unmarkIndex);

            Task unmarkTask = tasks.get(unmarkIndex);
            // use unmarkIndex to mark task from Task[] as not done
            unmarkTask.markAsNotDone();

            UI.print("Noted, I've marked this task as not done yet:");
            // display marked task
            UI.print((unmarkIndex + UI.getOffset()) + "." + unmarkTask);
        } catch (NumberFormatException error) {
            throw new DukeException("Use: unmark <task_number>");
        }
    }

    public static void deleteTask(String line) throws DukeException {
        try {
            int deleteIndex = extractIndex(line);
            validateIndex(deleteIndex);

            Task deletedTask = tasks.get(deleteIndex);
            // display task before deletion
            UI.print("This task will be deleted:\n" + deletedTask);

            // remove task from arrayList
            tasks.remove(deletedTask);
            // display number of tasks left
            displayNumOfTasks();
        } catch (NumberFormatException error) {
            throw new DukeException("Use: delete <task_number>");
        }
    }

    private static int extractIndex(String line) {
        return Integer.parseInt(line.replaceAll("[^0-9]",
                "")) - UI.getOffset();
    }

    private static void validateIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid or unavailable task number.");
        }
    }


}
