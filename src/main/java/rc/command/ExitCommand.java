package rc.command;

import rc.storage.Storage;
import rc.ui.UI;

/**
 * Represents a command to exit the application.
 * <p>
 * This command prints a farewell message to the user and signals the application
 * to terminate by overriding the `shouldExit` method to return `true`.
 */
public class ExitCommand extends Command {
    /**
     * Executes the command to exit the application.
     * <p>
     * This method performs the following steps:
     * <ol>
     *   <li>Prints a farewell message to user using `UI.printFarewellMessage`.</li>
     *   <li>Adds a divider using `UI.addDivider`.</li>
     * </ol>
     * </p>
     *
     * @param storage The storage component (unused in this command).
     */
    @Override
    public void execute(Storage storage) {
        UI.printFarewellMessage();
        UI.addDivider();
    }

    /**
     * Indicates that the application should exit.
     * <p>
     * This method overrides the default behavior of the `shouldExit` method
     * in the `Command` class to return `true`, signaling that the application
     * should terminate.
     *
     * @return `true` to indicate that the application should exit.
     */
    @Override
    public boolean shouldExit() {
        return true;
    }
}
