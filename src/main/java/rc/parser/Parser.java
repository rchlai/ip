package rc.parser;

import rc.DukeException;
import rc.command.AddTaskCommand;
import rc.command.Command;
import rc.command.DeleteCommand;
import rc.command.ExitCommand;
import rc.command.ListCommand;
import rc.command.MarkCommand;
import rc.command.UnmarkCommand;
import rc.task.Deadline;
import rc.task.Event;
import rc.task.ToDo;

public class Parser {
    private static final String TO_DO_PREFIX = "todo";
    private static final String DEADLINE_PREFIX = "deadline";
    private static final String EVENT_PREFIX = "event";

    public static Command parse(String line) throws DukeException {
        if (line.isEmpty()) {
            throw new DukeException("Empty input. Please write down a task" +
                    " to record.");
        }

        if (line.equalsIgnoreCase("list")) {
            return new ListCommand();
        }

        if (line.startsWith("mark")) {
            return new MarkCommand(line);
        }

        if (line.startsWith("unmark")) {
            return new UnmarkCommand(line);
        }

        if (line.startsWith("delete")) {
            return new DeleteCommand(line);
        }

        if (line.equalsIgnoreCase("bye")) {
            return new ExitCommand();
        }

        return parseTaskCommand(line);
    }

    private static Command parseTaskCommand(String line) throws DukeException {
        // Directly check for the prefix and return the corresponding Command
        if (line.startsWith(TO_DO_PREFIX)) {
            return new AddTaskCommand(handleToDo(line));
        }

        if (line.startsWith(DEADLINE_PREFIX)) {
            return new AddTaskCommand(handleDeadline(line));
        }

        if (line.startsWith(EVENT_PREFIX)) {
            return new AddTaskCommand(handleEvent(line));
        }

        throw new DukeException("Invalid task format. Please use todo/event/" +
                "deadline/mark/unmark/delete prefix.");
    }

    private static ToDo handleToDo(String line) throws DukeException {
        String description = line.replace(TO_DO_PREFIX, "").trim();

        if (description.isEmpty()) {
            throw new DukeException("Please provide a description for the " +
                    "to-do task.");
        }

        return new ToDo(description);
    }

    private static Deadline handleDeadline(String line) throws DukeException {
        final String BY_PREFIX = "/by";

        int indexOfByPrefix = line.indexOf(BY_PREFIX);

        // checks if "/by" is not found
        boolean isByNotFound = (indexOfByPrefix == -1);
        if (isByNotFound) {
            throw new DukeException("Deadline tasks need '/by' keyword.");
        }

        String description = extractString(line, 0, indexOfByPrefix,
                DEADLINE_PREFIX);
        String dueDate = extractLastString(line, indexOfByPrefix, BY_PREFIX);

        if (description.isEmpty() || dueDate.isEmpty()) {
            throw new DukeException("Use format: deadline <description> /by" +
                    " <due_date>");
        }

        return new Deadline(description, dueDate);
    }

    private static Event handleEvent(String line) throws DukeException {
        final String FROM_PREFIX = "/from";
        final String TO_PREFIX = "/to";

        int indexOfFromPrefix = line.indexOf(FROM_PREFIX);
        int indexOfToPrefix = line.indexOf(TO_PREFIX);

        // checks if "/from" or "/to" is not found
        boolean isFromNotFound = (indexOfFromPrefix == -1);
        boolean isToNotFound = (indexOfToPrefix == -1);
        // checks whether "/from" comes after "/to"
        boolean isFromAfterTo = (indexOfFromPrefix > indexOfToPrefix);
        if (isFromNotFound || isToNotFound || isFromAfterTo) {
            throw new DukeException("Event tasks need '/from' and '/to' keywords.");
        }

        String description = extractString(line, 0, indexOfFromPrefix,
                EVENT_PREFIX);
        String start = extractString(line, indexOfFromPrefix, indexOfToPrefix,
                FROM_PREFIX);
        String end = extractLastString(line, indexOfToPrefix, TO_PREFIX);

        if (description.isEmpty() || start.isEmpty() || end.isEmpty()) {
            throw new DukeException("Use format: event <description> /from" +
                    " <start> /to <end>");
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
