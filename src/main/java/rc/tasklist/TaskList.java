package rc.tasklist;

import java.util.ArrayList;

import rc.exception.DukeException;
import rc.ui.UI;
import rc.task.Task;

/**
 * Manages a list of tasks and provides functionality such as adding,
 * marking, unmarking, deleting, and displaying tasks.
 * <p>
 * Also validate task indices and extract task numbers from user input.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs a new `TaskList` object.
     * Initializes an empty list of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Returns the list of tasks.
     *
     * @return tasks
     */
    public ArrayList<Task> getTaskList() {
        return tasks;
    }

    /**
     * Adds a task to the task list and displays a confirmation prompt.
     *
     * @param task of Task.
     */
    public static void addTask(Task task) {
        // add task into arrayList
        tasks.add(task);
        // print added task
        UI.showAddedTask(task);
    }

    /**
     * Displays the total number of tasks in the list.
     */
    public static void displayNumOfTasks() {
        UI.print("You have " + tasks.size() + " task(s) in the list");
    }

    /**
     * Displays all tasks in the list with their corresponding indices.
     */
    public static void printAllTasks() {
        UI.print("Here are the tasks in your list:");

        int index = 0;
        for (Task task: tasks) {
            UI.showIndexedTask(index, task);
            index++;
        }
    }

    /**
     * Marks a task as done based on the provided task number.
     *
     * @param line The user input containing the task number to mark.
     * @throws DukeException If the task number is invalid or out of range.
     */
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
            UI.showIndexedTask(markIndex, markedTask);
        } catch (NumberFormatException error) {
            throw new DukeException("Invalid mark format. Use: mark <task_number>");
        }
    }

    /**
     * Marks a task as not done based on the provided task number.
     *
     * @param line The user input containing the task number to unmark.
     * @throws DukeException If the task number is invalid or out of range.
     */
    public static void unmarkTask(String line) throws DukeException {
        try {
            int unmarkIndex = extractIndex(line);
            validateIndex(unmarkIndex);

            Task unmarkTask = tasks.get(unmarkIndex);
            // use unmarkIndex to mark task from Task[] as not done
            unmarkTask.markAsNotDone();

            UI.print("Noted, I've marked this task as not done yet:");
            // display marked task
            UI.showIndexedTask(unmarkIndex, unmarkTask);
        } catch (NumberFormatException error) {
            throw new DukeException("Use: unmark <task_number>");
        }
    }

    /**
     * Deletes a task from the list based on the provided task number.
     *
     * @param line The user input containing the task number to delete.
     * @throws DukeException If the task number is invalid or out of range.
     */
    public static void deleteTask(String line) throws DukeException {
        try {
            int deleteIndex = extractIndex(line);
            validateIndex(deleteIndex);

            Task deletedTask = tasks.get(deleteIndex);
            // display task before deletion
            UI.showDeletedTask(deletedTask);

            // remove task from arrayList
            tasks.remove(deletedTask);
            // display number of tasks left
            displayNumOfTasks();
        } catch (NumberFormatException error) {
            throw new DukeException("Use: delete <task_number>");
        }
    }

    /**
     * Extracts the task index from the user input.
     *
     * @param line The user input containing the task number.
     * @return The extracted task index (adjusted for zero-based indexing).
     */
    private static int extractIndex(String line) {
        return Integer.parseInt(line.replaceAll("[^0-9]",
                "")) - UI.getOffset();
    }

    /**
     * Validates the task index to ensure it is within the bounds of
     * the task list.
     *
     * @param index The task index to validate.
     * @throws DukeException If the index is out of range.
     */
    private static void validateIndex(int index) throws DukeException {
        if (index < 0 || index >= tasks.size()) {
            throw new DukeException("Invalid or unavailable task number.");
        }
    }
}
