package app.core.domain.store;

import app.core.domain.model.*;
import app.core.domain.shared.SerializeClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents were the schedule vaccine is stored.
 *
 * @author Group 40
 */
public class ScheduleVaccineStore {

    /**
     * This variable holds schedule vaccine list.
     */
    private List<ScheduleVaccine> listSV;

    /**
     * This variable holds a temporary schedule vaccine list.
     */
    private List<ScheduleVaccine> tempListSV = new ArrayList<>();

    /**
     * The name of the file who has all the information to keep data persistence for scheduled vaccines.
     */
    private final String FILE_WITH_ALL_DATA = "ScheduledVaccines.ser";

    /**
     * Constructor to build a ScheduleVaccineStore instance object.
     */
    public ScheduleVaccineStore() {
        this.listSV = this.loadSVFromFile();
    }

    /**
     * This function gets the vaccination schedule list.
     *
     * @return schedule vaccine list.
     */
    public List<ScheduleVaccine> getVaccinationScheduleList() {
        this.listSV = loadSVFromFile();
        return listSV;
    }

    /**
     * Function to return the list of vaccination schedules of an SNS user in a vaccination center.
     *
     * @param su the SNS user of the SNS user arrival.
     * @param vc the vaccination center of the SNS user arrival.
     * @return the vaccination schedule list.
     */
    public List<ScheduleVaccine> getVaccinationScheduleListSNSUser(SNSUser su, VaccinationCenter vc) {

        List<ScheduleVaccine> filteredListSV = new ArrayList<>();

        this.listSV = this.loadSVFromFile();

        for (ScheduleVaccine scheduleVaccine : this.listSV) {
            if (scheduleVaccine.getSnsUser().equals(su)
                    && scheduleVaccine.getVaccinationCenter().equals(vc)
                    && scheduleVaccine.getDate().equals(Date.currentDate())) {

                filteredListSV.add(scheduleVaccine);
            }
        }

        return filteredListSV;
    }

    /**
     * This function adds the scheduleVaccine to the list if it is validated.
     *
     * @param scheduleVaccine represents the schedule vaccine.
     * @return true if the vaccine schedule is successfully saved into the system, or false otherwise.
     */
    public boolean saveScheduleVaccine(ScheduleVaccine scheduleVaccine) {
        if (!validateScheduleVaccine(scheduleVaccine)) {
            return false;
        } else {
            return addScheduleVaccine(scheduleVaccine);
        }
    }

    /**
     * Method to create a new instance of a vaccine schedule.
     *
     * @param sU           The SNS User.
     * @param vc The vaccination center.
     * @param date              The date.
     * @param time              The time.
     * @param vt       The vaccine type.
     * @return The created object of ScheduleVaccine.
     */
    public ScheduleVaccine createScheduleVaccine(SNSUser sU, VaccinationCenter vc, Date date, Time time,
                                                 VaccineType vt) {
        return new ScheduleVaccine(sU, vc, date, time, vt);
    }

    /**
     * Removes a certain scheduled vaccine from the list.
     *
     * @param scheduleVaccine The scheduled vaccine.
     */
    public void removeVaccineSchedule(ScheduleVaccine scheduleVaccine) {
        this.listSV.remove(scheduleVaccine);
    }

    /**
     * Method to save all vaccine schedules registered in the system into the file to keep data persistence.
     *
     * @return true if the operation is successfully completed or false otherwise.
     */
    public boolean saveSVListInFile() {
        return SerializeClasses.saveObjectIntoDisk(this.listSV, this.FILE_WITH_ALL_DATA);
    }

    /**
     * Method to load into memory all the scheduled vaccines registered in the system.
     *
     * @return The list with all the scheduled vaccines.
     */
    public List<ScheduleVaccine> loadSVFromFile() {
        return SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
    }

    /**
     * This function validates the schedule vaccine if it's not null.
     *
     * @param scheduleVaccine represents the schedule vaccine.
     * @return true if the schedule vaccine is valid, or false otherwise.
     */
    public boolean validateScheduleVaccine(ScheduleVaccine scheduleVaccine) {
        if (scheduleVaccine == null) {
            return false;
        } else {
            return !this.listSV.contains(scheduleVaccine);
        }
    }

    /**
     * This function will add an scheduleVaccine to the listSV.
     *
     * @param scheduleVaccine represents the schedule vaccine.
     * @return true if the schedule vaccine is successfully added into the list.
     */
    public boolean addScheduleVaccine(ScheduleVaccine scheduleVaccine) {
        return this.listSV.add(scheduleVaccine);
    }

    /**
     * Creates a new instance o time.
     *
     * @param hours   the hours of the time.
     * @param minutes the minutes of the time.
     * @return a new instance of time.
     */
    public Time createTime(int hours, int minutes) {
        return new Time(hours, minutes);
    }

    /**
     * This function gets the date of vaccination schedule list.
     *
     * @param intendedDate the intended date of the schedule date.
     * @param vc           the vaccination center were the sns user is trying to schedule the vaccine.
     * @return the list of the date of the vaccination schedule.
     */
    public List<ScheduleVaccine> getVaccinationScheduleListDate(Date intendedDate, VaccinationCenter vc) {

        this.listSV = this.loadSVFromFile();

        List<ScheduleVaccine> listSV = new ArrayList<>();

        for (ScheduleVaccine list : this.listSV) {
            if (list.getVaccinationCenter().equals(vc)
                    && list.getDate().equals(intendedDate)) {

                listSV.add(list);
            }
        }

        return listSV;
    }

    /**
     * Function to return the type of vaccine scheduled by the SNS user.
     *
     * @param su the SNS user.
     * @param d  The intended date.
     * @param vc the vaccination center where the sns user scheduled the vaccine.
     * @return the type of vaccine scheduled by the SNS user.
     */
    public VaccineType getTypeVaccineMarked(SNSUser su, VaccinationCenter vc, Date d) {
        VaccineType vt = null;

        for (ScheduleVaccine list : this.listSV) {
            if (list.getSnsUser().equals(su)
                    && list.getVaccinationCenter().equals(vc)
                    && list.getDate().equals(d)) {

                vt = list.getVaccineType();
            }
        }

        return vt;
    }

    /**
     * Method to add items to the temporary list.
     *
     * @param lSF     The LegacySystemFile;
     * @param sU The SNS User.
     * @param vc The vaccination center.
     * @param vT   The vaccine type.
     * @return true if successfully adds the data, or false otherwise.
     */
    public boolean createLegacySystemTemporaryData(LegacySystemFile lSF, SNSUser sU, VaccinationCenter vc,
                                                   VaccineType vT) {
        ScheduleVaccine sv = this.createScheduleVaccine(sU, vc, lSF.getScheduledDate(),
                lSF.getScheduledTime(), vT);

        if (!this.validateScheduleVaccine(sv)) {
            return false;
        }

        return this.tempListSV.add(sv);
    }

    /**
     * Method to save and persist the data recorded in the temporary list.
     */
    public void saveLegacySystemTemporaryData() {

        if (this.tempListSV.size() > 0) {
            for (ScheduleVaccine sv : this.tempListSV) {
                this.saveScheduleVaccine(sv);
            }

            this.saveSVListInFile();

            this.cleanTempList();
        }

    }

    /**
     * Cleans the temporary list.
     */
    public void cleanTempList() {
        this.tempListSV = new ArrayList<>();
    }

}
