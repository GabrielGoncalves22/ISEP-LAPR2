package domain.model;

import app.core.domain.model.Dose;
import org.junit.Test;

/**
 * Represents the tests of the Dose class.
 */
public class DoseTests {

    /**
     * Check that it is not possible to create an instance of the Dose class with a dose number or dose less than or equal to zero.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureReferenceMeetsAC5() {
        new Dose(0, 0, 25);
    }
}
