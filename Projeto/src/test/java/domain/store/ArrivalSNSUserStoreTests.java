package domain.store;

import app.core.dto.ArrivalSNSUserDTO;
import app.core.domain.model.ArrivalSNSUser;
import app.core.domain.model.Date;
import app.core.domain.model.Time;
import app.core.domain.store.ArrivalSNSUserStore;
import domain.shared.CommonConstants;
import org.junit.Test;
import org.junit.Assert;

/**
 * Represents the tests of the ArrivalSNSUserStore class.
 */
public class ArrivalSNSUserStoreTests implements CommonConstants {
    private static ArrivalSNSUserStore arrivalSNSUserStore = new ArrivalSNSUserStore();

    /**
     * This test will test the function of creating a arrival SNS user.
     */
    @Test
    public void testCreateArrivalSNSUser() {
        ArrivalSNSUser arrivalSNSUser1 = arrivalSNSUserStore.createArrivalSNSUser(new ArrivalSNSUserDTO(SNS_USER1, new Date(2022, 12, 12),
                new Time(10, 25, 10), VACCINATION_CENTER));
        ArrivalSNSUser arrivalSNSUser2 = arrivalSNSUserStore.createArrivalSNSUser(new ArrivalSNSUserDTO(SNS_USER2, new Date(2022, 12, 12),
                new Time(12, 30, 15), VACCINATION_CENTER));
        ArrivalSNSUser arrivalSNSUser3 = arrivalSNSUserStore.createArrivalSNSUser(new ArrivalSNSUserDTO(SNS_USER3, new Date(2022, 12, 12),
                new Time(14, 45, 20), VACCINATION_CENTER));

        ArrivalSNSUser expectedResult1 = new ArrivalSNSUser(SNS_USER1, new Date(2022, 12, 12),
                new Time(10, 25, 10), VACCINATION_CENTER);
        ArrivalSNSUser expectedResult2 = new ArrivalSNSUser(SNS_USER2, new Date(2022, 12, 12),
                new Time(12, 30, 15), VACCINATION_CENTER);
        ArrivalSNSUser expectedResult3 = new ArrivalSNSUser(SNS_USER3, new Date(2022, 12, 12),
                new Time(14, 45, 20), VACCINATION_CENTER);

        Assert.assertEquals(expectedResult1, arrivalSNSUser1);
        Assert.assertEquals(expectedResult2, arrivalSNSUser2);
        Assert.assertEquals(expectedResult3, arrivalSNSUser3);
    }

    /**
     * This test will test the function of validating a arrival SNS user.
     */
    @Test
    public void testValidateArrivalSNSUserTest() {
        boolean test1 = arrivalSNSUserStore.validateArrivalSNSUser(null);
        boolean test2 = arrivalSNSUserStore.validateArrivalSNSUser(new ArrivalSNSUser(SNS_USER1, new Date(2022, 12, 12),
                new Time(10, 25, 10), VACCINATION_CENTER));
        boolean test3 = arrivalSNSUserStore.validateArrivalSNSUser(new ArrivalSNSUser(SNS_USER2, new Date(2022, 12, 12),
                new Time(12, 30, 15), VACCINATION_CENTER));

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
    }

    /**
     * This test will test the function of validating a arrival SNS user.
     */
    @Test
    public void testCheckDuplicates() {
        boolean test1 = arrivalSNSUserStore.checkDuplicates(new ArrivalSNSUser(SNS_USER1, new Date(2022, 12, 12),
                new Time(10, 25, 10), VACCINATION_CENTER));
        boolean test2 = arrivalSNSUserStore.checkDuplicates(new ArrivalSNSUser(SNS_USER2, new Date(2022, 12, 12),
                new Time(12, 30, 15), VACCINATION_CENTER));
        boolean test3 = arrivalSNSUserStore.checkDuplicates(new ArrivalSNSUser(SNS_USER3, new Date(2022, 12, 12),
                new Time(14, 45, 20), VACCINATION_CENTER));

        Assert.assertFalse(test1);
        Assert.assertFalse(test2);
        Assert.assertFalse(test3);
    }

    /**
     * This test will test the function of saving a arrival SNS user.
     */
    @Test
    public void testSaveArrivalSNSUser() {
        boolean test1 = arrivalSNSUserStore.saveArrivalSNSUser(null);
        boolean test2 = arrivalSNSUserStore.saveArrivalSNSUser(new ArrivalSNSUser(SNS_USER1, new Date(2022, 12, 12),
                new Time(10, 25, 10), VACCINATION_CENTER));
        boolean test3 = arrivalSNSUserStore.saveArrivalSNSUser(new ArrivalSNSUser(SNS_USER2, new Date(2022, 12, 12),
                new Time(12, 30, 15), VACCINATION_CENTER));

        Assert.assertFalse(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
    }
}
