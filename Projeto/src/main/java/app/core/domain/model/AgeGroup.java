package app.core.domain.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * It represents an age group through minimum age, maximum age, doses administered and a list of doses.
 *
 * @author Group 40
 */
public class AgeGroup implements Comparable<AgeGroup>, Serializable {
    /**
     * The minimum age of the age group.
     */
    private int minimumAge;

    /**
     * The maximum age of the age group.
     */
    private int maximumAge;

    /**
     * The list of age group doses.
     */
    private List<Dose> dList;

    /**
     * Builds an instance of AgeGroup receiving the minimum age, maximum age and doses administered.
     *
     * @param minimumAge the minimum age of the age group.
     * @param maximumAge the maximum age of the age group.
     */
    public AgeGroup(int minimumAge, int maximumAge) {
        checkAgeRules(minimumAge, maximumAge);
        this.minimumAge = minimumAge;
        this.maximumAge = maximumAge;
        this.dList = new ArrayList<>();
    }

    /**
     * Method for checking the rules of the vaccine age group.
     *
     * @param minimumAge the minimum age of the age group.
     * @param maximumAge the maximum age of the age group.
     */
    private void checkAgeRules(int minimumAge, int maximumAge) {
        if (minimumAge < 0 || maximumAge < 0) {
            throw new IllegalArgumentException("Age cannot be negative.");
        }
        if (minimumAge > maximumAge) {
            throw new IllegalArgumentException("The minimum age and the maximum age are invalid.");
        }
    }

    /**
     * Add a dose to the existing list.
     *
     * @param ap the dose
     */
    public void addDose(Dose ap) {
        this.dList.add(ap);
    }

    /**
     * Function to return the minimum age of the age group.
     *
     * @return the minimum age of the age group.
     */
    public int getMinimumAge() {
        return minimumAge;
    }

    /**
     * Function to return the maximum age of the age group.
     *
     * @return the maximum age of the age group.
     */
    public int getMaximumAge() {
        return maximumAge;
    }

    /**
     * Returns the list of dose.
     *
     * @return the list of age group dose.
     */
    public List<Dose> getDoseList() {
        return dList;
    }

    /**
     * Returns the textual description of the age group.
     *
     * @return characteristics of the age group.
     */
    @Override
    public String toString() {
        return String.format("Minimum Age: %d | Maximum Age: %d %s",
                this.minimumAge, this.maximumAge, apListToString());
    }

    /**
     * Returns the textual description of the list of age group doses.
     *
     * @return characteristics of the list of age group doses.
     */
    private String apListToString() {
        String text = "";
        int number = 0;

        for (Dose ag : dList) {
            text += String.format("\n%dº Dose \n%s", ++number, ag);
        }

        return text;
    }

    /**
     * Compares the age group with the object received.
     *
     * @param otherObject the object to be compared with the age group.
     * @return true if the object received represents another age group equivalent to the age group.
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

        AgeGroup otherAgeGroup = (AgeGroup) otherObject;
        return (this.minimumAge == otherAgeGroup.minimumAge)
                && (this.maximumAge == otherAgeGroup.maximumAge)
                && (this.dList.equals(otherAgeGroup.dList));
    }

    /**
     * Compares the age group with the other age group received by parameter.
     *
     * @param otherAgeGroup the age group to be compared.
     * @return the value 0 if the received otherAgeGroup is equal to the age group;
     * the value -1 if the otherAgeGroup is later than the age group;
     * the value 1 if the otherAgeGroup is earlier than the age group.
     */
    @Override
    public int compareTo(AgeGroup otherAgeGroup) {
        return this.minimumAge - otherAgeGroup.minimumAge;
    }
}
