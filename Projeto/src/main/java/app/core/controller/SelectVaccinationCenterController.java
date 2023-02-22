package app.core.controller;

import app.core.domain.model.Employee;
import app.core.domain.store.EmployeeStore;
import app.core.dto.VaccinationCenterDTO;
import app.core.mapper.VaccinationCenterMapper;
import app.core.domain.model.Company;
import app.core.domain.model.VaccinationCenter;
import app.core.domain.store.VaccinationCenterStore;

import java.util.List;

/**
 * Represents the select vaccination center controller.
 *
 * @author Group 40
 */
public class SelectVaccinationCenterController {
    /**
     * The company.
     */
    private Company company;

    /**
     * The vaccination center store.
     */
    private VaccinationCenterStore vcStore;

    /**
     * The vaccination center store.
     */
    private EmployeeStore eStore;

    /**
     * Builds an instance of SelectVaccinationCenterController.
     */
    public SelectVaccinationCenterController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of SelectVaccinationCenterController receiving the company.
     *
     * @param company the company.
     */
    public SelectVaccinationCenterController(Company company) {
        this.company = company;
    }

    /**
     * Returns the list of the vaccination centers.
     *
     * @return the vaccination center list.
     */
    public List<VaccinationCenterDTO> getVaccinationCenterList() {
        this.vcStore = this.company.getVaccinationCenterStore();
        List<VaccinationCenter> listVC = vcStore.getVaccinationCenterList();
        List<VaccinationCenterDTO> listVCDto = VaccinationCenterMapper.toListDTO(listVC);
        return listVCDto;
    }

    /**
     * Function to check if an id of a vaccination center exists.
     *
     * @param vcId the id of the vaccine center.
     * @return true if it exists. Otherwise, false.
     */
    public boolean existsVaccinationCenter(int vcId) {
        return vcStore.existsVaccinationCenter(vcId);
    }

    /**
     * Function to return the id vaccination center from a vaccination center coordinator.
     *
     * @param centerCoordinatorId the id of the vaccination center coordinator.
     * @return the id of the vaccination center.
     */
    public int getVaccinationCenterIdCenterCoordinator(int centerCoordinatorId) {
        this.vcStore = this.company.getVaccinationCenterStore();
        return this.vcStore.getVaccinationCenterIdCenterCoordinator(centerCoordinatorId);
    }

    /**
     * Method to return the id of the vaccination center coordinator.
     *
     * @param email the vaccination center coordinator's email.
     * @return the id of the vaccination center coordinator.
     */
    public int getCenterCoordinatorId(String email) {
        this.eStore = this.company.getEmployeeStore();

        Employee employee = this.eStore.findEmployeeByEmail(email);
        if (employee != null) {
            return employee.getId();
        } else {
            return -1;
        }
    }

    /**
     * Function to return the id of the vaccination center.
     *
     * @param vcDto the vaccination center DTO.
     * @return the id of the vaccine center.
     */
    public int getVaccinationCenterId(VaccinationCenterDTO vcDto) {
        return vcDto.getId();
    }
}
