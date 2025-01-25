import java.sql.SQLOutput;
import java.util.Scanner;

import org.w3c.dom.ls.LSInput;

public class RC_app {
    public static void greet() {
        Scanner input = new Scanner(System.in);
        String line;

        System.out.println("Good day! I'm RC, your personal chatbot.");
        System.out.println("You need my assistance today?\n");
        
        do {
            System.out.print("User says: ");
            line = input.nextLine();
            System.out.println("RC says: " + line + "\n");
        } while((!line.equals("bye")));

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
        greet();
    }
}
