package domain.model;

import app.core.domain.model.Time;
import app.core.domain.model.VaccinationCenter;
import org.junit.Test;
import pt.isep.lei.esoft.auth.domain.model.Email;

/**
 * Represents the tests of the VaccinationCenter class.
 */
public class VaccinationCenterTests {

    /**
     * Check that it is not possible to create an instance of the Vaccine center class with null values.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureNullIsNotAllowed() {
        new VaccinationCenter(null, null, 0, null, 0, null, null, null, 0, 0);
    }

    /**
     * Check that it is not possible to create an instance of the Vaccine Center class with the phone and fax number cannot be longer than 9 digits.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureReferenceMeetsAC2() {
        Time openingHours = new Time(9, 0);
        Time closingHours = new Time(18,  0);

        new VaccinationCenter("Ramalde Lda", "R. Eng. Ferreira Dias 874, 4100-246 Porto", 1234789, new Email("jumpjump@ramaldejump.pt"), 1234689, "https://www.jumpers.pt/pt/", openingHours, closingHours, 5, 10);
    }

    /**
     * Check that it is not possible to create an instance of the Vaccine Center class with the value of slot duration and slot Vaccine Limit cannot be negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public void ensureReferenceMeetsAC3() {
        Time openingHours = new Time(9, 0);
        Time closingHours = new Time(18,  0);

        new VaccinationCenter("Ramalde Lda", "R. Eng. Ferreira Dias 874, 4100-246 Porto", 918273645, new Email("jumpjump@ramaldejump.pt"), 123456789, "https://www.jumpers.pt/pt/", openingHours, closingHours, -14, -10);
    }
}
