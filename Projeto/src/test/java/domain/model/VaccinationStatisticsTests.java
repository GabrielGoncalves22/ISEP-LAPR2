package domain.model;

import app.core.domain.model.Date;
import app.core.domain.model.VaccinationStatistics;
import org.junit.Assert;
import org.junit.Test;

/**
 * Represents the tests of the VaccinationStatistics class.
 */
public class VaccinationStatisticsTests {

    @Test
    public void verifyDateIntervalTest() {

        Date date1 = new Date(2022, 6, 2);
        Date date2 = new Date(2022, 6, 2);
        Date date3 = new Date(2022, 6, 6);
        Date date4 = new Date(2021, 12, 31);
        Date date5 = new Date(2022, 1, 1);
        Date date6 = new Date(2022, 5, 31);
        Date date7 = new Date(2022, 6, 1);
        Date date8 = new Date(2023, 4, 13);

        VaccinationStatistics vs1 = new VaccinationStatistics(date1,date2);
        VaccinationStatistics vs2 = new VaccinationStatistics(date1, date3);
        VaccinationStatistics vs3 = new VaccinationStatistics(date4, date5);
        VaccinationStatistics vs4 = new VaccinationStatistics(date6, date7);
        VaccinationStatistics vs5 = new VaccinationStatistics(date4, date6);
        VaccinationStatistics vs6 = new VaccinationStatistics(date8, date1);
        VaccinationStatistics vs7 = new VaccinationStatistics(date1, date8);

        boolean state1 = vs1.verifyDateInterval();
        boolean state2 = vs2.verifyDateInterval();
        boolean state3 = vs3.verifyDateInterval();
        boolean state4 = vs4.verifyDateInterval();
        boolean state5 = vs5.verifyDateInterval();
        boolean state6 = vs6.verifyDateInterval();
        boolean state7 = vs7.verifyDateInterval();

        Assert.assertFalse(state1);
        Assert.assertTrue(state2);
        Assert.assertTrue(state3);
        Assert.assertTrue(state4);
        Assert.assertTrue(state5);
        Assert.assertFalse(state6);
        Assert.assertFalse(state7);






    }
}
