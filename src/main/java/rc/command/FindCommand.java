package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;

public class FindCommand extends Command {
    private final String line;

    public FindCommand(String input) {
        line = input;
    }

    @Override
    public void execute(Storage storage) throws DukeException {
        TaskList.findTask(line);
        storage.writeTaskToFile();
        UI.addDivider();
    }
}
