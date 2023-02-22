package app.core.domain.model;

import java.io.Serializable;
import java.util.Locale;

/**
 * It represents a dose through dose number, dosage and a time interval between last dose.
 *
 * @author Group 40
 */
public class Dose implements Comparable<Dose>, Serializable {
    /**
     * The dose number of the dose.
     */
    private int doseNumber;

    /**
     * The dosage of the dose.
     */
    private int dosage;

    /**
     * The time interval between last dose of the dose.
     */
    private int timeIntervalBetweenLastDose;

    /**
     * String representation of the first dose.
     */
    public static final String FIRST_DOSE_STRING = "primeira";

    /**
     * String representation of the second dose.
     */
    public static final String SECOND_DOSE_STRING = "segunda";

    /**
     * String representation of the third dose.
     */
    public static final String THIRD_DOSE_STRING = "terceira";

    /**
     * String representation of the fourth dose.
     */
    public static final String FOURTH_DOSE_STRING = "quarta";

    /**
     * String representation of the fifth dose.
     */
    public static final String FIFTH_DOSE_STRING = "quinta";

    /**
     * Builds an instance of Dose receiving the dose number, dosage and a time interval between last dose.
     *
     * @param doseNumber                  the dose number of the dose.
     * @param dosage                      the dosage of the dose.
     * @param timeIntervalBetweenLastDose the time interval between last dose of the dose.
     */
    public Dose(int doseNumber, int dosage, int timeIntervalBetweenLastDose) {
        checkDoseNumber(doseNumber);
        checkDosage(dosage);
        checkIntervalBetweenLastDose(timeIntervalBetweenLastDose);
        this.doseNumber = doseNumber;
        this.dosage = dosage;
        this.timeIntervalBetweenLastDose = timeIntervalBetweenLastDose;
    }

    /**
     * Method to map a dose number in a String format
     *
     * @param dose The dose in a String format.
     * @return The dose in int format.
     */
    public static int mapDoseValue(String dose) {
        int doseNumber = -1;
        switch (dose.toLowerCase()) {
            case FIRST_DOSE_STRING:
                doseNumber = 1;
                break;
            case SECOND_DOSE_STRING:
                doseNumber = 2;
                break;
            case THIRD_DOSE_STRING:
                doseNumber = 3;
                break;
            case FOURTH_DOSE_STRING:
                doseNumber = 4;
                break;
            case FIFTH_DOSE_STRING:
                doseNumber = 5;
                break;
        }

        return doseNumber;

    }

    /**
     * Method for checking the rules of the dose number.
     *
     * @param doseNumber the dose number of the dose.
     */
    private void checkDoseNumber(int doseNumber) {
        if (doseNumber <= 0)
            throw new IllegalArgumentException("Dose number cannot be negative, nor be zero");
    }

    /**
     * Method for checking the rules of the dose dosage.
     *
     * @param dosage the dosage of the dose.
     */
    private void checkDosage(int dosage) {
        if (dosage <= 0)
            throw new IllegalArgumentException("Dosage cannot be negative, nor be zero");
    }

    /**
     * Method for checking the rules of the dose time interval between last dose.
     *
     * @param timeIntervalBetweenLastDose the time interval between last dose of the dose.
     */
    private void checkIntervalBetweenLastDose(int timeIntervalBetweenLastDose) {
        if (timeIntervalBetweenLastDose < 0)
            throw new IllegalArgumentException("Interval between last dose cannot be negative.");
    }

    /**
     * Function to return the dose number of the dose.
     *
     * @return the dose number of the dose.
     */
    public int getDoseNumber() {
        return doseNumber;
    }

    /**
     * Function to return the dosage of the dose.
     *
     * @return the dosage of the dose.
     */
    public int getDosage() {
        return dosage;
    }

    /**
     * Function to return the time interval between last dose of the dose.
     *
     * @return the time interval between last dose of the dose.
     */
    public int getTimeIntervalBetweenLastDose() {
        return timeIntervalBetweenLastDose;
    }

    /**
     * Returns the textual description of the dose.
     *
     * @return characteristics of the dose.
     */
    @Override
    public String toString() {
        return String.format("Dose Number: %d | Dosage: %d | Time Interval Between Last Dose: %d",
                this.doseNumber, this.dosage, this.timeIntervalBetweenLastDose);
    }

    /**
     * Compares the dose with the object received.
     *
     * @param otherObject the object to be compared with the dose.
     * @return true if the object received represents another dose equivalent to the dose.
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

        Dose otherDose = (Dose) otherObject;
        return (this.doseNumber == otherDose.doseNumber)
                && (this.dosage == otherDose.dosage)
                && (this.timeIntervalBetweenLastDose == otherDose.timeIntervalBetweenLastDose);
    }

    /**
     * Compares the dose with the other dose received by parameter.
     *
     * @param otherDose the dose to be compared.
     * @return the value 0 if the received otherDose is equal to the dose;
     * the value -1 if the otherDose is later than the dose;
     * the value 1 if the otherDose is earlier than the dose.
     */
    @Override
    public int compareTo(Dose otherDose) {
        return this.doseNumber - otherDose.doseNumber;
    }
}
