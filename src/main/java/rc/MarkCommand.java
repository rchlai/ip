package rc;

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
