package rc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import rc.task.Deadline;
import rc.task.Event;
import rc.task.Task;
import rc.task.ToDo;

public class RCApp {
    // global variables
    static Scanner input = new Scanner(System.in);
    private static Storage storage;
//    private static Parser parser;
//    static ArrayList<Task> tasks = new ArrayList<>();
//    static int indexOffset = 1;

    // global prefix constants
//    private static final String TO_DO_PREFIX = "todo";
//    private static final String DEADLINE_PREFIX = "deadline";
//    private static final String EVENT_PREFIX = "event";

    // relative path of 'F:\repos\ip\rc.txt' directory
    private static final String FILE_PATH = "rc.txt";

    public RCApp() {
        storage = new Storage(FILE_PATH);
//        parser = new Parser();
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

//    public static void print(String str) {
//        System.out.println(str);
//    }

//    public static void printWelcomeMessage() {
//        UI.print("Good day! I'm RC, your personal chatbot.");
//        UI.print("Do you need my assistance?");
//        UI.print("To exit, type 'bye'.\n");
//    }

//    public static void printChatbotLogo() {
//        final String LOGO =
//                "______________\n"
//                        +"\\____  \\_  __ \\\n"
//                        +"|    _/    \\ \\/\n"
//                        +"|  |  \\     \\__\n"
//                        +"|__|__/\\______/\n";
//
//        UI.print("Hello from\n" + LOGO);
//    }

//    public static void printFarewellMessage() {
//        UI.print("Goodbye. Hope I satisfy your needs for today!");
//    }

//    public static void addDivider() {
//        print("================================================" +
//                "=========================================");
//    }

//    public static void printErrorMessage(Exception error) {
//        UI.print(error.getMessage());
//    }

//    public static void addTask(Task t) {
//        // add task into arrayList
//        tasks.add(t);
//        // print added task
//        print("This task has been added: " +  "\n" + t);
//    }

//    public static void displayNumOfTasks() {
//        print("You have " + tasks.size() + " task(s) in the list");
//    }

//    public static void printAllTasks() {
//        print("Here are the tasks in your list:");
//
//        int index = 0;
//        for (Task task: tasks) {
//            print((index + indexOffset) + "." + task);
//            index++;
//        }
//    }

//    private static void markTask(String line) throws DukeException {
//        try {
//            // extract digits from string to be converted into integer type
//            int markIndex = extractIndex(line);
//            // checks if index is negative, greater than, or equal to taskCount
//            validateIndex(markIndex);
//
//            Task markedTask = tasks.get(markIndex);
//            // use markIndex to mark task from Task[] as done
//            markedTask.markAsDone();
//
//            UI.print("Good job! I've marked this task as done:");
//            // display marked task
//            UI.print((markIndex + indexOffset) + "." + markedTask);
//        } catch (NumberFormatException error) {
//            throw new DukeException("Invalid mark format. Use: mark <task_number>");
//        }
//    }

//    private static void unmarkTask(String line) throws DukeException {
//        try {
//            int unmarkIndex = extractIndex(line);
//            validateIndex(unmarkIndex);
//
//            Task unmarkTask = tasks.get(unmarkIndex);
//            // use unmarkIndex to mark task from Task[] as not done
//            unmarkTask.markAsNotDone();
//
//            UI.print("Noted, I've marked this task as not done yet:");
//            // display marked task
//            UI.print((unmarkIndex + indexOffset) + "." + unmarkTask);
//        } catch (NumberFormatException error) {
//            throw new DukeException("Use: unmark <task_number>");
//        }
//    }

//    private static void deleteTask(String line) throws DukeException {
//        try {
//            int deleteIndex = extractIndex(line);
//            validateIndex(deleteIndex);
//
//            Task deletedTask = tasks.get(deleteIndex);
//            // display task before deletion
//            UI.print("This task will be deleted:\n" + deletedTask);
//
//            // remove task from arrayList
//            tasks.remove(deletedTask);
//            // display number of tasks left
//            displayNumOfTasks();
//        } catch (NumberFormatException error) {
//            throw new DukeException("Use: delete <task_number>");
//        }
//    }

//    private static int extractIndex(String line) {
//        return Integer.parseInt(line.replaceAll("[^0-9]",
//                "")) - indexOffset;
//    }
//
//    private static void validateIndex(int index) throws DukeException {
//        if (index < 0 || index >= tasks.size()) {
//            throw new DukeException("Invalid or unavailable task number.");
//        }
//    }

//    private static void determineTaskType(String line) throws DukeException {
//        // checks if user provides a line input
//        if (line.isEmpty()) {
//            throw new DukeException("Empty input. Please write down a task " +
//                    "to record.");
//        }
//
//        String taskType = extractTaskType(line);
//        switch (taskType) {
//        case "todo":
//            handleToDo(line);
//            break;
//        case "deadline":
//            handleDeadline(line);
//            break;
//        case "event":
//            handleEvent(line);
//            break;
//        default:
//            throw new DukeException("Invalid task format. Please use " +
//                    "todo/event/deadline/mark/unmark/delete prefix.");
//        }
//    }
//
//    private static String extractTaskType(String line) {
//        if (line.startsWith(TO_DO_PREFIX)) {
//            return "todo";
//        }
//
//        if (line.startsWith(DEADLINE_PREFIX)) {
//            return "deadline";
//        }
//
//        if (line.startsWith(EVENT_PREFIX)) {
//            return "event";
//        }
//
//        return "invalid";
//    }
//
//    private static void handleToDo(String line) throws DukeException {
//        // remove "to-do" prefix from line and any leading & trailing whitespaces
//        String description = line.replace(TO_DO_PREFIX, "").trim();
//
//        // checks if to-do description is provided
//        if (description.isEmpty()) {
//            throw new DukeException("Please give a task description.");
//        }
//
//        // create a new to-do instance
//        ToDo toDo = new ToDo(description);
//        // add task instance into arrayList
//        TaskList.addTask(toDo);
//    }
//
//    private static void handleDeadline(String line) throws DukeException {
//        final String BY_PREFIX = "/by";
//
//        // obtain index of "/by, " returns -1 if substring is not found
//        int indexOfByPrefix = line.indexOf(BY_PREFIX);
//
//        // checks if "/by" is not found
//        boolean isByNotFound = (indexOfByPrefix == -1);
//        if (isByNotFound) {
//            throw new DukeException("Deadline tasks need '/by' keyword.");
//        }
//
//        // extract "deadline <description>" from line and remove "deadline" prefix
//        String description = extractString(line, 0, indexOfByPrefix,
//                DEADLINE_PREFIX);
//        // extract "/by <due date>" from line and remove "/by" prefix
//        String dueDate = extractLastString(line, indexOfByPrefix, BY_PREFIX);
//
//        // checks if description or due date is not given
//        if (description.isEmpty() || dueDate.isEmpty()) {
//            throw new DukeException("Use convention: deadline <description> " +
//                    "/by <due_date>");
//        }
//
//        // create a new Deadline instance
//        Deadline deadline = new Deadline(description, dueDate);
//        TaskList.addTask(deadline);
//    }
//
//    private static void handleEvent(String line) throws DukeException {
//        final String FROM_PREFIX = "/from";
//        final String TO_PREFIX = "/to";
//
//        // obtain index of "/from" from line
//        int indexOfFromPrefix = line.indexOf(FROM_PREFIX);
//        // obtain index of "/to"
//        int indexOfToPrefix = line.indexOf(TO_PREFIX);
//
//        // checks if "/from" or "/to" is not found
//        boolean isFromNotFound = (indexOfFromPrefix == -1);
//        boolean isToNotFound = (indexOfToPrefix == -1);
//        // checks whether "/from" comes after "/to"
//        boolean isFromAfterTo = (indexOfFromPrefix > indexOfToPrefix);
//        if (isFromNotFound || isToNotFound || isFromAfterTo) {
//            throw new DukeException("Event tasks need '/from' and '/to' keywords.");
//        }
//
//        // extract "event <description>" from line and remove "event" prefix
//        String description = extractString(line, 0, indexOfFromPrefix,
//                EVENT_PREFIX);
//        // extract "/from <start>" from line and remove "/from" prefix
//        String start = extractString(line, indexOfFromPrefix, indexOfToPrefix,
//                FROM_PREFIX);
//        // extract "/to <end>" from line and remove "/to" prefix
//        String end = extractLastString(line, indexOfToPrefix, TO_PREFIX);
//
//        // checks if description, start and end date are not given
//        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
//            throw new DukeException("Use convention: event <description> /from" +
//                    " <start> /to <end>");
//        }
//
//        // create a new Event instance
//        Event event = new Event(description, start, end);
//        TaskList.addTask(event);
//    }
//
//    private static String extractString(String line,
//                                        int firstIndex,
//                                        int lastIndex,
//                                        String prefix) {
//        return line.substring(firstIndex, lastIndex).replace(prefix, "")
//                .trim();
//    }
//
//    private static String extractLastString(String line, int index, String prefix) {
//        return line.substring(index).replace(prefix, "").trim();
//    }

//    public static void writeTaskToFile() {
//        try {
//            writeToFile();
//        } catch (IOException error) {
//            printErrorMessage(error);
//        }
//    }

//    private static void writeToFile() throws IOException {
//        FileWriter writer = new FileWriter(FILE_PATH);
//        // write each task from arrayList to rc.txt in file format
//        for (Task task: tasks) {
//            writer.write(task.toFileFormat() + "\n");
//        }
//        // complete the writing operation
//        writer.close();
//    }

//    public static void loadTasksFromFile() {
//        try {
//            printFileContents();
//        } catch (FileNotFoundException error) {
//            UI.print("File is not found.");
//        }
//    }
//
//    private static void printFileContents() throws FileNotFoundException {
//        File file = new File(FILE_PATH);
//        File parentFolder = file.getParentFile();
//
//        try {
//            // ensure the folder exists before trying to create file
//            if (parentFolder != null && !parentFolder.exists()) {
//                // create a new folder
//                if (parentFolder.mkdirs()) {
//                    UI.print("Creating parent folder at: " + parentFolder.getPath());
//                }
//                // no file to create if the folder was just created
//                return;
//            }
//
//            // checking existing data file
//            if (!file.exists()) {
//                if (file.createNewFile()) {
//                    UI.print("No existing data file found. " +
//                            "Creating a new one at: " + FILE_PATH);
//                }
//                // No tasks to load if file was just created
//                return;
//            }
//
//            Scanner scanner = new Scanner(file);
//            while (scanner.hasNextLine()) {
//                String line = scanner.nextLine();
//                parseAndAddTask(line);
//            }
//        } catch (DukeException | IOException error) {
//            UI.printErrorMessage(error);
//        } finally {
//            UI.addDivider();
//        }
//    }
//
//    private static void parseAndAddTask(String taskData) throws DukeException {
//        // split task string in file into an array of substrings
//        // e.g., T | 0 | wake up => ["T", "0", "wake up"]
//        String[] parts = taskData.split(" \\| ");
//
//        // parse each task from existing file
//        Task task = parseTaskFromFile(parts);
//
//        // add task to tasks list
//        TaskList.addTask(task);
//    }
//
//    private static Task parseTaskFromFile(String[] parts) throws DukeException {
//        String taskType = getPart(parts, 0);
//        boolean isDone = getPart(parts, 1).equals("1");
//        String description = getPart(parts, 2);
//
//        Task task;
//        switch (taskType) {
//        case "T":
//            // to-do task format after splitting:
//            // ["T", "0/1", "<description>"]
//            task = new ToDo(description);
//            break;
//        case "D":
//            // deadline task format after splitting:
//            // ["D", "0/1", "<description>", "<dueDate>"]
//            String dueDate = getPart(parts, 3);
//            task = new Deadline(description, dueDate);
//            break;
//        case "E":
//            // event task format after splitting:
//            // ["E", "0/1", "<description>", "<start>", "<end>"]
//            String start = getPart(parts, 3);
//            String end = getPart(parts, 4);
//            task = new Event(description, start, end);
//            break;
//        default:
//            throw new DukeException("Invalid task format.");
//        }
//
//        // mark task as done in program if isDone == 1 (true)
//        if (isDone) {
//            task.markAsDone();
//        }
//
//        return task;
//    }
//
//    private static String getPart(String[] parts, int index) {
//        return parts[index];
//    }

    public static void main(String[] args) {
        new RCApp().chatWithBot();
    }
}
