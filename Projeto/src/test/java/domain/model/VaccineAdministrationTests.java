package domain.model;

import app.core.domain.model.VaccineAdministration;
import domain.shared.CommonConstants;
import org.junit.Test;

/**
 * Represents the tests of the VaccineAdministration class.
 */
public class VaccineAdministrationTests implements CommonConstants {

    /**
     * Check that it is not possible to create an instance of the VaccineAdministration class with null values.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        new VaccineAdministration(null, null, null, null, null,
                null, null, null, null);
    }

    /**
     * Check that it is not possible to create an instance of the VaccineAdministration class without a leaving time smaller than the administration time.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckTime() {
        new VaccineAdministration(SNS_USER1, VACCINE1, DOSE1, ADMINISTRATION_DATE1, ADMINISTRATION_TIME1,
                LEAVING_DATE1, LEAVING_TIME1, VACCINATION_CENTER, "AA123-55");
    }

    /**
     * Check that it is not possible to create an instance of the VaccineAdministration class without an valid lot number.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckLotNumber() {
        new VaccineAdministration(SNS_USER1, VACCINE1, DOSE1, ADMINISTRATION_DATE1, ADMINISTRATION_TIME2,
                LEAVING_DATE1, LEAVING_TIME2, VACCINATION_CENTER, "123-AA");
    }
}
