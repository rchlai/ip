package rc.ui;

import rc.task.Task;

/**
 * This class contains all components and methods that make up and
 * design the user interface of the chatbot.
 */
public class UI {
    private static final int INDEX_OFFSET = 1;

    private static final String DIVIDER = "==========================" +
            "===============================================================";


    private static final String LOGO =
            "______________\n"
                    +"\\____  \\_  __ \\\n"
                    +"|    _/    \\ \\/\n"
                    +"|  |  \\     \\__\n"
                    +"|__|__/\\______/\n";

    /**
     * Getter method of the constant variable index offset.
     * @return INDEX_OFFSET
     */
    public static int getOffset() {
        return INDEX_OFFSET;
    }

    /**
     * Prints out a line of string passed into the method.
     * @param str of String
     */
    public static void print(String str) {
        System.out.println(str);
    }

    /**
     * Prints out a line divider to separate texts in the CLI
     * and avoid text clutter
     */
    public static void addDivider() {
        print(DIVIDER);
    }

    /**
     * Prints out the RCApp chatbot brand logo
     */
    public static void printChatbotLogo() {
        print("Hello from\n" + LOGO);
    }

    /**
     * Prints out the error message of a specific exception
     * @param error of Exception
     */
    public static void printErrorMessage(Exception error) {
        print(error.getMessage());
    }

    /**
     * Prints out the welcome prompt message of the chatbot.
     * <p>
     * Gives instruction on how to exit the program.
     */
    public static void printWelcomeMessage() {
        print("Good day! I'm RC, your personal chatbot.");
        print("Do you need my assistance?");
        print("To exit, type 'bye'.\n");
    }

    /**
     * Prints out the prompt for users to enter their commands.
     */
    public static void printPrompt() {
        print("User says: ");
    }

    /**
     * Prints out the chatbot farewell message after you exit the program.
     */
    public static void printFarewellMessage() {
        print("Goodbye. Hope I satisfy your needs for today!");
    }

    /**
     * Prints out the task formatted to be displayed with its index
     * calculated by adding to the index offset
     * @param index of int
     * @param task of Task
     */
    public static void showIndexedTask(int index, Task task) {
        print((index + UI.getOffset()) + "." + task);
    }

    /**
     * Prints out a task prompt when a task has been added.
     * @param task of Task
     */
    public static void showAddedTask(Task task) {
        print("This task has been added:\n" + task);
    }

    /**
     * Prints out a task prompt when a task has been deleted.
     * @param task of Task
     */
    public static void showDeletedTask(Task task) {
        print("This task has been deleted:\n" + task);
    }
}
