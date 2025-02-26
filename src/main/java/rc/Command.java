package rc;

public abstract class Command {
    public abstract void execute(Storage storage) throws DukeException;

    // Override in ExitCommand to return true
    public boolean shouldExit() {
        return false;
    }
}
