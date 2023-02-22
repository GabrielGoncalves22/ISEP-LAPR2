package app.core.dto;

/**
 * Represents the dose DTO.
 *
 * @author Group 40
 */
public class DoseDTO {
    /**
     * The dose number of the dose DTO.
     */
    private int doseNumber;

    /**
     * The dosage of the dose DTO.
     */
    private int dosage;

    /**
     * The time interval between last dose of the dose DTO.
     */
    private int timeIntervalBetweenLastDose;

    /**
     * Builds an instance of DoseDTO receiving the dose number, dosage and a time interval between last dose.
     *
     * @param doseNumber                  the dose number of the dose DTO.
     * @param dosage                      the dosage of the dose DTO.
     * @param timeIntervalBetweenLastDose the time interval between last dose of the dose DTO.
     */
    public DoseDTO(int doseNumber, int dosage, int timeIntervalBetweenLastDose) {
        this.doseNumber = doseNumber;
        this.dosage = dosage;
        this.timeIntervalBetweenLastDose = timeIntervalBetweenLastDose;
    }

    /**
     * Function to return the dose number of the dose DTO.
     *
     * @return the dose number of the dose DTO.
     */
    public int getDoseNumber() {
        return doseNumber;
    }

    /**
     * Function to return the dosage of the dose DTO.
     *
     * @return the dosage of the dose DTO.
     */
    public int getDosage() {
        return dosage;
    }

    /**
     * Function to return the time interval between last dose of the dose DTO.
     *
     * @return the time interval between last dose of the dose DTO.
     */
    public int getTimeIntervalBetweenLastDose() {
        return timeIntervalBetweenLastDose;
    }

    /**
     * Returns the textual description of the dose DTO.
     *
     * @return characteristics of the dose DTO.
     */
    @Override
    public String toString() {
        return Integer.toString(doseNumber);
    }
}
