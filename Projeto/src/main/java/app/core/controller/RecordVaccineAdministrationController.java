package app.core.controller;

import app.core.domain.model.*;
import app.core.domain.store.*;
import app.core.dto.*;
import app.core.mapper.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the vaccine administration controller.
 *
 * @author Group 40
 */
public class RecordVaccineAdministrationController {
    /**
     * The company.
     */
    private Company company;

    /**
     * The vaccination center store.
     */
    private VaccinationCenterStore vcStore;

    /**
     * The SNS user arrival store.
     */
    private ArrivalSNSUserStore auStore;

    /**
     * The SNS User store.
     */
    private SNSUserStore suStore;

    /**
     * The schedule vaccine store.
     */
    private ScheduleVaccineStore svStore;

    /**
     * The vaccine administration store.
     */
    private VaccineAdministrationStore vaStore;

    /**
     * The vaccine store.
     */
    private VaccineStore vStore;

    /**
     * The vaccine administration.
     */
    private VaccineAdministration va;

    /**
     * The vaccination center.
     */
    private VaccinationCenter vc;

    /**
     * The SNS user.
     */
    private SNSUser su;

    /**
     * Builds an instance of RecordAdministrationVaccineController.
     *
     * @param vcId the id of the vaccination center.
     */
    public RecordVaccineAdministrationController(int vcId) {
        this(App.getInstance().getCompany());
        this.vcStore = company.getVaccinationCenterStore();
        vc = vcStore.getVaccinationCenter(vcId);
    }

    /**
     * Builds an instance of RecordAdministrationVaccineController receiving the company.
     *
     * @param company the company.
     */
    public RecordVaccineAdministrationController(Company company) {
        this.company = company;
    }

    /**
     * Function to return a new instance of VaccineAdministration from the store.
     *
     * @param vaDto the vaccine administration DTO.
     * @return true if the vaccine administration was created successfully. Otherwise, returns false.
     */
    public boolean createVaccineAdministration(VaccineAdministrationDTO vaDto) {
        this.vaStore = this.company.getVaccineAdministrationStore();
        this.va = this.vaStore.createVaccineAdministration(vaDto);
        return this.vaStore.validateVaccineAdministration(va);
    }

    /**
     * Function to store a VaccineAdministration object from the store.
     *
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveVaccineAdministration() {
        this.vaStore.loadVaccineAdministrationListFromFile();
        this.auStore.removeWaitingRoom(su);

        if (this.vaStore.saveVaccineAdministration(va)) {
            if (this.vaStore.saveVaccineAdministrationListInFile()) {
                return true;
            } else {
                this.vaStore.removeVaccineAdministration(va);
            }
        }

        return false;
    }

    /**
     * Function to return the list arrival sns user from a certain center via the store.
     *
     * @return the list arrival sns user.
     */
    public List<ArrivalSNSUserDTO> getListArrivalSNSUser() {
        this.auStore = company.getArrivalSNSUserStore();
        List<ArrivalSNSUser> listAU = auStore.getListArrivalSNSUser(vc);
        return ArrivalSNSUserMapper.toListDTO(listAU);
    }

    /**
     * Function to search if there is a sns user through SNS user Number.
     *
     * @param snsUserNumber the SNS user number of SNS user.
     * @return the SNS user DTO.
     */
    public SNSUserDTO searchSNSUser(String snsUserNumber) {
        this.suStore = this.company.getSNSUserStore();
        this.su = suStore.getSNSUser(snsUserNumber);
        return SNSUserMapper.toDto(this.su);
    }

    /**
     * Function to return the list of suggested vaccines to administer.
     *
     * @return the list vaccine DTO.
     */
    public List<VaccineDTO> getListSuggestedVaccineAdminister() {
        VaccineType vt = checkTypeVaccineMarked();

        Vaccine v = checkSNSUserTakingAnyVaccine(vt);

        List<Vaccine> listV = new ArrayList<>();
        if (v != null) {
            listV.add(v);
        } else {
            listV = checkListSuggestedVaccines(vt);
        }

        return VaccineMapper.toListDTO(listV);
    }

    /**
     * Function to return the list of suggested doses to administer.
     *
     * @param v the vaccine.
     * @return the list dose DTO.
     */
    public List<DoseDTO> getListSuggestedDoseAdminister(Vaccine v) {
        List<Dose> listD = checkListDoses(v);
        listD = withdrawAdministeredDoses(v, listD);

        return DoseMapper.toListDTO(listD);
    }

    /**
     * Function to return the recovery period.
     *
     * @return int the recovery period.
     */
    public int getRecoveryPeriod() {
        String propertyRecoveryPeriod = App.getInstance().getPropertyRecoveryPeriod();
        return Integer.parseInt(propertyRecoveryPeriod);
    }

    /**
     * Function to send a message regarding the end of the recovery time.
     *
     * @param phoneNumber the phone number.
     * @param message     the message to send.
     * @param delay       The message delay.
     */
    public void sendMessage(String phoneNumber, String message, int delay) {
        this.vaStore.sendMessage(phoneNumber, message, delay);
    }

    /**
     * Function to return the vaccination center.
     *
     * @return the vaccination center.
     */
    public VaccinationCenter getVaccinationCenter() {
        return this.vc;
    }

    /**
     * Function to return the type of vaccine scheduled by the SNS user.
     *
     * @return the vaccine type.
     */
    private VaccineType checkTypeVaccineMarked() {
        Date date = Date.currentDate();
        this.svStore = this.company.getScheduleVaccineStore();
        return svStore.getTypeVaccineMarked(su, vc, date);
    }

    /**
     * Function to return the vaccine that an SNS user is taking from a type of vaccine.
     *
     * @param vt the vaccine type
     * @return the current vaccine
     */
    private Vaccine checkSNSUserTakingAnyVaccine(VaccineType vt) {
        this.vaStore = this.company.getVaccineAdministrationStore();
        return vaStore.currentVaccine(su, vt);
    }

    /**
     * Function to return a list with the vaccines of a type of vaccine.
     *
     * @param vt the vaccine type.
     * @return The vaccine list.
     */
    private List<Vaccine> checkListSuggestedVaccines(VaccineType vt) {
        int age = this.suStore.currentAgeSNSUser(su);
        this.vStore = this.company.getVaccineStore();
        return vStore.getListSuggestedVaccines(vt, age);
    }

    /**
     * Function to return a list with the doses of a vaccine.
     *
     * @param v the vaccine.
     * @return the dose list.
     */
    private List<Dose> checkListDoses(Vaccine v) {
        int age = this.suStore.currentAgeSNSUser(su);
        this.vStore = this.company.getVaccineStore();
        return vStore.getListDose(v, age);
    }

    /**
     * Function to return the list of doses that are missing in the administration of a vaccine to an SNS user.
     *
     * @param v     the vaccine.
     * @param listD the dose list.
     * @return the dose list.
     */
    private List<Dose> withdrawAdministeredDoses(Vaccine v, List<Dose> listD) {
        this.vaStore = this.company.getVaccineAdministrationStore();
        return vaStore.missingDoses(su, v, listD);
    }
}
