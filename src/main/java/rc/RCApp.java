package rc;

import java.util.Scanner;

import rc.task.Deadline;
import rc.task.Event;
import rc.task.Task;
import rc.task.ToDo;

public class RCApp {
    // global variables
    static Scanner input = new Scanner(System.in);
    static Task[] tasks = new Task[100];
    static int taskCount = 0;
    // offset is to be added to or subtracted from the index variable
    static int indexOffset = 1;

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

            if (line.equalsIgnoreCase("list")) {
                printAllTasks();
                // print a line separator to avoid text clutter
                addLineSeparator();
                // skip to the next iteration
                continue;
            }

            if (line.startsWith("mark")) {
                try {
                    markTask(line);
                } catch (DukeException error) {
                    printErrorMessage(error);
                } finally {
                    addLineSeparator();
                }
                continue;
            }

            if (line.startsWith("unmark")) {
                try {
                    unmarkTask(line);
                } catch (DukeException error) {
                    printErrorMessage(error);
                } finally {
                    addLineSeparator();
                }
                continue;
            }
            
            if (line.equalsIgnoreCase("bye")) {
                // exit the while loop
                break;
            }

            try {
                // check Task type (i.e., to do, event, deadline)
                determineTaskType(line);
                // print number of tasks added
                displayNumOfTasks();
            } catch (DukeException error) {
                printErrorMessage(error);
            } finally {
                addLineSeparator();
            }
        }

        addLineSeparator();
        printFarewellMessage();
    }

    public static void printWelcomeMessage() {
        System.out.println("Good day! I'm RC, your personal chatbot.");
        System.out.println("Do you need my assistance?\n");
        System.out.println("To exit, type 'bye'.");
    }

    public static void printChatbotLogo() {
        final String LOGO =
                "______________\n"
                        +"\\____  \\_  __ \\\n"
                        +"|    _/    \\ \\/\n"
                        +"|  |  \\     \\__\n"
                        +"|__|__/\\______/\n";

        System.out.println("Hello from\n" + LOGO);
    }

    public static void printFarewellMessage() {
        System.out.println("Goodbye. Hope I satisfy your needs for today!");
    }

    public static void addLineSeparator() {
        System.out.println("================================================" +
                "=========================================");
    }

    public static void printErrorMessage(DukeException error) {
        System.out.println(error.getMessage());
    }

    public static void addTask(Task t) throws DukeException {
        if (taskCount >= tasks.length) {
            throw new DukeException("Maximum number of tasks recorded has " +
                    "been reached.");
        }

        tasks[taskCount] = t;
        taskCount++;
        System.out.println("This task has been added: " +  "\n" + t);
    }

    public static void displayNumOfTasks() {
        System.out.println("You have " + taskCount + " task(s) in the list");
    }

    public static void printAllTasks() {
        System.out.println("Here are the tasks in your list:");

        for (int i = 0; i < taskCount; i++) {
            // displays as 1.[X] <description> and so on
            System.out.println((i + indexOffset) + "." + tasks[i]);
        }
    }

    private static void markTask(String line) throws DukeException {
        try {
            // extract digits from string to be converted into integer type
            int markIndex = Integer.parseInt(line.replaceAll("[^0-9]",
                    "")) - indexOffset;
            // checks if index is negative, greater than, or equal to taskCount
            validateIndex(markIndex);

            // use markIndex to mark task from Task[] as done
            tasks[markIndex].markAsDone();

            System.out.println("Good job! I've marked this task as done:");
            // display marked task
            System.out.println((markIndex + indexOffset) + "." + tasks[markIndex]);
        } catch (NumberFormatException error) {
            throw new DukeException("Invalid mark format. Use: mark <task_number>");
        }
    }

    private static void unmarkTask(String line) throws DukeException {
        try {
            int unmarkIndex = Integer.parseInt(line.replaceAll("[^0-9]",
                    "")) - indexOffset;
            validateIndex(unmarkIndex);

            // use unmarkIndex to mark task from Task[] as not done
            tasks[unmarkIndex].markAsNotDone();

            System.out.println("Noted, I've marked this task as not done yet:");
            // display marked task
            System.out.println((unmarkIndex + indexOffset) + "." + tasks[unmarkIndex]);
        } catch (NumberFormatException error) {
            throw new DukeException("Use: unmark <task_number>");
        }
    }

    private static void validateIndex(int index) throws DukeException {
        if (index < 0 || index >= taskCount) {
            throw new DukeException("Invalid or unavailable task number.");
        }
    }

    private static void determineTaskType(String line) throws DukeException {
        // checks if user provides a line input
        if (line.isEmpty()) {
            throw new DukeException("Empty input. Please write down a task " +
                    "to record.");
        }

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
            throw new DukeException("Invalid task format. Please use " +
                    "todo/event/deadline prefix.");
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

    private static void handleToDo(String line) throws DukeException {
        // remove "to-do" prefix from line and any leading & trailing whitespaces
        String description = line.replace(TO_DO_PREFIX, "").trim();

        // checks if to-do description is provided
        if (description.isEmpty()) {
            throw new DukeException("Please give a task description.");
        }

        // create a new to-do instance
        ToDo toDo = new ToDo(description);
        try {
            addTask(toDo);
        } catch (DukeException error) {
            printErrorMessage(error);
        }
    }

    private static void handleDeadline(String line) throws DukeException {
        // obtain index of "/by, " returns -1 if substring is not found
        int indexOfByPrefix = line.indexOf(BY_PREFIX);

        // checks if "/by" is not found
        boolean isByNotFound = (indexOfByPrefix == -1);
        if (isByNotFound) {
            throw new DukeException("Deadline tasks need '/by' keyword.");
        }

        // extract "deadline <description>" from line and remove "deadline" prefix
        String description = extractDescription(line, indexOfByPrefix, DEADLINE_PREFIX);
        // extract "/by <due date>" from line and remove "/by" prefix
        String dueDate = line.substring(indexOfByPrefix)
                .replace(BY_PREFIX, "").trim();

        // checks if description or due date is not given
        if (description.isEmpty() || dueDate.isEmpty()) {
            throw new DukeException("Use convention: deadline <description> " +
                    "/by <due_date>");
        }

        // create a new Deadline instance
        Deadline deadline = new Deadline(description, dueDate);
        try {
            addTask(deadline);
        } catch (DukeException error) {
            printErrorMessage(error);
        }
    }

    private static void handleEvent(String line) throws DukeException {
        // obtain index of "/from" from line
        int indexOfFromPrefix = line.indexOf(FROM_PREFIX);
        // obtain index of "/to"
        int indexOfToPrefix = line.indexOf(TO_PREFIX);

        // checks if "/from" or "/to" is not found
        boolean isFromNotFound = (indexOfFromPrefix == -1);
        boolean isToNotFound = (indexOfToPrefix == -1);
        // checks whether "/from" comes after "/to"
        boolean isFromAfterTo = (indexOfFromPrefix > indexOfToPrefix);
        if (isFromNotFound || isToNotFound || isFromAfterTo) {
            throw new DukeException("Event tasks need '/from' and '/to' keywords.");
        }

        // extract "event <description>" from line and remove "event" prefix
        String description = extractDescription(line, indexOfFromPrefix, EVENT_PREFIX);
        // extract "/from <start>" from line and remove "/from" prefix
        String start = line.substring(indexOfFromPrefix, indexOfToPrefix)
                .replace(FROM_PREFIX, "").trim();
        // extract "/to <end>" from line and remove "/to" prefix
        String end = line.substring(indexOfToPrefix)
                .replace(TO_PREFIX, "").trim();

        // checks if description, start and end date are not given
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new DukeException("Use convention: event <description> /from" +
                    " <start> /to <end>");
        }

        // create a new Event instance
        Event event = new Event(description, start, end);
        try {
            addTask(event);
        } catch (DukeException error) {
            printErrorMessage(error);
        }
    }

    private static String extractDescription(String line, int prefixIndex, String prefix) {
        return line.substring(0, prefixIndex).replace(prefix, "").trim();
    }

    public static void main(String[] args) {
        chatWithBot();
    }
}
