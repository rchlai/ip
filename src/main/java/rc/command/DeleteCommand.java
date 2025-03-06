package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;

/**
 * Represents a command to delete a task from the task list.
 * <p>
 * This command deletes a task specified by the user input, updates
 * the storage file, and adds a visual divider for better UI presentation.
 */
public class DeleteCommand extends Command {
    private final String line;

    /**
     * Constructs a `DeleteCommand` with the specified user input.
     *
     * @param input The user input specifying the task to be deleted.
     */
    public DeleteCommand(String input) {
        line = input;
    }

    /**
     * Executes the command to delete the task from the task list.
     * <p>
     * This method performs the following steps:
     * <ol>
     *   <li>Deletes the task from the task list using `TaskList.deleteTask`.</li>
     *   <li>Writes the updated task list to the storage file using `storage.writeTaskToFile`.</li>
     *   <li>Adds a divider using `UI.addDivider`.</li>
     * </ol>
     * </p>
     *
     * @param storage The storage component used to write the updated task list to the file.
     * @throws DukeException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(Storage storage) throws DukeException {
        TaskList.deleteTask(line);
        storage.writeTaskToFile();
        UI.addDivider();
    }
}
