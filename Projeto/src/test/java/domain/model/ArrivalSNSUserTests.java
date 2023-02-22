package domain.model;

import app.core.domain.model.ArrivalSNSUser;
import app.core.domain.model.Date;
import app.core.domain.model.Time;
import domain.shared.CommonConstants;
import org.junit.Test;

/**
 * Class to test the methods of the ArrivalSNSUser class
 *
 * @author Group 40
 */
public class ArrivalSNSUserTests implements CommonConstants {

    /**
     * Check that it is not possible to create an instance of the ArrivalSNSUser class with null values.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        new ArrivalSNSUser(null, null, null, null);
    }

    /**
     * Tests the checkSNSUser function.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckSNSUser() {
        new ArrivalSNSUser(null, new Date(2022, 12, 12), new Time(10, 25, 10), CommonConstants.VACCINATION_CENTER);
    }

    /**
     * Tests the checkArrivalDate function.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckArrivalDate() {
        new ArrivalSNSUser(CommonConstants.SNS_USER1, null, new Time(10, 25, 10), CommonConstants.VACCINATION_CENTER);
    }

    /**
     * Tests the checkArrivalTime function.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckArrivalTime() {
        new ArrivalSNSUser(CommonConstants.SNS_USER1, new Date(2022, 12, 12), null, CommonConstants.VACCINATION_CENTER);
    }

    /**
     * Tests the checkVaccinationCenter function.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckVaccinationCenter() {
        new ArrivalSNSUser(CommonConstants.SNS_USER1, new Date(2022, 12, 12), new Time(10, 25, 10), null);
    }
}
