package rc;

import java.util.Scanner;

import rc.command.Command;
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

        /*
        while (true) {
            // prompt user to write command
            UI.printPrompt();
            String line = input.nextLine();

            if (line.equalsIgnoreCase("list")) {
                TaskList.printAllTasks();
                // print a line divider to avoid text clutter
                UI.addDivider();
                // skip to the next iteration
                continue;
            }

            if (line.startsWith("mark")) {
                try {
                    TaskList.markTask(line);
                    storage.writeTaskToFile();
                } catch (DukeException error) {
                    UI.printErrorMessage(error);
                } finally {
                    UI.addDivider();
                }
                continue;
            }

            if (line.startsWith("unmark")) {
                try {
                    TaskList.unmarkTask(line);
                    storage.writeTaskToFile();
                } catch (DukeException error) {
                    UI.printErrorMessage(error);
                } finally {
                    UI.addDivider();
                }
                continue;
            }

            if (line.startsWith("delete")) {
                try {
                    TaskList.deleteTask(line);
                    storage.writeTaskToFile();
                } catch (DukeException error) {
                    UI.printErrorMessage(error);
                } finally {
                    UI.addDivider();
                }
                continue;
            }

            if (line.equalsIgnoreCase("bye")) {
                // exit the while loop
                break;
            }

            try {
                // check Task type (i.e., to do, event, deadline)
                Parser.determineTaskType(line);
                storage.writeTaskToFile();
                // print number of tasks added
                TaskList.displayNumOfTasks();
            } catch (DukeException error) {
                UI.printErrorMessage(error);
            } finally {
                UI.addDivider();
            }
        }

        UI.addDivider();
        UI.printFarewellMessage();
         */
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
