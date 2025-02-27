package rc;

import java.util.Scanner;

import rc.command.Command;
import rc.exception.DukeException;
import rc.parser.Parser;
import rc.storage.Storage;
import rc.ui.UI;

public class RCApp {
    private final Scanner input = new Scanner(System.in);
    private final Storage storage;

    // relative path of 'F:\repos\ip\rc.txt' directory
    private static final String FILE_PATH = "rc.txt";

    public RCApp() {
        storage = new Storage(FILE_PATH);
    }

    public void chatWithBot() {
        UI.printChatbotLogo();
        UI.printWelcomeMessage();

        // load tasks from file when program starts
        storage.loadTasksFromFile();

        boolean isRunning = true;
        while (isRunning) {
            UI.printPrompt();
            String line = input.nextLine();

            try {
                Command command = Parser.parse(line);
                command.execute(storage);

                if (command.shouldExit()) {
                    isRunning = false;
                }
            } catch (DukeException error) {
                UI.printErrorMessage(error);
            }
        }
    }

    public static void main(String[] args) {
        new RCApp().chatWithBot();
    }
}
