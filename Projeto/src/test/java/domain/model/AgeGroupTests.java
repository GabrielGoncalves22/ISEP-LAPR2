package domain.model;

import app.core.domain.model.AgeGroup;
import org.junit.Test;

/**
 * Represents the tests of the AgeGroup class.
 */
public class AgeGroupTests {

    /**
     * Check that it is not possible to create an instance of the AgeGroup class with the minimum age be greater than the maximum age
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureReferenceMeetsAC3() {
        new AgeGroup(30, 20);
    }
}
