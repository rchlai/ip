import java.util.Scanner;

public class RCApp {
    static Scanner input = new Scanner(System.in);
    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void chatWithBot() {
        printWelcomeMessage();

        while (true) {
            // prompt user to write command
            System.out.print("User says: ");
            String line = input.nextLine();

            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                printAllTasks();
                addLineSeparator();
                continue;
            }

            if (line.startsWith("mark")) {
                markTask(line);
                addLineSeparator();
                continue;
            }

            if (line.startsWith("unmark")) {
                unmarkTask(line);
                addLineSeparator();
                continue;
            }
            
            if (line.equals("bye")) {
                break;
            }

            // create a new Task instance
            Task t = new Task(line);
            addTask(t);
            System.out.println("This task has been added: " +  "\n" + t);
            displayNumOfTasks();
            addLineSeparator();
        };

        addLineSeparator();
        printFarewellMessage();
    }

    public static void printWelcomeMessage() {
        System.out.println("Good day! I'm RC, your personal chatbot.");
        System.out.println("You need my assistance today?\n");
    }

    public static void printFarewellMessage() {
        System.out.println("Goodbye. Hope I satisfy your needs for today!");
    }

    public static void addLineSeparator() {
        // print a line separator to avoid text clutter
        System.out.println("============================================");
    }

    public static void printChatbotLogo() {
        String logo =
                "______________\n"
                        +"\\____  \\_  __ \\\n"
                        +"|    _/    \\ \\/\n"
                        +"|  |  \\     \\__\n"
                        +"|__|__/\\______/\n";

        System.out.println("Hello from\n" + logo);
    }

    public static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
    }

    public static void displayNumOfTasks() {
        System.out.println("You have " + taskCount + " task(s) in the list");
    }

    public static void printAllTasks() {
        for (int i = 0; i < taskCount; i++) {
            // displays as 1.[X] <description> and so on
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    private static void unmarkTask(String line) {
        int unmarkIndex = Integer.parseInt(line.replaceAll("[^0-9]", ""));
        // use unmarkIndex to mark task from Task[] as not done
        tasks[unmarkIndex - 1].markAsNotDone();
        System.out.println("Noted, I've marked this task as not done yet:");
        // displays unmarked task
        System.out.println(unmarkIndex + "." + tasks[unmarkIndex - 1]);
    }

    private static void markTask(String line) {
        // extracts digits from string to be converted into integer type
        int markIndex = Integer.parseInt(line.replaceAll("[^0-9]", ""));
        // use markIndex to mark task from Task[] as done
        tasks[markIndex - 1].markAsDone();
        System.out.println("Good job! I'll mark this task as done:");
        // displays marked task
        System.out.println(markIndex + "." + tasks[markIndex - 1]);
    }

    public static void main(String[] args) {
        printChatbotLogo();
        chatWithBot();
    }
}
