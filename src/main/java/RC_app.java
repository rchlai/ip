import java.sql.SQLOutput;

public class RC_app {
    public static void greet() {
        System.out.println("Hello! I'm RC");
        System.out.println("What can I do for you?");
        System.out.println("\n");
        System.out.println("Bye. Hope to see you again soon!");;
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
