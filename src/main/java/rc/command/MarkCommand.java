package rc.command;

import rc.DukeException;
import rc.storage.Storage;
import rc.TaskList;
import rc.UI;

public class MarkCommand extends Command {
    private final String line;

    public MarkCommand(String input) {
        line = input;
    }

    @Override
    public void execute(Storage storage) throws DukeException {
        TaskList.markTask(line);
        storage.writeTaskToFile();
        UI.addDivider();
    }
}
