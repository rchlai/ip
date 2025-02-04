public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        setDescription(description);
        markAsNotDone();
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsNotDone() {
        isDone = false;
    }

//    public void addTask() {
//        this.texts[textCount] = this.description;
//        textCount++;
//    }
//
//    public void listTask() {
//        System.out.println("RC added: " + getDescription() + "\n");
//    }
//
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
