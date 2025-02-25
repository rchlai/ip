package rc;

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

    public static int getOffset() {
        return INDEX_OFFSET;
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void addDivider() {
        print(DIVIDER);
    }

    public static void printChatbotLogo() {
        print("Hello from\n" + LOGO);
    }

    public static void printErrorMessage(Exception error) {
        print(error.getMessage());
    }

    public static void printWelcomeMessage() {
        print("Good day! I'm RC, your personal chatbot.");
        print("Do you need my assistance?");
        print("To exit, type 'bye'.\n");
    }

    public static void printPrompt() {
        print("User says: ");
    }

    public static void printFarewellMessage() {
        print("Goodbye. Hope I satisfy your needs for today!");
    }
}
