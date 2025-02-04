import java.util.Scanner;

public class RCApp {
    static Scanner input = new Scanner(System.in);
    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    public static void chatWithBot() {
        // prefix constants
        final String BY_PREFIX = "/by";
        final String FROM_PREFIX = "/from";
        final String TO_PREFIX = "/to";
        final String TO_DO_PREFIX = "todo";
        final String DEADLINE_PREFIX = "deadline";
        final String EVENT_PREFIX = "event";

        String description;
        String dueDate;
        String start;
        String end;
        int indexOfByPrefix;
        int indexOfFromPrefix;
        int indexOfToPrefix;

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

            if (line.startsWith(TO_DO_PREFIX)) {
                description = line.replace(TO_DO_PREFIX, "").trim();

                ToDo td = new ToDo(description);
                addTask(td);
                System.out.println("This task has been added: " +  "\n" + td);
            }

            if (line.startsWith(DEADLINE_PREFIX)) {
                indexOfByPrefix = line.indexOf(BY_PREFIX);

                description = line.substring(0, indexOfByPrefix).replace(DEADLINE_PREFIX, "").trim();
                dueDate = line.substring(indexOfByPrefix).replace("/by", "").trim();

                Deadline d = new Deadline(description, dueDate);
                addTask(d);
                System.out.println("This task has been added: " +  "\n" + d);
            }

            if (line.startsWith(EVENT_PREFIX)) {
                indexOfFromPrefix = line.indexOf("/from");
                indexOfToPrefix = line.indexOf("/to");

                description = line.substring(0, indexOfFromPrefix).replace(EVENT_PREFIX, "").trim();
                start = line.substring(indexOfFromPrefix, indexOfToPrefix).replace(FROM_PREFIX, "").trim();
                end = line.substring(indexOfToPrefix).replace(TO_PREFIX, "").trim();

                Event e = new Event(description, start, end);
                addTask(e);
                System.out.println("This task has been added: " +  "\n" + e);
            }

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
