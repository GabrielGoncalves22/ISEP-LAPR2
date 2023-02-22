package app.core.controller;

import app.core.domain.model.Company;
import app.core.domain.model.VaccineType;
import app.core.domain.store.VaccineTypeStore;

import java.util.List;

/**
 * Represents the vaccine type controller.
 *
 * @author Group 40
 */
public class SpecifyNewVaccineTypeController {
    /**
     * The company.
     */
    private Company company;

    /**
     * The vaccine type.
     */
    private VaccineType vt;

    /**
     * The vaccine type store.
     */
    private VaccineTypeStore store;

    /**
     * Builds an instance of SpecifyNewVaccineTypeController.
     */
    public SpecifyNewVaccineTypeController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of SpecifyNewVaccineTypeController receiving the company.
     *
     * @param company the company.
     */
    public SpecifyNewVaccineTypeController(Company company) {
        this.company = company;
        this.vt = null;
        this.store = this.company.getVaccineTypeStore();
    }

    /**
     * Function to return a new instance of VaccineType from the store.
     *
     * @param code        the code of the vaccine type.
     * @param description the description of the vaccine type.
     * @param technology  the technology of the vaccine type.
     * @return the vaccine type.
     */
    public boolean createVaccineType(String code, String description, String technology) {
        this.vt = this.store.createVaccineType(code, description, technology);
        return this.store.validateVaccineType(vt);
    }

    /**
     * Function to store a VaccineType object from the store.
     *
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveVaccineType() {
        return this.store.saveVaccineType(vt);
    }

    /**
     * Returns the type of vaccines.
     *
     * @return the vaccine type.
     */
    public VaccineType getVaccineType() {
        return vt;
    }

    /**
     * Returns the list of the type of vaccines from the store.
     *
     * @return the vaccine type list.
     */
    public List<VaccineType> getVaccineTypeList() {
        return this.store.getVaccineTypeList();
    }
}
