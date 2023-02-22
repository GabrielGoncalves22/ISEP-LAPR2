package domain.model;

import app.core.domain.model.VaccineType;
import org.junit.Test;

/**
 * Represents the tests of the VaccineType class.
 */
public class VaccineTypeTests {

    /**
     * Check that it is not possible to create an instance of the VaccineType class with null values.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        new VaccineType(null, null, null);
    }

    /**
     * Check that it is not possible to create an instance of the VaccineType class without a code containing five alphanumeric characters.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureReferenceMeetsAC2() {
        new VaccineType("1A", "Covid-19", "Live-attenuated vaccines");
    }

    /**
     * Check that it is not possible to create an instance of the VaccineType class with a esignation containing more than forty chars.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureReferenceMeetsAC3() {
        new VaccineType("1ABCD", "Covid-19-Covid-19-Covid-19-Covid-19-Covid-19", "Live-attenuated vaccines");
    }
}
