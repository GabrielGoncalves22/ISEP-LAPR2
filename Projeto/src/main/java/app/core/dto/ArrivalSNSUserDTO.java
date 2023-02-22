package app.core.dto;

import app.core.domain.model.Date;
import app.core.domain.model.SNSUser;
import app.core.domain.model.Time;
import app.core.domain.model.VaccinationCenter;

/**
 * Represents the SNS user arrival DTO.
 *
 * @author Group 40
 */
public class ArrivalSNSUserDTO {
    /**
     * The SNS User of the SNS user arrival DTO.
     */
    private SNSUser snsUser;

    /**
     * The arrival date of the SNS user arrival DTO.
     */
    private Date arrivalDate;

    /**
     * The arrival time of the SNS user arrival DTO.
     */
    private Time arrivalTime;

    /**
     * The vaccination center of the SNS user arrival DTO.
     */
    private VaccinationCenter vaccinationCenter;

    /**
     * The waiting room of the SNS user arrival DTO.
     */
    private boolean waitingRoom;

    /**
     * Builds an instance of ArrivalSNSUserDTO receiving the SNS user, arrival time and vaccination center.
     *
     * @param snsUser           the SNS user of the SNS user arrival DTO.
     * @param arrivalDate       the arrival date of the SNS user arrival DTO.
     * @param arrivalTime       the arrival time of the SNS user arrival DTO.
     * @param vaccinationCenter the vaccination center of the SNS user arrival DTO.
     */
    public ArrivalSNSUserDTO(SNSUser snsUser, Date arrivalDate, Time arrivalTime, VaccinationCenter vaccinationCenter) {
        this.snsUser = snsUser;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.vaccinationCenter = vaccinationCenter;
        this.waitingRoom = true;
    }

    /**
     * Builds an instance of ArrivalSNSUserDTO receiving the SNS user.
     *
     * @param snsUser           the SNS user of the SNS user arrival DTO.
     */
    public ArrivalSNSUserDTO(SNSUser snsUser) {
        this.snsUser = snsUser;
    }

    /**
     * Function to return the SNS User of the SNS user arrival DTO.
     *
     * @return the SNS User of the SNS user arrival DTO.
     */
    public SNSUser getSnsUser() {
        return snsUser;
    }

    /**
     * Function to return the arrival date of the SNS user arrival DTO.
     *
     * @return the arrival date of the SNS user arrival DTO.
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Function to return the arrival time of the SNS user arrival DTO.
     *
     * @return the arrival time of the SNS user arrival DTO.
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Function to return the vaccination center of the SNS user arrival DTO.
     *
     * @return the vaccination center of the SNS user arrival DTO.
     */
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     * Returns the textual description of the SNS User.
     *
     * @return characteristics of the SNS User.
     */
    @Override
    public String toString() {
        String text = String.format("Name: %s%n", snsUser.getName());
        text += String.format("Sex: %s%n", snsUser.getSex());
        text += String.format("Phone Number: %s%n", snsUser.getPhoneNumber());
        text += String.format("Birth date: %s%n", snsUser.getBirthdate());
        text += String.format("Sns Number: %s%n", snsUser.getSnsUserNumber());
        return text;
    }
}
