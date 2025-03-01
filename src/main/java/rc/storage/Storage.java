package rc.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import rc.exception.DukeException;
import rc.tasklist.TaskList;
import rc.ui.UI;
import rc.task.Deadline;
import rc.task.Event;
import rc.task.Task;
import rc.task.ToDo;

public class Storage {
    private final TaskList taskList;
    private final String filePath;

    public Storage(String path) {
        taskList = new TaskList();
        filePath = path;
    }

    public void writeTaskToFile() {
        try {
            writeToFile();
        } catch (IOException error) {
            UI.printErrorMessage(error);
        }
    }

    public void writeToFile() throws IOException {
        FileWriter writer = new FileWriter(filePath);
        ArrayList<Task> tasks = taskList.getTaskList();
        // write each task from arrayList to rc.txt in file format
        for (Task task: tasks) {
            writer.write(task.toFileFormat() + "\n");
        }
        // complete the writing operation
        writer.close();
    }

    public void loadTasksFromFile() {
        try {
            printFileContents();
        } catch (FileNotFoundException error) {
            UI.print("File is not found.");
        }
    }

    private void printFileContents() throws FileNotFoundException {
        File file = new File(filePath);
        File parentFolder = file.getParentFile();

        try {
            // ensure the folder exists before trying to create file
            if (parentFolder != null && !parentFolder.exists()) {
                // create a new folder
                if (parentFolder.mkdirs()) {
                    UI.print("Creating parent folder at: " + parentFolder.getPath());
                }
                // no file to create if the folder was just created
                return;
            }

            // checking existing data file
            if (!file.exists()) {
                if (file.createNewFile()) {
                    UI.print("No existing data file found. " +
                            "Creating a new one at: " + filePath);
                }
                // No tasks to load if file was just created
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseAndAddTask(line);
            }
        } catch (DukeException | IOException error) {
            UI.printErrorMessage(error);
        } finally {
            UI.addDivider();
        }
    }

    private void parseAndAddTask(String taskData) throws DukeException {
        // split task string in file into an array of substrings
        // e.g., T | 0 | wake up => ["T", "0", "wake up"]
        String[] parts = taskData.split(" \\| ");

        // parse each task from existing file
        Task task = parseTaskFromFile(parts);

        // add task to tasks list
        taskList.addTask(task);
    }

    private Task parseTaskFromFile(String[] parts) throws DukeException {
        String taskType = getPart(parts, 0);
        boolean isDone = getPart(parts, 1).equals("1");
        String description = getPart(parts, 2);

        Task task;
        switch (taskType) {
        case "T":
            // to-do task format after splitting:
            // ["T", "0/1", "<description>"]
            task = new ToDo(description);
            break;
        case "D":
            // deadline task format after splitting:
            // ["D", "0/1", "<description>", "<dueDate>"]
            String dueDate = getPart(parts, 3);
            task = new Deadline(description, dueDate);
            break;
        case "E":
            // event task format after splitting:
            // ["E", "0/1", "<description>", "<start>", "<end>"]
            String start = getPart(parts, 3);
            String end = getPart(parts, 4);
            task = new Event(description, start, end);
            break;
        default:
            throw new DukeException("Invalid task format.");
        }

        // mark task as done in program if isDone == 1 (true)
        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    private static String getPart(String[] parts, int index) {
        return parts[index];
    }
}
