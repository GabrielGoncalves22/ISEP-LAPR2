package app.core.domain.shared.exception;

/**
 * Class to throw an exception when some data about VaccineAdministration isn't valid.
 *
 * @author Group 40
 */
public class InvalidVaccineAdministration extends IllegalArgumentException {

    /**
     * Constructor to throw a default message.
     */
    public InvalidVaccineAdministration() {
        super("Invalid vaccine administration!");
    }

    /**
     * Constructor to throw a personalized message.
     *
     * @param message The personalized message.
     */
    public InvalidVaccineAdministration(String message) {
        super(message);
    }
}
