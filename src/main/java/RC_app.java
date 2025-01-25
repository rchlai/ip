import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Scanner;

import org.w3c.dom.ls.LSInput;

public class RC_app {
    public static void chatWithBot() {
        Scanner input = new Scanner(System.in);
        String line;
        String[] texts = new String[100];
        int textCount = 0;

        System.out.println("Good day! I'm RC, your personal chatbot.");
        System.out.println("You need my assistance today?\n");

        System.out.print("User says: ");
        line = input.nextLine();
        while(!line.equals("bye")) {
            if(line.equals("list")) {
                for(int i = 0; i < texts.length; i++) {
                    System.out.println((i + 1) + ". " + texts[i]);
                }
            } else {
                texts[textCount] = line;
                textCount++;
                System.out.println("RC added: " + line + "\n");
            }

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
