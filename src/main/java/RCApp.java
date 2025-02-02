import java.util.Scanner;

public class RCApp {
    public static void chatWithBot() {
        Scanner input = new Scanner(System.in);
        String line;
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("Good day! I'm RC, your personal chatbot.");
        System.out.println("You need my assistance today?\n");

        System.out.print("User says: ");
        line = input.nextLine();

        while (!line.equals("bye")) {
            if (line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    // displays as 1.[X] read book and so on
                    System.out.println((i + 1) + "." + "[" + tasks[i].getStatusIcon()
                            + "] " + tasks[i].getDescription());
                }
                // create an empty line to avoid text clutter
                System.out.println();
            } else if (line.startsWith("mark")) {
                // extracts digits from string to be converted into integer type
                int markIndex = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                // use markIndex to mark task from Task[] as done
                tasks[markIndex - 1].markAsDone();
                System.out.println("Good job! I'll mark this task as done:");
                // displays marked task
                System.out.println(markIndex + "." + "[" + tasks[markIndex - 1].getStatusIcon()
                        + "] " + tasks[markIndex - 1].getDescription() + "\n");
            } else if (line.startsWith("unmark")) {
                int unmarkIndex = Integer.parseInt(line.replaceAll("[^0-9]", ""));
                // use unmarkIndex to mark task from Task[] as not done
                tasks[unmarkIndex - 1].markAsNotDone();
                System.out.println("Noted, I've marked this task as not done yet:");
                // displays unmarked task
                System.out.println(unmarkIndex + "." + "[" + tasks[unmarkIndex - 1].getStatusIcon()
                        + "] " + tasks[unmarkIndex - 1].getDescription() + "\n");
            } else {
                // create a new Task instance
                Task t = new Task(line);
                System.out.println("RC added: " + t.getDescription() + "\n");
                // prompt user to write next command
                tasks[taskCount] = t;
                taskCount++;
            }

            // prompt user to write next command
            System.out.print("User says: ");
            line = input.nextLine();
        };

        System.out.println("Goodbye. Hope I satisfy your needs for today!");;
    }

    public static void main(String[] args) {
        String logo =
        "______________\n"
        +"\\____  \\_  __ \\\n"
        +"|    _/    \\ \\/\n"
        +"|  |  \\     \\__\n"
        +"|__|__/\\______/\n";

        System.out.println("Hello from\n" + logo);
        chatWithBot();
    }
}
