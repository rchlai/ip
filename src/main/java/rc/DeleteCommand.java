package rc;

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
