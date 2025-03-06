package rc.task;

/**
 * Represents an `Event` subclass inheriting the properties of the task
 * superclass.
 * <p>
 * Identified by the capital letter E.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an `Event` task with the specified description, start time
     * and end time.
     *
     * @param description The description of the `Event` task.
     * @param start The time when the event starts
     * @param end The time when the event ends
     */
    public Event(String description, String start, String end) {
        super(description);
        setFrom(start);
        setTo(end);
    }

    /**
     * Returns the start time of the event.
     *
     * @return start time
     */
    public String getFrom() {
        return from;
    }

    /**
     * Sets the start time.
     *
     * @param start The start time
     */
    public void setFrom(String start) {
        from = start;
    }

    /**
     * Returns the end time of the event.
     *
     * @return end time
     */
    public String getTo() {
        return to;
    }

    /**
     * Sets the end time.
     *
     * @param end The end time
     */
    public void setTo(String end) {
        to = end;
    }

    /**
     * Returns a string representation of the `Event` task.
     * <p>
     * The format includes the task type `[E]`, the event's description,
     * its status, and the start and end times
     * <p>
     * (e.g., [E][X] Team meeting (from: 2pm to: 4pm)).
     *
     * @return A string representation of the `Event` task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: "
                + getTo() + ")";
    }

    /**
     * Returns a formatted string representation of the `Event` task
     * for saving to a file.
     * <p>
     * The format includes the task type `E`, the task's status, its description,
     * and the start and end times
     * <p>
     * (e.g., E | 1 | Team meeting | 2pm | 4pm).
     *
     * @return A string representation of the `Event` task in file format.
     */
    @Override
    public String toFileFormat() {
        return "E" + super.toFileFormat() + " | " + getFrom() + " | "
                + getTo();
    }
}
