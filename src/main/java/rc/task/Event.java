package rc.task;

public class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String start, String end) {
        super(description);
        setFrom(start);
        setTo(end);
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String start) {
        from = start;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String end) {
        to = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + getFrom() + " to: "
                + getTo() + ")";
    }
}
