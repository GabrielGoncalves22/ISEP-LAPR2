package app.core.domain.shared.exception;

/**
 * Class to throw an exception when some line in a file of the legacy system isn't valid (at Run Time).
 *
 * @author Group 40
 */
public class InvalidDataInLegacySystemFileException extends RuntimeException {

    /**
     * Constructor to throw a default message.
     */
    public InvalidDataInLegacySystemFileException() {
        super("Invalid register found!");
    }

    /**
     * Constructor to throw a message indicating the line in the file where the error was found, and a custom message.
     *
     * @param line    The line of the file where the error was found.
     * @param message The custom message.
     */
    public InvalidDataInLegacySystemFileException(int line, String message) {
        super("There is a invalid register in line " + line + " of the file! " + message);
    }

}
