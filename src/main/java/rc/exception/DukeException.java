package rc.exception;

/**
 * The `DukeException` class is a custom exception used to handle errors
 * specific to the RC chatbot application.
 * <p>
 * It extends the `Exception` class and provides a way to pass error
 * messages to the caller.
 */
public class DukeException extends Exception {
    /**
     * Constructs a new `DukeException` with the specified error message.
     *
     * @param message The error message describing the exception.
     */
    public DukeException(String message) {
        super(message);
    }
}
