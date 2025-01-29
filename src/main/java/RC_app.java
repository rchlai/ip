import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Scanner;

import org.w3c.dom.ls.LSInput;

public class RC_app {
    public static void chatWithBot() {
        Scanner input = new Scanner(System.in);
        String line;
        int markIndex;
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("Good day! I'm RC, your personal chatbot.");
        System.out.println("You need my assistance today?\n");

        System.out.print("User says: ");
        line = input.nextLine();

        while(!line.equals("bye")) {
            if(line.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for(int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + "." + "[" + tasks[i].getStatusIcon() + "] " + tasks[i].getDescription()); // displays as 1.[X] read book and so on
                }
                System.out.println(); // create an empty line to avoid text clutter
            } else if(line.contains("mark")) {
                markIndex = Integer.parseInt(line.replaceAll("[^0-9]", "")); // extracts digits from string to be converted into integer type
                tasks[markIndex - 1].markAsDone(); // use markIndex to mark selected task from Task[] as done
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(markIndex + "." + "[" + tasks[markIndex - 1].getStatusIcon() + "] " + tasks[markIndex - 1].getDescription() + "\n"); // displays marked task
            } else {
                Task t = new Task(line); // create a new Task instance
                System.out.println("RC added: " + t.getDescription() + "\n");
                tasks[taskCount] = t; // add Task instance into Task[]
                taskCount++;
            }

            System.out.print("User says: "); // prompt user to write next command
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
