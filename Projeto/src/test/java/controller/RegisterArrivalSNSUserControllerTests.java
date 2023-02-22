package controller;

import app.core.controller.RegisterArrivalSNSUserController;
import app.core.dto.ScheduleVaccineDTO;
import app.core.domain.model.Company;
import domain.shared.CommonConstants;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the tests of the RegisterArrivalSNSUserControllerTests class.
 */
public class RegisterArrivalSNSUserControllerTests implements CommonConstants {
    Company company = new Company("DGS");
    RegisterArrivalSNSUserController controller = new RegisterArrivalSNSUserController(company);

    /**
     * This test will test the function of creation SNS user arrival.
     */
    @Test
    public void testCreateArrivalSNSUser() {
        initializeData();

        boolean test1 = controller.createArrivalSNSUser();

        Assert.assertTrue(test1);
    }

    /**
     * This test will test the function of saving SNS user arrival.
     */
    @Test
    public void testSaveArrivalSNSUser() {
        initializeData();

        controller.createArrivalSNSUser();

        boolean test1 = controller.saveArrivalSNSUser();
        boolean test2 = controller.saveArrivalSNSUser();

        Assert.assertTrue(test1);
        Assert.assertFalse(test2);
    }

    /**
     * This test will test the function of creation SNS user through the SNS user number.
     */
    @Test
    public void testCreateSNSUser() {
        company.getSNSUserStore().saveSNSUser(SNS_USER1);

        boolean test1 = controller.createSNSUser("123456789");
        boolean test2 = controller.createSNSUser("987654321");
        boolean test3 = controller.createSNSUser("187178348");

        Assert.assertFalse(test1);
        Assert.assertFalse(test2);
        Assert.assertTrue(test3);
    }

    /**
     * This test will test the function of get SNS user vaccination schedule list.
     */
    @Test
    public void testGetListSNSUserVaccinationSchedule() {
        initializeData();
        controller.createArrivalSNSUser();
        controller.saveArrivalSNSUser();

        company.getScheduleVaccineStore().saveScheduleVaccine(SCHEDULE_VACCINE1);

        List<ScheduleVaccineDTO> resultVaccinationScheduleList = controller.getListSNSUserVaccinationSchedule();

        List<ScheduleVaccineDTO> expectedVaccinationScheduleList = new ArrayList<>();
        expectedVaccinationScheduleList.add(new ScheduleVaccineDTO(SCHEDULE_VACCINE1.getDate(), SCHEDULE_VACCINE1.getTime()));

        int n = 0;
        while (n < expectedVaccinationScheduleList.size()) {
            boolean result = expectedVaccinationScheduleList.get(n).equals(resultVaccinationScheduleList.get(n));
            Assert.assertFalse(result);
            n++;
        }
    }

    /**
     * Method to initialize some important data for tests.
     */
    public void initializeData() {
        company.getVaccinationCenterStore().saveVaccinationCenter(VACCINATION_CENTER);
        controller.setVaccinationCenter(VACCINATION_CENTER.getId());

        company.getSNSUserStore().saveSNSUser(SNS_USER1);
        controller.createSNSUser("187178348");
    }
}
