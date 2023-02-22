package app.core.domain.model;

import java.io.Serializable;

/**
 * It represents an SNS user arrival through SNS user, arrival time and vaccination center.
 *
 * @author Group 40
 */
public class ArrivalSNSUser implements Comparable<ArrivalSNSUser>, Serializable {
    /**
     * The SNS User of the SNS user arrival.
     */
    private SNSUser snsUser;

    /**
     * The arrival date of the SNS user arrival.
     */
    private Date arrivalDate;

    /**
     * The arrival time of the SNS user arrival.
     */
    private Time arrivalTime;

    /**
     * The vaccination center of the SNS user arrival.
     */
    private VaccinationCenter vaccinationCenter;

    /**
     * The waiting room of the SNS user arrival.
     */
    private boolean waitingRoom;

    /**
     * Builds an instance of ArrivalSNSUser receiving the SNS user, arrival time and vaccination center.
     *
     * @param snsUser           the SNS user of the SNS user arrival.
     * @param arrivalDate       the arrival date of the SNS user arrival.
     * @param arrivalTime       the arrival time of the SNS user arrival.
     * @param vaccinationCenter the vaccination center of the SNS user arrival.
     */
    public ArrivalSNSUser(SNSUser snsUser, Date arrivalDate, Time arrivalTime, VaccinationCenter vaccinationCenter) {
        checkSNSUser(snsUser);
        checkArrivalDate(arrivalDate);
        checkArrivalTime(arrivalTime);
        checkVaccinationCenter(vaccinationCenter);

        this.snsUser = snsUser;
        this.arrivalDate = arrivalDate;
        this.arrivalTime = arrivalTime;
        this.vaccinationCenter = vaccinationCenter;
        this.waitingRoom = true;
    }

    /**
     * Method for checking the rules of the SNS user.
     *
     * @param snsUser the SNS user of the SNS user arrival.
     */
    private void checkSNSUser(SNSUser snsUser) {
        if (snsUser == null) {
            throw new IllegalArgumentException("SNS user cannot be null.");
        }
    }

    /**
     * Method for checking the rules of the arrival date.
     *
     * @param arrivalDate the arrival date of the SNS user arrival.
     */
    private void checkArrivalDate(Date arrivalDate) {
        if (arrivalDate == null) {
            throw new IllegalArgumentException("Arrival date cannot be null.");
        }
    }

    /**
     * Method for checking the rules of the arrival time.
     *
     * @param arrivalTime the arrival time of the SNS user arrival.
     */
    private void checkArrivalTime(Time arrivalTime) {
        if (arrivalTime == null) {
            throw new IllegalArgumentException("Arrival time be null.");
        }
    }

    /**
     * Method for checking the rules of the vaccination center.
     *
     * @param vaccinationCenter the vaccination center of the SNS user arrival.
     */
    private void checkVaccinationCenter(VaccinationCenter vaccinationCenter) {
        if (vaccinationCenter == null) {
            throw new IllegalArgumentException("Vaccination center cannot be null.");
        }
    }

    /**
     * Method to assign the waiting room of the SNS user arrival.
     *
     * @param waitingRoom the waiting room of the SNS user arrival.
     */
    public void setWaitingRoom(boolean waitingRoom) {
        this.waitingRoom = waitingRoom;
    }

    /**
     * Function to return the sns user.
     *
     * @return the sns user.
     */
    public SNSUser getSnsUser() {
        return snsUser;
    }

    /**
     * Function to return the vaccination center.
     *
     * @return the vaccination center.
     */
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     * Function to return the arrival date.
     *
     * @return the arrival date.
     */
    public Date getArrivalDate() {
        return arrivalDate;
    }

    /**
     * Function to return the arrival time.
     *
     * @return the arrival time.
     */
    public Time getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Function to return the waiting room.
     *
     * @return the waiting room.
     */
    public boolean getWaitingRoom() {
        return waitingRoom;
    }

    /**
     * Returns the textual description of the SNS user arrival.
     *
     * @return characteristics of the SNS user arrival.
     */
    @Override
    public String toString() {
        return String.format("SNS User Number: %s \nArrival Date: %s \nArrival Time: %s",
                this.snsUser.getSnsUserNumber(), this.arrivalDate, this.arrivalTime);
    }

    /**
     * Compares the SNS user arrival with the object received.
     *
     * @param otherObject the object to be compared with the SNS user arrival.
     * @return true if the object received represents another SNS user arrival equivalent to the SNS user arrival.
     * Otherwise, returns false.
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }

        ArrivalSNSUser otherArrivalSNSUser = (ArrivalSNSUser) otherObject;
        return (this.snsUser.equals(otherArrivalSNSUser.snsUser)
                && (this.arrivalDate.equals(otherArrivalSNSUser.arrivalDate))
                && (this.vaccinationCenter.equals(otherArrivalSNSUser.vaccinationCenter)));
    }

    /**
     * Compares the SNS user arrival with the other SNS user arrival received by parameter.
     *
     * @param otherArrivalSNSUser the SNS user arrival to be compared.
     * @return the value 0 if the received otherArrivalSNSUser is equal to the SNS user arrival;
     * the value -1 if the otherArrivalSNSUser is later than the SNS user arrival;
     * the value 1 if the otherArrivalSNSUser is earlier than the SNS user arrival.
     */
    @Override
    public int compareTo(ArrivalSNSUser otherArrivalSNSUser) {
        if (!this.arrivalDate.equals(otherArrivalSNSUser.arrivalDate)) {
            return this.arrivalDate.compareTo(otherArrivalSNSUser.arrivalDate);
        } else {
            return this.arrivalTime.compareTo(otherArrivalSNSUser.arrivalTime);
        }
    }
}
