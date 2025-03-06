package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;

/**
 * Represents a command to unmark a task as incomplete in the task list.
 * <p>
 * This command unmarks a task specified by the user input as incomplete,
 * updates the storage file, and adds a divider for better UI presentation.
 */
public class UnmarkCommand extends Command {
    private final String line;

    /**
     * Constructs an `UnmarkCommand` with the specified user input.
     *
     * @param input The user input specifying the task to be unmarked as incomplete.
     */
    public UnmarkCommand(String input) {
        line = input;
    }

    /**
     * Executes the command to unmark the task as incomplete.
     * <p>
     * This method performs the following steps:
     * <ol>
     *   <li>Unmarks the specified task as incomplete using `TaskList.unmarkTask`.</li>
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
        TaskList.unmarkTask(line);
        storage.writeTaskToFile();
        UI.addDivider();
    }
}
