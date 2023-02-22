package app.core.domain.shared.exception;

/**
 * Class to throw an exception when a time is invalid (at Run Time).
 *
 * @author Group 40
 */
public class InvalidTimeException extends RuntimeException {

    /**
     * Constructor to throw a default message.
     */
    public InvalidTimeException() {
        super("The time is invalid!");
    }

    /**
     * Constructor to throw a message showing the inserted time.
     *
     * @param actualParameter The inserted time.
     */
    public InvalidTimeException(String actualParameter) {
        super("The time \"" + actualParameter + "\" is invalid!");
    }

}
