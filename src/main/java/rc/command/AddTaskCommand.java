package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;
import rc.tasklist.TaskList;
import rc.ui.UI;
import rc.task.Task;

public class AddTaskCommand extends Command {
    private final Task task;

    public AddTaskCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Storage storage) throws DukeException {
        TaskList.addTask(task);
        storage.writeTaskToFile();
        TaskList.displayNumOfTasks();
        UI.addDivider();
    }
}