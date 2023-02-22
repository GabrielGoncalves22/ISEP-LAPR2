package domain.model;

import app.core.domain.model.ScheduleVaccine;
import domain.shared.CommonConstants;
import org.junit.Test;
import org.junit.Assert;

/**
 * Class to test the methods of the ScheduleVaccine class
 *
 * @author Group 40
 */
public class ScheduleVaccineTests implements CommonConstants {

    @Test
    public void testCompareTo() {
        int comparation1 = SCHEDULE_VACCINE_1.compareTo(SCHEDULE_VACCINE_2);
        int comparation2 = SCHEDULE_VACCINE_2.compareTo(SCHEDULE_VACCINE_3);

        Assert.assertEquals(comparation1, 1);
        Assert.assertEquals(comparation2, 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testNull() {
        ScheduleVaccine sv = new ScheduleVaccine(null, null,null,null,null);

    }
}
