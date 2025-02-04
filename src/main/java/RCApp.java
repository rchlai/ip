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
                for (int i = 0; i < taskCount; i++) {
                    // displays as 1.[X] read book and so on
                    System.out.println((i + 1) + "." + "[" + tasks[i].getStatusIcon()
                            + "] " + tasks[i].getDescription());
                }
                // create an empty line to avoid text clutter
                System.out.println();
            } else if (line.startsWith("mark")) {
                // extracts digits from string to be converted into integer type
                int markIndex = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                // use markIndex to mark task from Task[] as done
                tasks[markIndex - 1].markAsDone();
                System.out.println("Good job! I'll mark this task as done:");
                // displays marked task
                System.out.println(markIndex + "." + "[" + tasks[markIndex - 1].getStatusIcon()
                        + "] " + tasks[markIndex - 1].getDescription() + "\n");
            } else if (line.startsWith("unmark")) {
                int unmarkIndex = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                // use unmarkIndex to mark task from Task[] as not done
                tasks[unmarkIndex - 1].markAsNotDone();
                System.out.println("Noted, I've marked this task as not done yet:");
                // displays unmarked task
                System.out.println(unmarkIndex + "." + "[" + tasks[unmarkIndex - 1].getStatusIcon()
                        + "] " + tasks[unmarkIndex - 1].getDescription() + "\n");
            } else if (line.equals("bye")) {
                break;
            } else {
                // create a new Task instance
                Task t = new Task(line);
                System.out.println("RC added: " + t.getDescription() + "\n");
                addTask(t);
            }
        };

        printFarewellMessage();
    }

    public static void printWelcomeMessage() {
        System.out.println("Good day! I'm RC, your personal chatbot.");
        System.out.println("You need my assistance today?\n");
    }

    public static void printFarewellMessage() {
        System.out.println("Goodbye. Hope I satisfy your needs for today!");
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

    public static void main(String[] args) {
        printChatbotLogo();
        chatWithBot();
    }
}
