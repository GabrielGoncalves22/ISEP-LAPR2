package app.core.dto;

import app.core.domain.model.*;

public class ScheduleVaccineDTO implements Comparable<ScheduleVaccineDTO> {
    private SNSUser snsUser;
    private VaccinationCenter vaccinationCenter;
    private Date date;
    private Time time;
    private VaccineType vaccineType;

    public ScheduleVaccineDTO(SNSUser snsUser, VaccinationCenter vaccinationCenter, Date date, Time time, VaccineType vaccineType) {
        this.snsUser = snsUser;
        this.vaccinationCenter = vaccinationCenter;
        this.date = date;
        this.time = time;
        this.vaccineType = vaccineType;
    }

    public ScheduleVaccineDTO(Date date, Time time) {
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return String.format("Date: %s \nTime: %s",
                this.date, this.time);
    }

    public SNSUser getSNSUser() {
        return snsUser;
    }

    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    public Date getDate() {
        return date;
    }

    public Time getTime() {
        return time;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    /**
     * This function compares two scheduleVaccineDTO.
     *
     * @param otherScheduleVaccine the object to be compared.
     * @return the call of a function.
     */
    @Override
    public int compareTo(ScheduleVaccineDTO otherScheduleVaccine) {
        if (!this.date.equals(otherScheduleVaccine.date)) {
            return this.date.compareTo(otherScheduleVaccine.date);
        } else {
            return this.time.compareTo(otherScheduleVaccine.time);
        }
    }
}
