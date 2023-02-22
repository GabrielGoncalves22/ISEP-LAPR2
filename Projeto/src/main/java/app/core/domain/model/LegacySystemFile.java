package app.core.domain.model;

import app.core.dto.SNSUserDTO;
import app.core.dto.VaccineDTO;

/**
 * This class represent the information present in a file with center manage data from a legacy system
 *
 * @author Group 40
 */
public class LegacySystemFile {

    /**
     * Holds the Sns User information in a DTO form.
     */
    private SNSUserDTO snsUserDTO;

    /**
     * Holds the vaccine information in a DTO form.
     */
    private VaccineDTO vaccineDTO;

    /**
     * Holds the dose of the vaccine.
     */
    private String dose;

    /**
     * Holds the lot number.
     */
    private String lotNumber;

    /**
     * Holds the date that the vaccine was scheduled
     */
    private Date scheduledDate;

    /**
     * Holds the time that the vaccine was scheduled
     */
    private Time scheduledTime;

    /**
     * Holds the arrival date of the SNS User to the center.
     */
    private Date arrivalDate;

    /**
     * Holds the arrival time of the SNS User to the center.
     */
    private Time arrivalTime;

    /**
     * Holds the date the nurse administrated the vaccine to the SNS User.
     */
    private Date nurseAdministrationDate;

    /**
     * Holds the time the nurse administrated the vaccine to the SNS User.
     */
    private Time nurseAdministrationTime;

    /**
     * Holds the date the SNS User left the center.
     */
    private Date leavingDate;

    /**
     * Holds the time the SNS User left the center.
     */
    private Time leavingTime;

    /**
     * Constructor to build an instance of this class.
     *
     * @param snsUserDTO                  Holds the Sns User information in a DTO form.
     * @param vaccineDTO                  Holds the vaccine information in a DTO form.
     * @param dose                        Holds the dose of the vaccine.
     * @param lotNumber                   Holds the lot number.
     * @param scheduledDate               Holds the date that the vaccine was scheduled
     * @param scheduledTime               Holds the time that the vaccine was scheduled
     * @param arrivalDate                 Holds the arrival date of the SNS User to the center.
     * @param arrivalTime                 Holds the arrival time of the SNS User to the center.
     * @param nurseAdministrationDate Holds the date the nurse administrated the vaccine to the SNS User.
     * @param nurseAdministrationTime Holds the time the nurse administrated the vaccine to the SNS User.
     * @param leavingDate                 Holds the date the SNS User left the center.
     * @param leavingTime                 Holds the time the SNS User left the center.
     */
    public LegacySystemFile(SNSUserDTO snsUserDTO, VaccineDTO vaccineDTO, String dose, String lotNumber,
                            Date scheduledDate, Time scheduledTime, Date arrivalDate, Time arrivalTime,
                            Date nurseAdministrationDate, Time nurseAdministrationTime, Date leavingDate,
                            Time leavingTime) {
        this.snsUserDTO = snsUserDTO;
        this.vaccineDTO = vaccineDTO;
        this.dose = dose;
        this.lotNumber = lotNumber;
        this.scheduledDate = scheduledDate;
        this.scheduledTime = scheduledTime;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.nurseAdministrationDate = nurseAdministrationDate;
        this.nurseAdministrationTime = nurseAdministrationTime;
        this.leavingDate = leavingDate;
        this.leavingTime = leavingTime;
    }

    /**
     * Method to return the Sns User information in a DTO form.
     *
     * @return The Sns User information in a DTO form.
     */
    public SNSUserDTO getSnsUserDTO() {
        return snsUserDTO;
    }

    /**
     * Method to return the vaccine information in a DTO form.
     *
     * @return The vaccine information in a DTO form.
     */
    public VaccineDTO getVaccineDTO() {
        return vaccineDTO;
    }

    /**
     * Method to return the dose.
     *
     * @return the dose.
     */
    public String getDose() {
        return dose;
    }

    /**
     * Method to return the lot number.
     *
     * @return The lot number.
     */
    public String getLotNumber() {
        return lotNumber;
    }

    /**
     * Method to return the scheduled vaccine date.
     *
     * @return The scheduled vaccine date.
     */
    public Date getScheduledDate() {
        return scheduledDate;
    }

    /**
     * Method to return the scheduled vaccine time.
     *
     * @return The scheduled vaccine time.
     */
    public Time getScheduledTime() {
        return scheduledTime;
    }

    /**
     * Method to return the arrival date by the SNS User.
     *
     * @return The arrival date by the SNS User.
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Method to return the arrival time by the SNS User.
     *
     * @return The arrival time by the SNS User.
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Method to return the date the nurse administered the vaccine.
     *
     * @return The date the nurse administered the vaccine.
     */
    public Date getNurseAdministrationDate() {
        return nurseAdministrationDate;
    }

    /**
     * Method to return the time the nurse administered the vaccine.
     *
     * @return The time the nurse administered the vaccine.
     */
    public Time getNurseAdministrationTime() {
        return nurseAdministrationTime;
    }

    /**
     * Method to return the date the SNS User left the center.
     *
     * @return The date the SNS User left the center.
     */
    public Date getLeavingDate() {
        return leavingDate;
    }

    /**
     * Method to return time the SNS User left the center.
     *
     * @return The time the SNS User left the center.
     */
    public Time getLeavingTime() {
        return leavingTime;
    }
}
