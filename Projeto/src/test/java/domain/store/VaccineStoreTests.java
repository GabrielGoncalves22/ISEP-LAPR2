package domain.store;

import app.core.domain.model.Dose;
import app.core.domain.model.AgeGroup;
import app.core.domain.model.Vaccine;
import app.core.domain.model.VaccineType;
import app.core.domain.store.VaccineStore;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the tests of the VaccineStore class.
 */
public class VaccineStoreTests {
    private static VaccineStore vaccineStore = new VaccineStore();

    private static VaccineType vaccineType = new VaccineType("1ABCD", "Covid-19", "Viral vector");

    private static Vaccine vaccine1 = vaccineStore.createVaccine("Pfizer", "Pfizer, Inc.", vaccineType);
    private static Vaccine vaccine2 = vaccineStore.createVaccine("Moderna", "Pfizer, Inc.", vaccineType);
    private static Vaccine vaccine3 = vaccineStore.createVaccine("Johnson & Johnson", "Janssen", vaccineType);

    private static AgeGroup ageGroup1 = vaccineStore.createAgeGroup(10, 25);
    private static AgeGroup ageGroup2 = vaccineStore.createAgeGroup(25, 30);
    private static AgeGroup ageGroup3 = vaccineStore.createAgeGroup(45, 60);

    private static Dose dose1 = vaccineStore.createDose(1, 10, 0);
    private static Dose dose2 = vaccineStore.createDose(2, 15, 10);
    private static Dose dose3 = vaccineStore.createDose(3, 25, 11);

    /**
     * This test will test the function of validating a vaccine.
     */
    @Test
    public void testValidateVaccineType() {
        boolean test1 = vaccineStore.validateVaccine(null);
        boolean test2 = vaccineStore.validateVaccine(new Vaccine("Pfizer", "Pfizer, Inc.", vaccineType));
        boolean test3 = vaccineStore.validateVaccine(new Vaccine("Moderna", "Pfizer, Inc.", vaccineType));
        boolean test4 = vaccineStore.validateVaccine(new Vaccine("Johnson & Johnson", "Janssen", vaccineType));
        boolean test5 = vaccineStore.validateVaccine(new Vaccine("Johnson & Johnson", "Janssen", vaccineType));

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
        Assert.assertTrue(test4);
        Assert.assertTrue(test5);
    }

    /**
     * This test will test the function of creating age group.
     */
    @Test
    public void testCreateAgeGroup() {
        AgeGroup expectedResult1 = new AgeGroup(10, 25);
        AgeGroup expectedResult2 = new AgeGroup(25, 30);
        AgeGroup expectedResult3 = new AgeGroup(45, 60);

        Assert.assertEquals(expectedResult1, ageGroup1);
        Assert.assertEquals(expectedResult2, ageGroup2);
        Assert.assertEquals(expectedResult3, ageGroup3);
    }

    /**
     * This test will test the function of validating age group.
     */
    @Test
    public void testValidateAgeGroup() {
        List<AgeGroup> agList = new ArrayList<>();

        boolean test1 = vaccineStore.validateAgeGroup(agList, null);
        boolean test2 = vaccineStore.validateAgeGroup(agList, ageGroup1);
        boolean test3 = vaccineStore.validateAgeGroup(agList, ageGroup2);
        boolean test4 = vaccineStore.validateAgeGroup(agList, ageGroup3);

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
        Assert.assertTrue(test4);
    }

    /**
     * This test will test the function of creating dose.
     */
    @Test
    public void testCreateDose() {
        Dose expectedResult1 = new Dose(1, 10, 0);
        Dose expectedResult2 = new Dose(2, 15, 10);
        Dose expectedResult3 = new Dose(3, 25, 11);

        Assert.assertEquals(expectedResult1, dose1);
        Assert.assertEquals(expectedResult2, dose2);
        Assert.assertEquals(expectedResult3, dose3);
    }

    /**
     * This test will test the function of validating dose.
     */
    @Test
    public void testValidateDose() {
        List<Dose> apList = new ArrayList<>();

        boolean test1 = vaccineStore.validateDose(apList, null);
        boolean test2 = vaccineStore.validateDose(apList, dose1);
        boolean test3 = vaccineStore.validateDose(apList, dose2);
        boolean test4 = vaccineStore.validateDose(apList, dose3);

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
        Assert.assertTrue(test4);
    }

    /**
     * This test will test the function of saving vacine.
     */
    @Test
    public void testSaveVaccine() {
        boolean test1 = vaccineStore.saveVaccine(null);
        boolean test2 = vaccineStore.saveVaccine(vaccine1);
        boolean test3 = vaccineStore.saveVaccine(vaccine2);
        boolean test4 = vaccineStore.saveVaccine(vaccine3);
        boolean test5 = vaccineStore.saveVaccine(vaccine3);

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
        Assert.assertTrue(test4);
        Assert.assertFalse(test5);
    }
}
