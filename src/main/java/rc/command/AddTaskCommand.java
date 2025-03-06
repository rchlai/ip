package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;
import rc.task.Task;

/**
 * Represents a command to add a task to the task list.
 * <p>
 * This command adds a specified task to the task list, updates
 * the storage file, and displays the updated number of tasks in the list.
 */
public class AddTaskCommand extends Command {
    private final Task task;

    /**
     * Constructs an `AddTaskCommand` with the specified task.
     *
     * @param task The task to be added to the task list.
     */
    public AddTaskCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     * <p>
     * This method performs the following steps:
     * <ol>
     *   <li>Adds the task to the task list using `TaskList.addTask`.</li>
     *   <li>Writes the updated task list to the storage file using `storage.writeTaskToFile`.</li>
     *   <li>Displays the updated number of tasks using `TaskList.displayNumOfTasks`.</li>
     *   <li>Adds a divider using `UI.addDivider`.</li>
     * </ol>
     * </p>
     *
     * @param storage The storage component used to write the updated task list to the file.
     * @throws DukeException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(Storage storage) throws DukeException {
        TaskList.addTask(task);
        storage.writeTaskToFile();
        TaskList.displayNumOfTasks();
        UI.addDivider();
    }
}