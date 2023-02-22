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
 * Represents the schedule vaccine controller.
 */
public class ScheduleVaccineController {
    private Company company;
    private ScheduleVaccine sv;
    private ScheduleVaccineStore svStore;
    private SNSUser su;
    private SNSUserStore suStore;
    private VaccinationCenterStore vcStore;
    private VaccineTypeStore vtStore;

    /**
     * Builds an instance of ScheduleVaccineController.
     */
    public ScheduleVaccineController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of ScheduleVaccineController receiving the company as parameter.
     *
     * @param company represents the company.
     */
    public ScheduleVaccineController(Company company) {
        this.company = company;
    }

    /**
     * Function to create a new instance of ScheduleVaccine from the store.
     *
     * @return true if the schedule vaccine was created successfully. Otherwise, returns false.
     */
    public boolean createScheduleVaccine(ScheduleVaccineDTO svDto) {
        svStore = company.getScheduleVaccineStore();
        sv = ScheduleVaccineMapper.toModel(svDto);
        return svStore.validateScheduleVaccine(sv);
    }

    /**
     * This function get a sns user by its sns user number.
     *
     * @param snsUserNumber holds the SNS user number.
     * @return sns user.
     */
    public SNSUser createSNSUser(String snsUserNumber) {
        suStore = company.getSNSUserStore();
        this.su = suStore.getSNSUser(snsUserNumber);
        return this.su;
    }

    /**
     * This function calls the getVaccinationCenterList from the vaccinationCenterStore.
     *
     * @return the call of a function.
     */
    public List<VaccinationCenter> getVaccinationCenterList() {
        vcStore = company.getVaccinationCenterStore();
        return vcStore.getVaccinationCenterList();
    }

    /**
     * This function verifys the slot availability in the requested time, date and vaccination center.
     *
     * @param vc           holds a vacination center were the sns user is trying to shedule the vaccine.
     * @param intendedDate holds the intended schedule vaccine date.
     * @param intendedTime holds the intended schedule vaccine time.
     * @return boolean value.
     */
    public boolean verifySlotAvailability(VaccinationCenter vc, Date intendedDate, Time intendedTime) {
        boolean availableSlot = false;

        //Verificar se o centro está aberto à hora pretendida
        if (validateTime(vc, intendedTime)) {

            this.svStore = company.getScheduleVaccineStore();

            //Lista de marcações para o dia atual e o centro de vacinação pretendido
            List<ScheduleVaccine> scheduleVaccineListDate = this.svStore.getVaccinationScheduleListDate(intendedDate, vc);

            //Se a lista de agendamentos estiver vazia, a slot está livre
            if (scheduleVaccineListDate.isEmpty()) {
                availableSlot = true;
            } else {

                //Ordenar array
                Collections.sort(scheduleVaccineListDate);

                int slotDuration = vc.getSlotDuration();
                Time openingTime = vc.getOpeningHours();
                Time closingTime = vc.getClosingHours();

                Time startTimeSlot = openingTime;
                Time finishTimeSlot = startTimeSlot.addMinutes(slotDuration);

                //Enquanto o tempo a avaliar estiver dentro das horas do centro
                while (startTimeSlot.isBigger(openingTime) && closingTime.isBigger(startTimeSlot) && !availableSlot) {

                    if (intendedTime.isBigger(startTimeSlot) && finishTimeSlot.isBigger(intendedTime)) {
                        availableSlot = !existsScheduleVaccination(scheduleVaccineListDate, startTimeSlot, finishTimeSlot);
                    }

                    //Adicionar ao tempo a avaliar a duração da slot
                    Time temporary = finishTimeSlot;
                    startTimeSlot = finishTimeSlot;
                    finishTimeSlot = temporary.addMinutes(slotDuration);
                }
            }
        }

        return availableSlot;
    }

    /**
     * This function goes through a scheduleVaccineListDate and verifys the time availability.
     *
     * @param scheduleVaccineListDate is a list of the date of the schedule vaccine.
     * @param startTimeSlot           is the time of the start of the slot.
     * @param finishTimeSlot          is the ending time of the slot.
     * @return boolean value.
     */
    public boolean existsScheduleVaccination(List<ScheduleVaccine> scheduleVaccineListDate, Time startTimeSlot, Time finishTimeSlot) {
        boolean existsScheduleVaccination = false;

        //Percorrer a lista para encontrar vaga no tempo pretendido
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
     * @return the call of a function.
     */
    public List<VaccineType> getVaccineTypeList() {
        vtStore = company.getVaccineTypeStore();
        return vtStore.getVaccineTypeList();
    }

    /**
     * This function calls the saveScheduleVacination from the ScheduleVaccineStore.
     *
     * @return the call of a function and passes an scheduleVaccine as parameter.
     */
    public boolean saveScheduleVacine() {
        svStore = company.getScheduleVaccineStore();
        svStore.loadSVFromFile();

        if (svStore.saveScheduleVaccine(sv)) {
            if (!svStore.saveSVListInFile()) {
                svStore.removeVaccineSchedule(sv);
                return false;
            }
        }

        return true;
    }

    /**
     * This function verifys if the intended schedule time is available for certain vaccination center.
     *
     * @param vc   holds a vacination center were the sns user is trying to shedule the vaccine.
     * @param time holds the intended schedule vaccine time.
     * @return boolean value.
     */
    public boolean validateTime(VaccinationCenter vc, Time time) {
        if (vc.getClosingHours().isBigger(time) && time.isBigger(vc.getOpeningHours())) {
            return true;
        }
        return false;
    }
}
