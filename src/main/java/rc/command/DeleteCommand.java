package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;

public class DeleteCommand extends Command {
    private final String line;

    public DeleteCommand(String input) {
        line = input;
    }

    @Override
    public void execute(Storage storage) throws DukeException {
        TaskList.deleteTask(line);
        storage.writeTaskToFile();
        UI.addDivider();
    }
}
