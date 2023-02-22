package app.core.domain.model;

import app.core.domain.shared.exception.InvalidVaccineAdministration;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * It represents an administration vaccine through SNS user, vaccine, dose, administration date, administration time,
 * leaving date, leaving time and vaccination center.
 *
 * @author Group 40
 */
public class VaccineAdministration implements Comparable<VaccineAdministration>, Serializable {
    /**
     * The vaccine administration SNS user.
     */
    private SNSUser snsUser;

    /**
     * The vaccine administration vaccine.
     */
    private Vaccine vaccine;

    /**
     * The vaccine administration dose.
     */
    private Dose dose;

    /**
     * The vaccine administration date.
     */
    private Date administrationDate;

    /**
     * The vaccine administration time.
     */
    private Time administrationTime;

    /**
     * The vaccine administration leaving date.
     */
    private Date leavingDate;

    /**
     * The vaccine administration leaving time.
     */
    private Time leavingTime;

    /**
     * The vaccine administration vaccination center.
     */
    private VaccinationCenter vaccinationCenter;

    /**
     * The vaccine administration lot number.
     */
    private String lotNumber;

    /**
     * Builds an instance of AdministrationVaccine receiving the SNS user, vaccine, dose, administration date, administration time,
     * leaving date, leaving time and vaccination center.
     *
     * @param snsUser            the vaccine administration SNS user.
     * @param vaccine            the vaccine administration vaccine.
     * @param dose               the vaccine administration dose.
     * @param administrationDate the vaccine administration date.
     * @param administrationTime the vaccine administration time.
     * @param leavingDate        the vaccine administration leaving date.
     * @param leavingTime        the vaccine administration leaving time.
     * @param vaccinationCenter  the vaccine administration vaccination center.
     * @param lotNumber          the vaccine administration lot number.
     */
    public VaccineAdministration(SNSUser snsUser, Vaccine vaccine, Dose dose, Date administrationDate, Time administrationTime,
                                 Date leavingDate, Time leavingTime, VaccinationCenter vaccinationCenter, String lotNumber) {
        checkAllNull(snsUser, vaccine, dose, administrationDate, administrationTime, leavingDate, leavingTime, vaccinationCenter);
        checkTime(administrationTime, leavingTime);
        checkLotNumber(lotNumber);

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
     * Method to check for the existence of null objects.
     *
     * @param snsUser            the vaccine administration SNS user.
     * @param vaccine            the vaccine administration vaccine.
     * @param dose               the vaccine administration dose.
     * @param administrationDate the vaccine administration date.
     * @param administrationTime the vaccine administration time.
     * @param leavingDate        the vaccine administration leaving date.
     * @param leavingTime        the vaccine administration leaving time.
     * @param vaccinationCenter  the vaccine administration vaccination center.
     */
    private void checkAllNull(SNSUser snsUser, Vaccine vaccine, Dose dose, Date administrationDate, Time administrationTime,
                              Date leavingDate, Time leavingTime, VaccinationCenter vaccinationCenter) {
        if (snsUser == null || vaccine == null || dose == null || administrationDate == null || administrationTime == null
                || leavingDate == null || leavingTime == null || vaccinationCenter == null)
            throw new InvalidVaccineAdministration();
    }

    /**
     * Method for checking the rules of the vaccine administration time and leaving time.
     *
     * @param administrationTime the vaccine administration time.
     * @param leavingTime        the vaccine administration leaving time.
     */
    private void checkTime(Time administrationTime, Time leavingTime) {
        if (administrationTime.isBigger(leavingTime))
            throw new InvalidVaccineAdministration("The leaving time cannot be greater than the administration time");
    }

    /**
     * Method for checking the rules of the vaccine administration lot number.
     *
     * @param lotNumber the vaccine administration lot number.
     */
    private void checkLotNumber(String lotNumber) {
        if (StringUtils.isBlank(lotNumber))
            throw new InvalidVaccineAdministration("Lot number cannot be blank.");
        if (!correctFormatLotNumber(lotNumber))
            throw new InvalidVaccineAdministration("The lot number has five alphanumeric characters an Hyphen and two numerical characters (example: 21C16-05).");
    }

    /**
     * Function to return the vaccine administration SNS user.
     *
     * @return the vaccine administration SNS user.
     */
    public SNSUser getSnsUser() {
        return snsUser;
    }

    /**
     * Function to return the vaccine administration vaccine.
     *
     * @return the vaccine administration vaccine.
     */
    public Vaccine getVaccine() {
        return vaccine;
    }

    /**
     * Function to return the vaccine administration dose.
     *
     * @return the vaccine administration dose.
     */
    public Dose getDose() {
        return dose;
    }

    /**
     * Function to return the vaccine administration date.
     *
     * @return the vaccine administration date.
     */
    public Date getAdministrationDate() {
        return administrationDate;
    }

    /**
     * Function to return the vaccine administration leaving date.
     *
     * @return the vaccine administration leaving date.
     */
    public Date getLeavingDate() {
        return leavingDate;
    }

    /**
     * Function to return the vaccine administration leaving time.
     *
     * @return the vaccine administration leaving time.
     */
    public Time getLeavingTime() {
        return leavingTime;
    }

    /**
     * Function to return the vaccine administration vaccination center.
     *
     * @return the vaccine administration vaccination center.
     */
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     * Method to check if the lot number format is correct.
     *
     * @param lotNumber the vaccine administration lot number.
     * @return true if the format is correct. Otherwise, returns false.
     */
    public static boolean correctFormatLotNumber(String lotNumber) {
        String regex = "^[a-zA-Z0-9]{5}-[0-9]{2}$*";
        return lotNumber.matches(regex);
    }

    /**
     * Compares the vaccine administration with the other vaccine administration received by parameter.
     *
     * @param otherVaccineAdministration the vaccine administration to be compared.
     * @return the value 0 if the received otherVaccineAdministration is equal to the vaccine administration;
     * the value -1 if the otherVaccineAdministration is later than the vaccine administration;
     * the value 1 if the otherVaccineAdministration is earlier than the vaccine administration.
     */
    @Override
    public int compareTo(VaccineAdministration otherVaccineAdministration) {
        if (!this.administrationDate.equals(otherVaccineAdministration.administrationDate)) {
            return this.administrationDate.compareTo(otherVaccineAdministration.administrationDate);
        } else {
            return this.administrationTime.compareTo(otherVaccineAdministration.administrationTime);
        }
    }
}
