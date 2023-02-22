package app.core.controller;

import app.core.domain.model.*;
import app.core.domain.model.Dose;
import app.core.domain.store.VaccineStore;

/**
 * Represents the vaccine controller.
 *
 * @author Group 40
 */
public class SpecifyNewVaccineController {
    /**
     * The company.
     */
    private Company company;

    /**
     * The vaccine.
     */
    private Vaccine v;

    /**
     * The age group.
     */
    private AgeGroup ag;

    /**
     * The dose.
     */
    private Dose d;

    /**
     * The vaccine store.
     */
    private VaccineStore store;

    /**
     * Builds an instance of SpecifyNewVaccineController.
     */
    public SpecifyNewVaccineController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of SpecifyNewVaccineController receiving the company.
     *
     * @param company the company.
     */
    public SpecifyNewVaccineController(Company company) {
        this.company = company;
        this.v = null;
        this.store = this.company.getVaccineStore();
    }

    /**
     * Function to create a new instance of Vaccine from the store.
     *
     * @param name  the name of the vaccine.
     * @param brand the brand of the vaccine.
     * @param vt    the vaccine type of the vaccine.
     * @return true if the vaccine was created successfully. Otherwise, returns false.
     */
    public boolean createVaccine(String name, String brand, VaccineType vt) {
        this.v = this.store.createVaccine(name, brand, vt);
        return this.store.validateVaccine(v);
    }

    /**
     * Function to create a new instance of AgeGroup from the store.
     *
     * @param minimumAge the minimum age of the age group.
     * @param maximumAge the maximum age of the age group.
     * @return true if the age group was created successfully. Otherwise, returns false.
     */
    public boolean createAgeGroup(int minimumAge, int maximumAge) {
        this.ag = this.store.createAgeGroup(minimumAge, maximumAge);

        boolean validateAgeGroup = this.store.validateAgeGroup(v.getAgList(), ag);
        if (validateAgeGroup) {
            this.v = this.store.addAgeGroup(v, ag);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Function to create a new instance of Dose from the store.
     *
     * @param doseNumber                  the dose number of the dose.
     * @param dosage                      the dosage of the dose.
     * @param timeIntervalBetweenLastDose the time interval between last dose of the dose.
     * @return true if the dose was created successfully. Otherwise, returns false.
     */
    public boolean createDose(int doseNumber, int dosage, int timeIntervalBetweenLastDose) {
        this.d = this.store.createDose(doseNumber, dosage, timeIntervalBetweenLastDose);

        boolean validateDose = this.store.validateDose(ag.getDoseList(), d);
        if (validateDose) {
            this.ag = this.store.addDose(ag, d);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Function to store a Vaccine object from the store.
     *
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveVaccine() {
        return this.store.saveVaccine(v);
    }

    /**
     * Returns the list of the vaccines.
     *
     * @return the vaccine list.
     */
    public Vaccine getVaccine() {
        return v;
    }
}
