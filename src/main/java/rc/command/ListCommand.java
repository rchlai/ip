package rc.command;

import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;

/**
 * Represents a command to list all tasks in the task list.
 * <p>
 * This command prints all tasks currently stored in the task list and
 * adds a divider for better UI presentation.
 */
public class ListCommand extends Command {
    /**
     * Executes the command to list all tasks.
     * <p>
     * This method performs the following steps:
     * <ol>
     *   <li>Prints all tasks in the list using `TaskList.printAllTasks`.</li>
     *   <li>Adds a divider using `UI.addDivider`.</li>
     * </ol>
     * </p>
     *
     * @param storage The storage component (unused in this command).
     */
    @Override
    public void execute(Storage storage) {
        TaskList.printAllTasks();
        UI.addDivider();
    }
}
