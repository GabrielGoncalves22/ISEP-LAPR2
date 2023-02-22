package app.core.domain.shared.exception;

/**
 * Class to throw an exception when some data about SNSUser present in a list isn't valid (at Run Time). This list with
 * SNSUser data was read in a file.
 *
 * @author Group 40
 */
public class InvalidSNSUserInListException extends RuntimeException {

    /**
     * Constructor to throw a default message.
     */
    public InvalidSNSUserInListException() {
        super("Invalid register found!");
    }

    /**
     * Constructor to throw a message indicating the line in the file where the error was found.
     *
     * @param line The line of the file with error.
     */
    public InvalidSNSUserInListException(int line) {
        super("There is a invalid register in line " + line + " (only counting lines with SNS User data, discarding header) of the file!");
    }

    /**
     * Constructor to throw a personalized message.
     *
     * @param message The personalized message.
     */
    public InvalidSNSUserInListException(String message) {
        super(message);
    }

    /**
     * Constructor to throw a message indicating the line in the file where the error was found and the error message
     * thrown.
     *
     * @param line         The line of the file with error.
     * @param errorMessage The error message thrown.
     */
    public InvalidSNSUserInListException(int line, String errorMessage) {
        super("There is a invalid register in line " + line + " (only counting lines with SNS User data, discarding " +
                "header) of the file! Error details: " + errorMessage);
    }
}
