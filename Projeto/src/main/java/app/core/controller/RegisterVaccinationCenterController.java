package app.core.controller;

import app.core.domain.model.Company;
import app.core.domain.model.Time;
import app.core.domain.model.VaccinationCenter;
import app.core.domain.store.VaccinationCenterStore;
import pt.isep.lei.esoft.auth.domain.model.Email;

/**
 * Represents the vaccine center controller.
 *
 * @author Group 40
 */
public class RegisterVaccinationCenterController {

    /**
     * The company.
     */
    private Company company;

    /**
     * The vaccine center.
     */
    private VaccinationCenter vc;

    /**
     * The vaccine center store.
     */
    private VaccinationCenterStore store;

    /**
     * Builds an instance of RegisterVaccinationCenterController.
     */
    public RegisterVaccinationCenterController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of RegisterVaccinationCenterController receiving the company.
     *
     * @param company the company.
     */
    public RegisterVaccinationCenterController(Company company) {
        this.company = company;
        this.vc = null;
        this.store = this.company.getVaccinationCenterStore();
    }

    /**
     * Function to return a new instance of VaccinationCenter from the store.
     *
     * @param name             the name of the vaccine center.
     * @param address          the address of the vaccine center.
     * @param phoneNumber      the phone number of the vaccine center.
     * @param email            the email of the vaccine center.
     * @param faxNumber        the fax number of the vaccine center.
     * @param website          the website of the vaccine center.
     * @param openingHours     the opening hours of the vaccine center.
     * @param closingHours     the closing hours of the vaccine center.
     * @param slotDuration     the slot duration of the vaccine center.
     * @param slotVaccineLimit the slot vaccine limit of the vaccine center.
     * @return the boolean
     */
    public boolean createVaccinationCenter(String name, String address, int phoneNumber, Email email,
                                           int faxNumber, String website, Time openingHours, Time closingHours,
                                           int slotDuration, int slotVaccineLimit) {
        this.vc = this.store.createVaccinationCenter(name, address, phoneNumber, email, faxNumber, website, openingHours, closingHours,
                slotDuration, slotVaccineLimit);
        return this.store.validateVaccinationCenter(vc);
    }

    /**
     * Create schedule time.
     *
     * @param hours   the hours
     * @param minutes the minutes
     * @return the time
     */
    public Time createSchedule(int hours, int minutes) {
        return this.store.createTime(hours, minutes);
    }

    /**
     * Function to store a VaccinationCenter object from the store.
     *
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveVaccinationCenter() {
        return this.store.saveVaccinationCenter(vc);
    }

}
