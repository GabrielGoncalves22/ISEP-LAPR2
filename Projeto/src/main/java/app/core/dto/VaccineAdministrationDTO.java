package app.core.dto;

import app.core.domain.model.*;

/**
 * The type Vaccine administration DTO.
 */
public class VaccineAdministrationDTO {
    /**
     * The vaccine administration DTO SNS user.
     */
    private SNSUserDTO snsUser;

    /**
     * The vaccine administration DTO vaccine.
     */
    private VaccineDTO vaccine;

    /**
     * The vaccine administration DTO dose.
     */
    private DoseDTO dose;

    /**
     * The vaccine administration DTO date.
     */
    private Date administrationDate;

    /**
     * The vaccine administration DTO time.
     */
    private Time administrationTime;

    /**
     * The vaccine administration DTO leaving date.
     */
    private Date leavingDate;

    /**
     * The vaccine administration DTO leaving time.
     */
    private Time leavingTime;

    /**
     * The vaccine administration DTO vaccination center.
     */
    private VaccinationCenter vaccinationCenter;

    /**
     * The vaccine administration DTO lot number.
     */
    private String lotNumber;

    /**
     * Builds an instance of VaccineAdministrationDTO no parameters
     */
    public VaccineAdministrationDTO() {
    }

    /**
     * Builds an instance of VaccineAdministrationDTO receiving the SNS user, vaccine, dose, administration date, administration time,
     * leaving date, leaving time and vaccination center.
     *
     * @param snsUser            the vaccine administration DTO SNS user.
     * @param vaccine            the vaccine administration DTO vaccine.
     * @param dose               the vaccine administration DTO dose.
     * @param administrationDate the vaccine administration DTO date.
     * @param administrationTime the vaccine administration DTO time.
     * @param leavingDate        the vaccine administration DTO leaving date.
     * @param leavingTime        the vaccine administration DTO leaving time.
     * @param vaccinationCenter  the vaccine administration DTO vaccination center.
     * @param lotNumber          the vaccine administration DTO lot number.
     */
    public VaccineAdministrationDTO(SNSUserDTO snsUser, VaccineDTO vaccine, DoseDTO dose, Date administrationDate, Time administrationTime,
                                    Date leavingDate, Time leavingTime, VaccinationCenter vaccinationCenter, String lotNumber) {
        this.snsUser = snsUser;
        this.vaccine = vaccine;
        this.dose = dose;
        this.administrationDate = administrationDate;
        this.administrationTime = administrationTime;
        this.leavingDate = leavingDate;
        this.leavingTime = leavingTime;
        this.vaccinationCenter = vaccinationCenter;
        this.lotNumber = lotNumber;
    }

    /**
     * Method to assign the vaccine administration DTO SNS user.
     *
     * @param snsUser the vaccine administration DTO SNS user.
     */
    public void setSnsUserDTO(SNSUserDTO snsUser) {
        this.snsUser = snsUser;
    }

    /**
     * Method to assign the vaccine administration DTO vaccine.
     *
     * @param vaccine the vaccine administration DTO vaccine.
     */
    public void setVaccineDTO(VaccineDTO vaccine) {
        this.vaccine = vaccine;
    }

    /**
     * Method to assign the vaccine administration DTO dose.
     *
     * @param dose the vaccine administration DTO dose.
     */
    public void setDoseDTO(DoseDTO dose) {
        this.dose = dose;
    }

    /**
     * Method to assign the vaccine administration DTO date.
     *
     * @param administrationDate the vaccine administration DTO date.
     */
    public void setAdministrationDate(Date administrationDate) {
        this.administrationDate = administrationDate;
    }

    /**
     * Method to assign the vaccine administration DTO time.
     *
     * @param administrationTime the vaccine administration DTO time.
     */
    public void setAdministrationTime(Time administrationTime) {
        this.administrationTime = administrationTime;
    }

    /**
     * Method to assign the vaccine administration DTO leaving date.
     *
     * @param leavingDate the vaccine administration DTO leaving date.
     */
    public void setLeavingDate(Date leavingDate) {
        this.leavingDate = leavingDate;
    }

    /**
     * Method to assign the vaccine administration DTO leaving time.
     *
     * @param leavingTime the vaccine administration DTO leaving time.
     */
    public void setLeavingTime(Time leavingTime) {
        this.leavingTime = leavingTime;
    }

    /**
     * Method to assign the vaccine administration DTO vaccination center.
     *
     * @param vaccinationCenter the vaccine administration DTO vaccination center.
     */
    public void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
    }

    /**
     * Method to assign the vaccine administration DTO lot number.
     *
     * @param lotNumber the vaccine administration DTO lot number.
     */
    public void setLotNumber(String lotNumber) {
        this.lotNumber = lotNumber;
    }

    /**
     * Function to return the vaccine administration DTO SNS user.
     *
     * @return the vaccine administration SNS user.
     */
    public SNSUserDTO getSnsUserDTO() {
        return snsUser;
    }

    /**
     * Function to return the vaccine administration DTO vaccine.
     *
     * @return the vaccine administration vaccine.
     */
    public VaccineDTO getVaccineDTO() {
        return vaccine;
    }

    /**
     * Function to return the vaccine administration DTO dose.
     *
     * @return the vaccine administration dose.
     */
    public DoseDTO getDoseDTO() {
        return dose;
    }

    /**
     * Function to return the vaccine administration DTO date.
     *
     * @return the vaccine administration DTO date.
     */
    public Date getAdministrationDate() {
        return administrationDate;
    }

    /**
     * Function to return the vaccine administration DTO time.
     *
     * @return the vaccine administration DTO time.
     */
    public Time getAdministrationTime() {
        return administrationTime;
    }

    /**
     * Function to return the vaccine administration DTO leaving date.
     *
     * @return the vaccine administration DTO leaving date.
     */
    public Date getLeavingDate() {
        return leavingDate;
    }

    /**
     * Function to return the vaccine administration DTO leaving time.
     *
     * @return the vaccine administration DTO leaving time.
     */
    public Time getLeavingTime() {
        return leavingTime;
    }

    /**
     * Function to return the vaccine administration DTO vaccination center.
     *
     * @return the vaccine administration vaccination center.
     */
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     * Function to return the vaccine administration DTO lot number.
     *
     * @return the vaccine administration DTO lot number.
     */
    public String getLotNumber() {
        return lotNumber;
    }
}
