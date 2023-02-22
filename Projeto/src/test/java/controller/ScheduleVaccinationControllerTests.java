package controller;

import app.core.controller.ScheduleVaccinationController;
import app.core.domain.model.Company;
import app.core.domain.model.ScheduleVaccine;
import app.core.domain.store.ScheduleVaccineStore;
import domain.shared.CommonConstants;
import org.junit.Test;
import org.junit.Assert;

import java.util.List;

/**
 * Class to test the methods of the ScheduleVaccinationController class
 *
 * @author Group 40
 */
public class ScheduleVaccinationControllerTests implements CommonConstants {
    private Company company = new Company("DGS");
    private ScheduleVaccinationController controller = new ScheduleVaccinationController(company);
    private ScheduleVaccineStore svStore = company.getScheduleVaccineStore();

    private List<ScheduleVaccine> scheduleVaccineListDate = this.svStore.getVaccinationScheduleListDate(DATE_1, VACCINATION_CENTER_1);

    @Test
    public void testVerifySlotAvailability() {
    }

    @Test
    public void testExistsScheduleVaccination() {
        boolean value = controller.existsScheduleVaccination(scheduleVaccineListDate, TIME_1, TIME_3);

        Assert.assertFalse(value);
    }

    @Test
    public void testValidateTime() {
        boolean valication1 = controller.validateTime(VACCINATION_CENTER_1, TIME_1);
        boolean validation2 = controller.validateTime(VACCINATION_CENTER_2, TIME_3);

        Assert.assertTrue(valication1);
        Assert.assertFalse(validation2);
    }

}
