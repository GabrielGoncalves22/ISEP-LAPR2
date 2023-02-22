package app.core.controller;

import app.core.domain.model.*;
import app.core.domain.store.*;

import java.util.List;

/**
 * Represents the vaccine center controller.
 *
 * @author Group 40
 */
public class VaccinationStatisticsController {

    /**
     * Represents the company.
     */
    private Company company;

    /**
     * Represents the vaccine admnistration store.
     */
    private VaccineAdministrationStore vaccineAdministrationStore;

    /**
     * Represents the vaccine.
     */
    private VaccinationCenterStore vaccinationCenterStore;

    /**
     * Represents the vaccine store.
     */
    private VaccineStore vaccineStore;

    /**
     * Represents the SNS user store.
     */
    private SNSUserStore snsUserStore;

    /**
     * Represents the vaccination statistics.
     */
    private VaccinationStatistics vaccinationStatistics;

    /**
     * Represents the vaccination center.
     */
    private VaccinationCenter vaccinationCenter;

    /**
     * Builds an instance of VaccinationStatisticsController.
     */
    public VaccinationStatisticsController(int vcId) {
        this(App.getInstance().getCompany());
        this.vaccinationCenterStore = company.getVaccinationCenterStore();
        vaccinationCenter = vaccinationCenterStore.getVaccinationCenter(vcId);
    }

    /**
     * Builds an instance of vaccinationStatisticsController
     *
     * @param company represents the company.
     */
    public VaccinationStatisticsController(Company company) {
        this.company = company;
        this.vaccinationStatistics = null;
    }

    /**
     * This function gets a list of the fully vaccinated people in an interval.
     *
     * @param date1 the first date in the interval.
     * @param date2 the second date in the interval.
     * @return a list of integers.
     */
    public List<Integer> totalNumberFullyVaccinatedInInterval(Date date1, Date date2) {
        this.snsUserStore = this.company.getSNSUserStore();
        List<SNSUser> suList = this.snsUserStore.getSNSUserList();

        this.vaccineStore = this.company.getVaccineStore();
        List<Vaccine> vList = this.vaccineStore.getListV();

        this.vaccineAdministrationStore = this.company.getVaccineAdministrationStore();
        return this.vaccineAdministrationStore.totalNumberFullyVaccinatedInInterval(suList, vList, date1, date2, vaccinationCenter);
    }
}
