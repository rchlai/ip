package rc.task;

/**
 * Represents a task with a description and a completion status.
 * <p>
 * It serves as the base class for specific task types like
 * `ToDo`, `Deadline`, and `Event`.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new `Task` with the specified description.
     * The task is initially marked as not done.
     *
     * @param description The description of the task.
     */
    public Task(String description) {
        setDescription(description);
        markAsNotDone();
    }

    /**
     * Returns the status icon of the task, indicating whether it is
     * done or not.
     *
     * @return "X" if the task is done, otherwise a blank space " ".
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the task description.
     *
     * @return description
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description of the task.
     *
     * @param description The new description of the task.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone() {
        isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task, including its
     * status icon and description.
     *
     * @return The formatted string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }

    /**
     * Returns a string representation of the task in a file storage format.
     *
     * @return The task in file format, including its completion status and description.
     */
    public String toFileFormat() {
        return " | " + (isDone ? "1" : "0") + " | " + getDescription();
    }
}
