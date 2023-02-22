package app.core.controller;

import app.core.domain.model.*;
import app.core.dto.ScheduleVaccineDTO;
import app.core.mapper.ScheduleVaccineMapper;
import app.core.domain.store.SNSUserStore;
import app.core.domain.store.ScheduleVaccineStore;
import app.core.domain.store.VaccinationCenterStore;
import app.core.domain.store.VaccineTypeStore;

import java.util.Collections;
import java.util.List;

/**
 * Represents the schedule vaccination controller.
 *
 * @author Group 40
 */
public class ScheduleVaccinationController {
    /**
     * The company.
     */
    private Company company;
    /**
     * Represents the schedule vaccine.
     */
    private ScheduleVaccine sv;
    /**
     * Represents the schedule vaccine is store.
     */
    private ScheduleVaccineStore svStore;
    /**
     * Represents the sns user.
     */
    private SNSUser su;
    /**
     * Represents the sns user store.
     */
    private SNSUserStore suStore;
    /**
     * Represents the vaccination center store.
     */
    private VaccinationCenterStore vcStore;
    /**
     * Represents the vaccine type store.
     */
    private VaccineTypeStore vtStore;

    /**
     * Builds an instance of ScheduleVaccinationController.
     */
    public ScheduleVaccinationController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of ScheduleVaccinationController receiving the company as parameter.
     *
     * @param company represents the company.
     */
    public ScheduleVaccinationController(Company company) {
        this.company = company;
        // this.scheduleVaccine = null;
    }

    /**
     * Method used to create a new vaccination schedule.
     *
     * @param svDto The vaccine schedule in a DTO form.
     * @return True if the vaccine schedule is successfully created, or false otherwise.
     */
    public boolean createScheduleVaccination(ScheduleVaccineDTO svDto) {
        svStore = company.getScheduleVaccineStore();
        sv = ScheduleVaccineMapper.toModel(svDto);
        return svStore.validateScheduleVaccine(sv);
    }

    /**
     * This function get a sns user by its sns user number.
     *
     * @param snsUserNumber holds the SNS user number.
     * @return The created sns user.
     */
    public SNSUser createSNSUser(String snsUserNumber) {
        suStore = company.getSNSUserStore();
        this.su = suStore.getSNSUser(snsUserNumber);
        return this.su;
    }

    /**
     * This function calls the getVaccinationCenterList from the vaccinationCenterStore.
     *
     * @return A list containing all the vaccination centers registered in the system.
     */
    public List<VaccinationCenter> getVaccinationCenterList() {
        vcStore = company.getVaccinationCenterStore();
        return vcStore.getVaccinationCenterList();
    }

    /**
     * This function verifies the slot availability in the requested time, date and vaccination center.
     *
     * @param vc           holds a vaccination center were the sns user is trying to schedule the vaccine.
     * @param intendedDate holds the intended schedule vaccine date.
     * @param intendedTime holds the intended schedule vaccine time.
     * @return true if the slot is available or false otherwise.
     */
    public boolean verifySlotAvailability(VaccinationCenter vc, Date intendedDate, Time intendedTime) {
        boolean availableSlot = false;

        //Verify if the center is open at the intended time
        if (validateTime(vc, intendedTime)) {

            this.svStore = company.getScheduleVaccineStore();

            //List of schedules for the current day and the vaccination center
            List<ScheduleVaccine> scheduleVaccineListDate = this.svStore.getVaccinationScheduleListDate(intendedDate, vc);

            //If the schedule list is empty the slot is free
            if (scheduleVaccineListDate.isEmpty()) {
                availableSlot = true;
            } else {

                //Sort array
                Collections.sort(scheduleVaccineListDate);

                int slotDuration = vc.getSlotDuration();
                Time openingTime = vc.getOpeningHours();
                Time closingTime = vc.getClosingHours();

                Time startTimeSlot = openingTime;
                Time finishTimeSlot = startTimeSlot.addMinutes(slotDuration);

                //While the time to evaluate is inside the center time
                while (startTimeSlot.isBigger(openingTime) && closingTime.isBigger(startTimeSlot) && !availableSlot) {

                    if (intendedTime.isBigger(startTimeSlot) && finishTimeSlot.isBigger(intendedTime)) {
                        availableSlot = !existsScheduleVaccination(scheduleVaccineListDate, startTimeSlot, finishTimeSlot);
                    }

                    //Adds to the time to evaluate the duration of the slot
                    Time temporary = finishTimeSlot;
                    startTimeSlot = finishTimeSlot;
                    finishTimeSlot = temporary.addMinutes(slotDuration);
                }
            }
        }

        return availableSlot;
    }

    /**
     * This function goes through a scheduleVaccineListDate and verifies the time availability.
     *
     * @param scheduleVaccineListDate is a list of the date of the schedule vaccine.
     * @param startTimeSlot           is the time of the start of the slot.
     * @param finishTimeSlot          is the ending time of the slot.
     * @return true if a scheduled vaccination exists in the slot, or false otherwise.
     */
    public boolean existsScheduleVaccination(List<ScheduleVaccine> scheduleVaccineListDate, Time startTimeSlot, Time finishTimeSlot) {
        boolean existsScheduleVaccination = false;

        //Goes through a list to find slot for the intended time
        for (ScheduleVaccine list : scheduleVaccineListDate) {
            if (list.getTime().isBigger(startTimeSlot) && finishTimeSlot.isBigger(list.getTime())) {
                existsScheduleVaccination = true;
            }
        }

        return existsScheduleVaccination;
    }

    /**
     * This function calls the function getVaccineTypeList.
     *
     * @return The list with all the vaccine types existentes in the system.
     */
    public List<VaccineType> getVaccineTypeList() {
        vtStore = company.getVaccineTypeStore();
        return vtStore.getVaccineTypeList();
    }

    /**
     * This function calls the saveScheduleVaccination from the ScheduleVaccineStore.
     */
    public void saveScheduleVacination() {

        svStore = company.getScheduleVaccineStore();
        svStore.loadSVFromFile();

        if (svStore.saveScheduleVaccine(sv)) {
            if (!svStore.saveSVListInFile()) {
                svStore.removeVaccineSchedule(sv);
            }
        }

    }

    /**
     * This function verifies if the intended schedule time is available for certain vaccination center.
     *
     * @param vc   holds a vaccination center were the sns user is trying to schedule the vaccine.
     * @param time holds the intended schedule vaccine time.
     */
    public boolean validateTime(VaccinationCenter vc, Time time) {
        if (vc.getClosingHours().isBigger(time) && time.isBigger(vc.getOpeningHours())) {
            return true;
        }
        return false;
    }
}
