package rc.command;

import rc.storage.Storage;
import rc.TaskList;
import rc.UI;

public class ListCommand extends Command {
    @Override
    public void execute(Storage storage) {
        TaskList.printAllTasks();
        UI.addDivider();
    }
}
