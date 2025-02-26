package rc;

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
