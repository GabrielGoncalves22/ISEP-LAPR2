package app.core.domain.shared.exception;

/**
 * Class to throw an exception when a date is invalid (at Run Time).
 *
 * @author Group 40
 */
public class InvalidDateException extends RuntimeException {

    /**
     * Constructor to throw a default message.
     */
    public InvalidDateException() {
        super("The date is invalid!");
    }

    /**
     * Constructor to throw a message showing the inserted date.
     *
     * @param actualParameter The inserted date.
     */
    public InvalidDateException(String actualParameter) {
        super("The date \"" + actualParameter + "\" is invalid!");
    }

}
