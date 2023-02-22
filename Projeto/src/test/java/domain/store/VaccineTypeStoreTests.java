package domain.store;

import app.core.domain.model.VaccineType;
import app.core.domain.store.VaccineTypeStore;
import org.junit.Test;
import org.junit.Assert;

/**
 * Represents the tests of the VaccineTypeStore class.
 */
public class VaccineTypeStoreTests {
    private static VaccineTypeStore vaccineTypeStore = new VaccineTypeStore();

    /**
     * This test will test the function of creating a vaccine type.
     */
    @Test
    public void testCreateVaccineType() {
        VaccineType vaccineType1 = vaccineTypeStore.createVaccineType("1ABCD", "Covid-19", "Viral vector");
        VaccineType vaccineType2 = vaccineTypeStore.createVaccineType("1ZYXW", "Tetanus", "Toxoid");
        VaccineType vaccineType3 = vaccineTypeStore.createVaccineType("1HIJK", "Ebola ", "Viral vector");

        VaccineType expectedResult1 = new VaccineType("1ABCD", "Covid-19", "Viral vector");
        VaccineType expectedResult2 = new VaccineType("1ZYXW", "Tetanus", "Toxoid");
        VaccineType expectedResult3 = new VaccineType("1HIJK", "Ebola ", "Viral vector");

        Assert.assertEquals(expectedResult1, vaccineType1);
        Assert.assertEquals(expectedResult2, vaccineType2);
        Assert.assertEquals(expectedResult3, vaccineType3);
    }

    /**
     * This test will test the function of validating a vaccine type.
     */
    @Test
    public void testValidateVaccineType() {
        boolean test1 = vaccineTypeStore.validateVaccineType(null);
        boolean test2 = vaccineTypeStore.validateVaccineType(new VaccineType("1ABCD", "Covid-19", "Viral vector"));
        boolean test3 = vaccineTypeStore.validateVaccineType(new VaccineType("1ZYXW", "Tetanus", "Toxoid"));

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
    }

    /**
     * This test will test the function of saving a vaccine type.
     */
    @Test
    public void testSaveVaccineType() {
        boolean test1 = vaccineTypeStore.saveVaccineType(null);
        boolean test2 = vaccineTypeStore.saveVaccineType(new VaccineType("1ABCD", "Covid-19", "Viral vector"));
        boolean test3 = vaccineTypeStore.saveVaccineType(new VaccineType("1ABCD", "Covid-19", "Viral vector"));

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertFalse(test3);
    }
}
