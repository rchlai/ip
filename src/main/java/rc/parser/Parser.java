package rc.parser;

import rc.exception.DukeException;
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

/**
 * Parses user command inputs to be converted into executable commands.
 * <p>
 * Handles various command types such as adding tasks,
 * marking/unmarking tasks, deleting tasks, and listing tasks.
 */
public class Parser {
    private static final String TO_DO_PREFIX = "todo";
    private static final String DEADLINE_PREFIX = "deadline";
    private static final String EVENT_PREFIX = "event";

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param line The user input to be parsed.
     * @return The command corresponding to the user input.
     * @throws DukeException If the input is empty or invalid.
     */
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

    /**
     * Parses task-related command input (todo, deadline, event).
     *
     * @param line The user input to be parsed.
     * @return The command corresponding to the task input.
     * @throws DukeException If the task format is invalid.
     */
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

    /**
     * Handles the parsing of a todo task input.
     *
     * @param line The user input containing the todo prefix.
     * @return A `ToDo` object representing the todo task.
     * @throws DukeException If the task description is empty.
     */
    private static ToDo handleToDo(String line) throws DukeException {
        String description = line.replace(TO_DO_PREFIX, "").trim();

        if (description.isEmpty()) {
            throw new DukeException("Please provide a description for the " +
                    "to-do task.");
        }

        return new ToDo(description);
    }

    /**
     * Handles the parsing of a deadline task input.
     *
     * @param line The user input containing the deadline prefix.
     * @return A `Deadline` object representing the deadline task.
     * @throws DukeException If the input format is invalid or missing required fields.
     */
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

    /**
     * Handles the parsing of an event task input.
     *
     * @param line The user input containing the event prefix.
     * @return An `Event` object representing the event task.
     * @throws DukeException If the input format is invalid or missing required fields.
     */
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

    /**
     * Extracts a substring from the input line based on the specified
     * indices and removes the prefix.
     *
     * @param line The input line to extract from.
     * @param firstIndex The starting index of the substring.
     * @param lastIndex The ending index of the substring.
     * @param prefix The prefix to remove from the substring.
     * @return The extracted and trimmed substring.
     */
    private static String extractString(String line, int firstIndex,
                                        int lastIndex, String prefix) {
        return line.substring(firstIndex, lastIndex)
                .replace(prefix, "").trim();
    }

    /**
     * Extracts a substring from the input line starting at the
     * specified index and removes the prefix.
     *
     * @param line The input line to extract from.
     * @param index The starting index of the substring.
     * @param prefix The prefix to remove from the substring.
     * @return The extracted and trimmed substring.
     */
    private static String extractLastString(String line, int index, String prefix) {
        return line.substring(index).replace(prefix, "").trim();
    }
}
