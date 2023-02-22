package app.core.domain.model;

import java.io.Serializable;

/**
 * Represents a schedule vaccine through snsUser, vaccinationCenter, date, time, vaccineType.
 *
 * @author Group 40
 */
public class ScheduleVaccine implements Comparable<ScheduleVaccine>, Serializable {
    /**
     * This variable holds a sns user whose trying to schedule a vaccine.
     */
    private SNSUser snsUser;

    /**
     * This variable holds a vaccination center were the sns user is trying to schedule the vaccine.
     */
    private VaccinationCenter vaccinationCenter;

    /**
     * This variable holds the intended schedule vaccine date.
     */
    private Date date;

    /**
     * This variable holds the intended schedule vaccine time.
     */
    private Time time;

    /**
     * This variable holds the type of vaccine.
     */
    private VaccineType vaccineType;

    /**
     * Instantiates a new schedule vaccine.
     *
     * @param snsUser           holds a sns user whose trying to schedule a vaccine.
     * @param vaccinationCenter holds a vaccination center were the sns user is trying to schedule the vaccine.
     * @param date              holds the intended schedule vaccine time.
     * @param time              holds the type of vaccine.
     * @param vaccineType       holds the type of vaccine.
     */
    public ScheduleVaccine(SNSUser snsUser, VaccinationCenter vaccinationCenter, Date date, Time time, VaccineType vaccineType) {
        checkFields(snsUser, vaccinationCenter, date, time, vaccineType);

        this.snsUser = snsUser;
        this.vaccinationCenter = vaccinationCenter;
        this.date = date;
        this.time = time;
        this.vaccineType = vaccineType;
    }

    /**
     * Method to check fields
     *
     * @param snsUser           holds a sns user whose trying to schedule a vaccine.
     * @param vaccinationCenter holds a vaccination center were the sns user is trying to schedule the vaccine.
     * @param date              holds the intended schedule vaccine time.
     * @param time              holds the type of vaccine.
     * @param vaccineType       holds the type of vaccine.
     */
    public void checkFields(SNSUser snsUser, VaccinationCenter vaccinationCenter, Date date, Time time, VaccineType vaccineType) {
        if (snsUser == null || vaccinationCenter == null || date == null || time == null || vaccineType == null) {
            throw new IllegalArgumentException("The SNS User name cannot be blank!");
        }
    }

    /**
     * This function gets the sns user who wants to schedule the vaccine.
     *
     * @return sns user.
     */
    public SNSUser getSnsUser() {
        return snsUser;
    }

    /**
     * This function gets the vaccination center where the user is trying to schedule the vaccine.
     *
     * @return vaccination center.
     */
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     * This function gets the date when the user is trying to schedule the vaccine.
     *
     * @return date.
     */
    public Date getDate() {
        return date;
    }

    /**
     * This function gets the time when the user is trying to schedule the vaccine.
     *
     * @return time.
     */
    public Time getTime() {
        return time;
    }

    /**
     * This function gets the vaccine type when the user is trying to schedule the vaccine.
     *
     * @return vaccine type.
     */
    public VaccineType getVaccineType() {
        return vaccineType;
    }

    /**
     * This function compares two scheduleVaccine.
     *
     * @param otherScheduleVaccine the object to be compared.
     * @return the call of a function.
     */
    @Override
    public int compareTo(ScheduleVaccine otherScheduleVaccine) {
        if (!this.date.equals(otherScheduleVaccine.date)) {
            return this.date.compareTo(otherScheduleVaccine.date);
        } else {
            return this.time.compareTo(otherScheduleVaccine.time);
        }
    }
}
