package rc.task;

/**
 * Represents a `ToDo` subclass inheriting the properties of the task
 * superclass.
 * <p>
 * Identified by the capital letter T.
 */
public class ToDo extends Task {
    /**
     * Constructs a `ToDo` task with the specified description.
     *
     * @param description The description of the `ToDo` task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the `ToDo` task.
     * <p>
     * The format includes the task type `[T]` followed by the task's description
     * and status (e.g., `[T][X] Buy groceries`).
     * </p>
     *
     * @return A string representation of the `ToDo` task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Returns a formatted string representation of the `ToDo` task when
     * saving it to a file.
     * <p>
     * The format includes the task type `T` followed by the task's status and
     * description (e.g., `T | 1 | Buy groceries`).
     * </p>
     *
     * @return A string representation of the `ToDo` task in file format.
     */
    @Override
    public String toFileFormat() {
        return "T" + super.toFileFormat();
    }
}
