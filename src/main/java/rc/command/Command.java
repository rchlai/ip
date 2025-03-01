package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;

public abstract class Command {
    public abstract void execute(Storage storage) throws DukeException;

    // Override in ExitCommand to return true
    public boolean shouldExit() {
        return false;
    }
}
