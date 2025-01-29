public class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public String getDescription() {
        return this.description;
    }

//    public void setDescription(String description) {
//        this.description = description;
//    }

    public void markAsDone() {
        this.isDone = true;
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
//    public void listAllTasks() {
//        System.out.println("Here are the tasks in your list:");
//        for(int i = 0; i < textCount; i++) {
//            System.out.println((i + 1) + "." + "[" + getStatusIcon() + "] " + texts[i]); // displays as 1.[X] read book and so on
//        }
//    }
}
