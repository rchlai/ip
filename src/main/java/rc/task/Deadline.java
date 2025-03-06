package rc.task;

/**
 * Represents a `Deadline` subclass inheriting the properties of the task
 * superclass.
 * <p>
 * Identified by the capital letter D.
 */
public class Deadline extends Task {
    protected String by;

    /**
     * Constructs a `Deadline` task with the specified description and
     * due time.
     *
     * @param description The description of the `Deadline` task.
     * @param due The due time of the task
     */
    public Deadline(String description, String due) {
        super(description);
        setBy(due);
    }

    /**
     * Returns the deadline due time.
     *
     * @return the due time
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the deadline due time.
     *
     * @param due The due time of the deadline task
     */
    public void setBy(String due) {
        by = due;
    }

    /**
     * Returns a string representation of the `Deadline` task.
     * <p>
     * The format includes the task type `[D]` followed by the task's status,
     * description, and due time (e.g., [D][X] Submit work (by: Monday 2pm)).
     * </p>
     *
     * @return A string representation of the `Deadline` task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    /**
     * Returns a formatted string representation of the `Deadline` task when
     * saving it to a file.
     * <p>
     * The format includes the task type `D` followed by the task's status,
     * description, and due time (e.g., `D | 1 | Buy groceries | Monday 2pm`).
     * </p>
     *
     * @return A string representation of the `Deadline` task in file format.
     */
    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + getBy();
    }
}
