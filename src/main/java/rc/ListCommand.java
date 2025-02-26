package rc;

public class ListCommand extends Command {
    @Override
    public void execute(Storage storage) {
        TaskList.printAllTasks();
        UI.addDivider();
    }
}
