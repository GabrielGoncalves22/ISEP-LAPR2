package domain.model;

import app.core.domain.model.Vaccine;
import org.junit.Test;

/**
 * Represents the tests of the AgeGroup class.
 */
public class VaccineTests {

    /**
     * Check that it is not possible to create an instance of the Vaccine class with null values.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        new Vaccine(null, null, null);
    }

    /**
     * Check that it is not possible to create an instance of the Vaccine class with an empty vaccine type.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureReferenceMeetsAC2() {
        new Vaccine("Pfizer", "Pfizer, Inc", null);
    }
}
