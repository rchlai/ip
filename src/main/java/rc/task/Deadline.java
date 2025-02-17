package rc.task;

public class Deadline extends Task {
    protected String by;

    public Deadline(String description, String due) {
        super(description);
        setBy(due);
    }

    public String getBy() {
        return by;
    }

    public void setBy(String due) {
        by = due;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + getBy() + ")";
    }

    @Override
    public String toFileFormat() {
        return "D" + super.toFileFormat() + " | " + getBy();
    }
}
