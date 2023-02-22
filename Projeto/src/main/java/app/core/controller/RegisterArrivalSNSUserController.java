package app.core.controller;

import app.core.domain.model.*;
import app.core.dto.ArrivalSNSUserDTO;
import app.core.dto.ScheduleVaccineDTO;
import app.core.mapper.ScheduleVaccineMapper;
import app.core.domain.store.ArrivalSNSUserStore;
import app.core.domain.store.SNSUserStore;
import app.core.domain.store.ScheduleVaccineStore;
import app.core.domain.store.VaccinationCenterStore;

import java.util.List;

/**
 * Represents the SNS user arrival controller.
 *
 * @author Group 40
 */
public class RegisterArrivalSNSUserController {
    /**
     * The company.
     */
    private Company company;

    /**
     * The SNS user arrival store.
     */
    private ArrivalSNSUserStore auStore;

    /**
     * The vaccine center store.
     */
    private VaccinationCenterStore vcStore;

    /**
     * The SNS User store.
     */
    private SNSUserStore suStore;

    /**
     * The schedule vaccine store.
     */
    private ScheduleVaccineStore svStore;

    /**
     * The SNS user arrival.
     */
    private ArrivalSNSUser au;

    /**
     * The SNS user.
     */
    private SNSUser su;

    /**
     * The vaccination center.
     */
    private VaccinationCenter vc;

    /**
     * Builds an instance of RegisterArrivalSNSUserController.
     */
    public RegisterArrivalSNSUserController(int vcId) {
        this(App.getInstance().getCompany());
        this.vcStore = company.getVaccinationCenterStore();
        vc = vcStore.getVaccinationCenter(vcId);
    }

    /**
     * Builds an instance of RegisterArrivalSNSUserController receiving the company.
     *
     * @param company the company.
     */
    public RegisterArrivalSNSUserController(Company company) {
        this.company = company;
    }

    /**
     * Function to return a new instance of ArrivalSNSUser from the store.
     *
     * @return true if the arrival SNS user was created successfully. Otherwise, returns false.
     */
    public boolean createArrivalSNSUser() {
        this.auStore = this.company.getArrivalSNSUserStore();
        ArrivalSNSUserDTO auDTO = new ArrivalSNSUserDTO(su, Date.currentDate(), Time.currentTime(), vc);
        this.au = this.auStore.createArrivalSNSUser(auDTO);
        return !this.auStore.checkDuplicates(au);
    }

    /**
     * Function to store a ArrivalSNSUser object from the store.
     *
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveArrivalSNSUser() {
        
        this.auStore.loadSNSUsersArrivalsListFromFile();

        if (this.auStore.saveArrivalSNSUser(au)) {
            if (this.auStore.saveArrivalSNSUserListInFile()) {
                return true;
            } else {
                this.auStore.removeArrivalSNSUser(au);
            }
        }

        return false;

    }

    /**
     * Returns the SNS user arrival.
     *
     * @return the SNS user arrival.
     */
    public ArrivalSNSUser getArrivalSNSUser() {
        return au;
    }

    /**
     * Function to create an SNS user through the SNS user number received by parameter.
     *
     * @param snsUserNumber the SNS user number of SNS user.
     * @return true if SNS user exists. Otherwise, returns false.
     */
    public boolean createSNSUser(String snsUserNumber) {
        this.suStore = this.company.getSNSUserStore();
        this.su = suStore.getSNSUser(snsUserNumber);
        return su != null;
    }

    /**
     * Function to return the list of vaccination schedules of a SNS user in a vaccination center from the store.
     *
     * @return the vaccination schedule list.
     */
    public List<ScheduleVaccineDTO> getListSNSUserVaccinationSchedule() {
        this.svStore = this.company.getScheduleVaccineStore();
        List<ScheduleVaccine> listSV = svStore.getVaccinationScheduleListSNSUser(su, vc);
        return ScheduleVaccineMapper.toListDTO(listSV);
    }

    /**
     * Function to define a vaccination center.
     *
     * @param vcId the id of the vaccine center.
     */
    public void setVaccinationCenter(int vcId) {
        this.vcStore = company.getVaccinationCenterStore();
        vc = vcStore.getVaccinationCenter(vcId);
    }
}
