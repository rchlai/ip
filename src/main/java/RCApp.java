import java.util.Scanner;

public class RCApp {
    // global variables
    static Scanner input = new Scanner(System.in);
    static Task[] tasks = new Task[100];
    static int taskCount = 0;

    // global prefix constants
    static final String BY_PREFIX = "/by";
    static final String FROM_PREFIX = "/from";
    static final String TO_PREFIX = "/to";
    static final String TO_DO_PREFIX = "todo";
    static final String DEADLINE_PREFIX = "deadline";
    static final String EVENT_PREFIX = "event";

    public static void chatWithBot() {
        printChatbotLogo();
        printWelcomeMessage();

        while (true) {
            // prompt user to write command
            System.out.print("User says: ");
            String line = input.nextLine();

            if (line.equals("list")) {
                printAllTasks();
                // print a line separator to avoid text clutter
                addLineSeparator();
                // skip to the next iteration
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
                // exit the while loop
                break;
            }

            // check Task type (i.e., to do, event, deadline)
            determineTaskType(line);
            // print number of tasks added
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

    public static void printChatbotLogo() {
        String logo =
                "______________\n"
                        +"\\____  \\_  __ \\\n"
                        +"|    _/    \\ \\/\n"
                        +"|  |  \\     \\__\n"
                        +"|__|__/\\______/\n";

        System.out.println("Hello from\n" + logo);
    }

    public static void printFarewellMessage() {
        System.out.println("Goodbye. Hope I satisfy your needs for today!");
    }

    public static void addLineSeparator() {
        System.out.println("============================================");
    }

    public static void addTask(Task t) {
        tasks[taskCount] = t;
        taskCount++;
        System.out.println("This task has been added: " +  "\n" + t);
    }

    public static void displayNumOfTasks() {
        System.out.println("You now have " + taskCount + " task(s) in the list");
    }

    public static void printAllTasks() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < taskCount; i++) {
            // displays as 1.[X] <description> and so on
            System.out.println((i + 1) + "." + tasks[i]);
        }
    }

    private static void markTask(String line) {
        // extract digits from string to be converted into integer type
        int markIndex = Integer.parseInt(line.replaceAll("[^0-9]", ""));

        // use markIndex to mark task from Task[] as done
        tasks[markIndex - 1].markAsDone();

        System.out.println("Good job! I'll mark this task as done:");
        // display marked task
        System.out.println(markIndex + "." + tasks[markIndex - 1]);
    }

    private static void unmarkTask(String line) {
        int unmarkIndex = Integer.parseInt(line.replaceAll("[^0-9]", ""));

        // use unmarkIndex to mark task from Task[] as not done
        tasks[unmarkIndex - 1].markAsNotDone();

        System.out.println("Noted, I've marked this task as not done yet:");
        // display unmarked task
        System.out.println(unmarkIndex + "." + tasks[unmarkIndex - 1]);
    }

    private static void determineTaskType(String line) {
        String taskType = extractTaskType(line);

        switch (taskType) {
        case "todo":
            handleToDo(line);
            break;
        case "deadline":
            handleDeadline(line);
            break;
        case "event":
            handleEvent(line);
            break;
        default:
            System.out.println("Invalid task format.");
            break;
        }
    }

    private static String extractTaskType(String line) {
        if (line.startsWith(TO_DO_PREFIX)) {
            return "todo";
        }

        if (line.startsWith(DEADLINE_PREFIX)) {
            return "deadline";
        }

        if (line.startsWith(EVENT_PREFIX)) {
            return "event";
        }

        return "invalid";
    }

    private static void handleToDo(String line) {
        // remove "to-do" prefix from line and any leading & trailing whitespaces
        String description = line.replace(TO_DO_PREFIX, "").trim();

        // create a new to-do instance
        ToDo td = new ToDo(description);
        addTask(td);
    }

    private static void handleDeadline(String line) {
        // obtain index of "/by" from line
        int indexOfByPrefix = line.indexOf(BY_PREFIX);

        // extract "deadline <description>" from line and remove "deadline" prefix
        String description = line.substring(0, indexOfByPrefix).replace(DEADLINE_PREFIX, "").trim();
        // extract "/by <due date>" from line and remove "/by" prefix
        String dueDate = line.substring(indexOfByPrefix).replace(BY_PREFIX, "").trim();

        // create a new Deadline instance
        Deadline d = new Deadline(description, dueDate);
        addTask(d);
    }

    private static void handleEvent(String line) {
        // obtain index of "/from" from line
        int indexOfFromPrefix = line.indexOf(FROM_PREFIX);
        // obtain index of "/to" from line
        int indexOfToPrefix = line.indexOf(TO_PREFIX);

        // extract "event <description>" from line and remove "event" prefix
        String description = line.substring(0, indexOfFromPrefix).replace(EVENT_PREFIX, "").trim();
        // extract "/from <start>" from line and remove "/from" prefix
        String start = line.substring(indexOfFromPrefix, indexOfToPrefix).replace(FROM_PREFIX, "").trim();
        // extract "/to <end>" from line and remove "/to" prefix
        String end = line.substring(indexOfToPrefix).replace(TO_PREFIX, "").trim();

        // create a new Event instance
        Event e = new Event(description, start, end);
        addTask(e);
    }

    public static void main(String[] args) {
        chatWithBot();
    }
}
