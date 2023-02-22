package app.core.domain.shared.exception;

/**
 * Class to throw an exception when some line in a file isn't valid (at Run Time).
 *
 * @author Group 40
 */
public class InvalidLineInFileException extends RuntimeException {

    /**
     * Constructor to throw a default message.
     */
    public InvalidLineInFileException() {
        super("An error was found in some line of the file!");
    }

    /**
     * Constructor to throw a message indicating the line in the file where the error was found.
     *
     * @param line The line of the file with error.
     */
    public InvalidLineInFileException(int line) {
        super("An error was found in line " + line + " of the file! Please, correct it!");
    }

    /**
     * Constructor to throw a personalized message.
     *
     * @param message The personalized message.
     */
    public InvalidLineInFileException(String message) {
        super(message);
    }

    /**
     * Constructor to throw a message indicating the line in the file where the error was found and in which attribute.
     *
     * @param line      The line of the file with error.
     * @param attribute The attribute with error.
     */
    public InvalidLineInFileException(int line, String attribute) {
        super("An error was found in line " + line + " of the file! " + attribute + " is incorrect! Please, " +
                "correct it!");
    }

}
