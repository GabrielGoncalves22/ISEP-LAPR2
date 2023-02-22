package domain.model;

import app.core.domain.model.Time;
import org.junit.Test;

/**
 * Represents the tests of the Time class.
 */
public class TimeTests {

    /**
     * Check that it is not possible to create an instance of the Vaccine Center class with the hour values can only be between 0 and 24.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureReferenceMeetsAC4a() {
        new Time(41, 12);
    }

    /**
     * Check that it is not possible to create an instance of the Vaccine Center class with the minutes and seconds values between 0 and 60.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureReferenceMeetsAC4b() {
        new Time(12, 421);
    }
}
