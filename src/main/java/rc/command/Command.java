package rc.command;

import rc.exception.DukeException;
import rc.storage.Storage;

/**
 * Represents an abstract command that can be executed.
 * <p>
 * This class serves as the base class for all commands in the application.
 * <p>
 * Subclasses must implement the `execute` method to define the specific
 * behavior
 * of the command.
 * <p>
 * The `shouldExit` method can be overridden to indicate whether
 * the command should terminate the application.
 */
public abstract class Command {
    /**
     * Executes the command with the given storage.
     * <p>
     * This method must be implemented by subclasses to define the specific
     * behavior of the command.
     *
     * @param storage The storage component used by the command to interact with data.
     * @throws DukeException If an error occurs during command execution.
     */
    public abstract void execute(Storage storage) throws DukeException;

    /**
     * Indicates whether the command should cause the application to exit.
     * <p>
     * By default, this method returns `false`. Subclasses (e.g., `ExitCommand`)
     * can override this method to return `true` if the command should terminate
     * the application.
     *
     * @return `true` if the command should cause the application to exit,
     *         otherwise `false`.
     */
    public boolean shouldExit() {
        return false;
    }
}
