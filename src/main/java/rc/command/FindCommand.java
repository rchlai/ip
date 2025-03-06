package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;

/**
 * Represents a command to search for tasks in the task list that match
 * a given keyword.
 * <p>
 * This command searches through the task list for tasks whose descriptions contain
 * the specified keyword as a whole word and displays the matching tasks.
 * <p>
 * A divider is added for better UI presentation.
 */
public class FindCommand extends Command {
    private final String line;

    /**
     * Constructs a `FindCommand` with the specified user input.
     *
     * @param input The user input containing the keyword to search for.
     */
    public FindCommand(String input) {
        line = input;
    }

    /**
     * Executes the command to search for tasks matching the keyword.
     * <p>
     * This method performs the following steps:
     * <ol>
     *   <li>Searches for tasks in the task list that match the keyword using `TaskList.findTask`.</li>
     *   <li>Adds a divider using `UI.addDivider`.</li>
     * </ol>
     * </p>
     *
     * @param storage The storage component (unused in this command).
     * @throws DukeException If an error occurs during the execution of the command.
     */
    @Override
    public void execute(Storage storage) throws DukeException {
        TaskList.findTask(line);
        UI.addDivider();
    }
}
