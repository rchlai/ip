package rc.command;

import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;

public class ListCommand extends Command {
    @Override
    public void execute(Storage storage) {
        TaskList.printAllTasks();
        UI.addDivider();
    }
}
