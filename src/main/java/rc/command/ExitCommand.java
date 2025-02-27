package rc.command;

import rc.storage.Storage;
import rc.UI;

public class ExitCommand extends Command {
    @Override
    public void execute(Storage storage) {
        UI.printFarewellMessage();
        UI.addDivider();
    }

    @Override
    public boolean shouldExit() {
        return true;
    }
}
