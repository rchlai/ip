package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;

/**
 * Represents a command to mark a task as completed in the task list.
 * <p>
 * This command marks a task specified by the user input as completed,
 * updates the storage file, and adds a divider for better UI presentation.
 */
public class MarkCommand extends Command {
    private final String line;

    /**
     * Constructs a `MarkCommand` with the specified user input.
     *
     * @param input The user input specifying the task to be marked as completed.
     */
    public MarkCommand(String input) {
        line = input;
    }

    /**
     * Executes the command to mark the task as completed.
     * <p>
     * This method performs the following steps:
     * <ol>
     *   <li>Marks the specified task as completed using `TaskList.markTask`.</li>
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
        TaskList.markTask(line);
        storage.writeTaskToFile();
        UI.addDivider();
    }
}
