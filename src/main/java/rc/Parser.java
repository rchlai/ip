package rc;

import rc.task.Deadline;
import rc.task.Event;
import rc.task.ToDo;

public class Parser {
    /*
    protected static TaskList taskList;

    private static final String TO_DO_PREFIX = "todo";
    private static final String DEADLINE_PREFIX = "deadline";
    private static final String EVENT_PREFIX = "event";

    public Parser() {
        taskList = new TaskList();
    }

    public void determineTaskType(String line) throws DukeException {
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
                    "todo/event/deadline/mark/unmark/delete prefix.");
        }
    }

    private String extractTaskType(String line) {
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

    private void handleToDo(String line) throws DukeException {
        // remove "to-do" prefix from line and any leading & trailing whitespaces
        String description = line.replace(TO_DO_PREFIX, "").trim();

        // checks if to-do description is provided
        if (description.isEmpty()) {
            throw new DukeException("Please give a task description.");
        }

        // create a new to-do instance
        ToDo toDo = new ToDo(description);
        // add task instance into arrayList
        taskList.addTask(toDo);
    }

    private void handleDeadline(String line) throws DukeException {
        final String BY_PREFIX = "/by";

        // obtain index of "/by, " returns -1 if substring is not found
        int indexOfByPrefix = line.indexOf(BY_PREFIX);

        // checks if "/by" is not found
        boolean isByNotFound = (indexOfByPrefix == -1);
        if (isByNotFound) {
            throw new DukeException("Deadline tasks need '/by' keyword.");
        }

        // extract "deadline <description>" from line and remove "deadline" prefix
        String description = extractString(line, 0, indexOfByPrefix,
                DEADLINE_PREFIX);
        // extract "/by <due date>" from line and remove "/by" prefix
        String dueDate = extractLastString(line, indexOfByPrefix, BY_PREFIX);

        // checks if description or due date is not given
        if (description.isEmpty() || dueDate.isEmpty()) {
            throw new DukeException("Use convention: deadline <description> " +
                    "/by <due_date>");
        }

        // create a new Deadline instance
        Deadline deadline = new Deadline(description, dueDate);
        taskList.addTask(deadline);
    }

    private void handleEvent(String line) throws DukeException {
        final String FROM_PREFIX = "/from";
        final String TO_PREFIX = "/to";

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
        String description = extractString(line, 0, indexOfFromPrefix,
                EVENT_PREFIX);
        // extract "/from <start>" from line and remove "/from" prefix
        String start = extractString(line, indexOfFromPrefix, indexOfToPrefix,
                FROM_PREFIX);
        // extract "/to <end>" from line and remove "/to" prefix
        String end = extractLastString(line, indexOfToPrefix, TO_PREFIX);

        // checks if description, start and end date are not given
        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new DukeException("Use convention: event <description> /from" +
                    " <start> /to <end>");
        }

        // create a new Event instance
        Event event = new Event(description, start, end);
        taskList.addTask(event);
    }

    private String extractString(String line, int firstIndex,
                                        int lastIndex, String prefix) {
        return line.substring(firstIndex, lastIndex).replace(prefix, "")
                .trim();
    }

    private String extractLastString(String line, int index, String prefix) {
        return line.substring(index).replace(prefix, "").trim();
    }
     */
    private static final String TO_DO_PREFIX = "todo";
    private static final String DEADLINE_PREFIX = "deadline";
    private static final String EVENT_PREFIX = "event";

    public static Command parse(String line) throws DukeException {
        if (line.isEmpty()) {
            throw new DukeException("Empty input. Please write down a task to record.");
        }

        if (line.equalsIgnoreCase("list")) {
            return new ListCommand();
        } else if (line.startsWith("mark")) {
            return new MarkCommand(line);
        } else if (line.startsWith("unmark")) {
            return new UnmarkCommand(line);
        } else if (line.startsWith("delete")) {
            return new DeleteCommand(line);
        } else if (line.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        return parseTaskCommand(line);
    }

    private static Command parseTaskCommand(String line) throws DukeException {
        String taskType = extractTaskType(line);

        switch (taskType) {
        case "todo":
            return new AddTaskCommand(handleToDo(line));
        case "deadline":
            return new AddTaskCommand(handleDeadline(line));
        case "event":
            return new AddTaskCommand(handleEvent(line));
        default:
            throw new DukeException("Invalid task format. " +
                    "Please use todo/event/deadline/mark/unmark/delete prefix.");
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

    private static ToDo handleToDo(String line) throws DukeException {
        String description = line.replace(TO_DO_PREFIX, "").trim();
        if (description.isEmpty()) {
            throw new DukeException("Please provide a description for the to-do task.");
        }
        return new ToDo(description);
    }

    private static Deadline handleDeadline(String line) throws DukeException {
        final String BY_PREFIX = "/by";
        int indexOfByPrefix = line.indexOf(BY_PREFIX);

        if (indexOfByPrefix == -1) {
            throw new DukeException("Deadline tasks require '/by' keyword.");
        }

        String description = extractString(line, 0, indexOfByPrefix, DEADLINE_PREFIX);
        String dueDate = extractLastString(line, indexOfByPrefix, BY_PREFIX);

        if (description.isEmpty() || dueDate.isEmpty()) {
            throw new DukeException("Use format: deadline <description> /by <due_date>");
        }

        return new Deadline(description, dueDate);
    }

    private static Event handleEvent(String line) throws DukeException {
        final String FROM_PREFIX = "/from";
        final String TO_PREFIX = "/to";
        int indexOfFromPrefix = line.indexOf(FROM_PREFIX);
        int indexOfToPrefix = line.indexOf(TO_PREFIX);

        if (indexOfFromPrefix == -1 || indexOfToPrefix == -1 || indexOfFromPrefix > indexOfToPrefix) {
            throw new DukeException("Event tasks require '/from' and '/to' keywords.");
        }

        String description = extractString(line, 0, indexOfFromPrefix, EVENT_PREFIX);
        String start = extractString(line, indexOfFromPrefix, indexOfToPrefix, FROM_PREFIX);
        String end = extractLastString(line, indexOfToPrefix, TO_PREFIX);

        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new DukeException("Use format: event <description> /from <start> /to <end>");
        }

        return new Event(description, start, end);
    }

    private static String extractString(String line, int firstIndex,
                                        int lastIndex, String prefix) {
        return line.substring(firstIndex, lastIndex)
                .replace(prefix, "").trim();
    }

    private static String extractLastString(String line, int index, String prefix) {
        return line.substring(index).replace(prefix, "").trim();
    }
}
