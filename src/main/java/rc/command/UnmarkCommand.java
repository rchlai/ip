package rc.command;

import rc.DukeException;
import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.UI;

public class UnmarkCommand extends Command {
    private final String line;

    public UnmarkCommand(String input) {
        line = input;
    }

    @Override
    public void execute(Storage storage) throws DukeException {
        TaskList.unmarkTask(line);
        storage.writeTaskToFile();
        UI.addDivider();
    }
}
