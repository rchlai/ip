package rc;

import java.util.Scanner;

import rc.command.Command;
import rc.exception.DukeException;
import rc.parser.Parser;
import rc.storage.Storage;
import rc.ui.UI;

/**
 * This is the main class for running the RCApp chatbot.
 * It utilizes the UI, Parser, TaskList, and Storage class containing
 * each of the components and functionalities of the chatbot.
 */
public class RCApp {
    private final Scanner input = new Scanner(System.in);
    private final Storage storage;

    // relative path of 'F:\repos\ip\rc.txt' directory
    private static final String FILE_PATH = "rc.txt";

    /**
     * Constructs a Storage instance which accepts
     * a file path of the text file to be read from and write to when
     * initialized.
     */
    public RCApp() {
        storage = new Storage(FILE_PATH);
    }

    /**
     * A while loop is used to keep the program running as it parses
     * commands typed into it until the user typed in 'exit' in the CLI
     * to halt the program.
     * <p>
     * All recorded tasks will be input into a text file (.txt). When the
     * program runs again, it will read the previously recorded tasks from
     * the same file.
     *
     * @throws DukeException To catch exceptions to each parsed command
     */
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


    /**
     * Serves as the entry point of the chatbot application.
     * Creates a new RCApp instance and uses its chatWithBot()
     * method to run the program.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        new RCApp().chatWithBot();
    }
}
