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

/**
 * Handles reading from and writing to a file to persist task data.
 * Manages the creation of the file and directory if they do not exist,
 * and provides methods to load tasks from the file and save tasks to the file.
 */
public class Storage {
    private final TaskList taskList;
    private final String filePath;

    /**
     * Constructs a new `Storage` object with the specified file path.
     * Constructs a new `TaskList` instance
     *
     * @param path The file path where tasks are stored.
     */
    public Storage(String path) {
        taskList = new TaskList();
        filePath = path;
    }

    /**
     * Writes all tasks from the task list to the file.
     * <p>
     * If an I/O error occurs, an error message will be displayed.
     */
    public void writeTaskToFile() {
        try {
            writeToFile();
        } catch (IOException error) {
            UI.printErrorMessage(error);
        }
    }

    /**
     * Writes all tasks from the task list to the file in the
     * file format specified in the Task class.
     *
     * @throws IOException If an I/O error occurs while writing to file.
     */
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

    /**
     * Loads tasks from the file into the task list.
     * <p>
     * If the file or directory does not exist, they are created.
     * <p>
     * Displays an error message if the file is not found.
     */
    public void loadTasksFromFile() {
        try {
            printFileContents();
        } catch (FileNotFoundException error) {
            UI.print("File is not found.");
        }
    }

    /**
     * Reads the contents of the file and parses each line into a task.
     * <p>
     * If the file or directory does not exist, they are created.
     *
     * @throws FileNotFoundException If the file does not exist and cannot be created.
     * @throws DukeException To display its error message
     * @throws IOException To catch I/O errors
     */
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

    /**
     * Parses a line from the text file into a task and adds it to
     * the task list.
     *
     * @param taskData The line from the file representing a task.
     * @throws DukeException If the task format is invalid.
     */
    private void parseAndAddTask(String taskData) throws DukeException {
        // split task string in file into an array of substrings
        // e.g., T | 0 | wake up => ["T", "0", "wake up"]
        String[] parts = taskData.split(" \\| ");

        // parse each task from existing file
        Task task = parseTaskFromFile(parts);

        // add task to tasks list
        taskList.addTask(task);
    }

    /**
     * Parses the parts of a task line into a `Task` object.
     *
     * @param parts The array of strings representing the task components.
     * @return `Task` object that is parsed.
     * @throws DukeException If the task type is invalid or the format is incorrect.
     */
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

    /**
     * Retrieves a specific part from the array of task components.
     *
     * @param parts The array of strings representing the task components.
     * @param index The index of the part to retrieve.
     * @return The part at the specified index.
     */
    private static String getPart(String[] parts, int index) {
        return parts[index];
    }
}
