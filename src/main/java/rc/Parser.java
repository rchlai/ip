package rc;

import rc.task.Deadline;
import rc.task.Event;
import rc.task.ToDo;

public class Parser {
    private static final String TO_DO_PREFIX = "todo";
    private static final String DEADLINE_PREFIX = "deadline";
    private static final String EVENT_PREFIX = "event";

    public static void determineTaskType(String line) throws DukeException {
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
        // add task instance into arrayList
        TaskList.addTask(toDo);
    }

    private static void handleDeadline(String line) throws DukeException {
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
        TaskList.addTask(deadline);
    }

    private static void handleEvent(String line) throws DukeException {
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
        TaskList.addTask(event);
    }

    private static String extractString(String line, int firstIndex,
                                        int lastIndex, String prefix) {
        return line.substring(firstIndex, lastIndex).replace(prefix, "")
                .trim();
    }

    private static String extractLastString(String line, int index, String prefix) {
        return line.substring(index).replace(prefix, "").trim();
    }
}
