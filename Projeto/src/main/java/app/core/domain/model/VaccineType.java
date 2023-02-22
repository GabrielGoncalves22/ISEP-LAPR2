package app.core.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * It represents a type of vaccine through code, description and technology.
 *
 * @author Group 40
 */
public class VaccineType implements Comparable<VaccineType>, Serializable {
    /**
     * The code of the vaccine type.
     */
    private String code;

    /**
     * The description of the vaccine type.
     */
    private String description;

    /**
     * The technology of the vaccine type.
     */
    private String technology;

    /**
     * Builds an instance of VaccineType receiving the code, description and technology.
     *
     * @param code        the code of the vaccine type.
     * @param description the description of the vaccine type.
     * @param technology  the technology of the vaccine type.
     */
    public VaccineType(String code, String description, String technology) {
        checkCodeRules(code);
        checkDescriptionRules(description);
        this.code = code;
        this.description = description;
        this.technology = technology;
    }

    /**
     * Method for checking the rules of the vaccine type code.
     *
     * @param code the description of the vaccine type.
     */
    private void checkCodeRules(String code) {
        if (StringUtils.isBlank(code))
            throw new IllegalArgumentException("Code cannot be blank.");
        if (code.length() != 5)
            throw new IllegalArgumentException("Code must have 5 alphanumeric characters.");
    }

    /**
     * Method for checking the rules of the vaccine type description.
     *
     * @param description the technology of the vaccine type.
     */
    private void checkDescriptionRules(String description) {
        if (StringUtils.isBlank(description))
            throw new IllegalArgumentException("Description cannot be blank.");
        if (description.length() > 40)
            throw new IllegalArgumentException("Description maximum 40 chars.");
    }

    /**
     * Function to return the code of the vaccine type.
     *
     * @return the code of the vaccine type.
     */
    public String getCode() {
        return code;
    }

    /**
     * Function to return the description of the vaccine type.
     *
     * @return the code of the description of the vaccine type.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Function to return the technology of the vaccine type.
     *
     * @return the code of the technology of the vaccine type.
     */
    public String getTechnology() {
        return technology;
    }

    /**
     * Returns the textual description of the vaccine type.
     *
     * @return characteristics of the vaccine type.
     */
    @Override
    public String toString() {
        return String.format("Code: %s | Description: %s | Technology: %s",
                this.code, this.description, this.technology);
    }

    /**
     * Compares the type of vaccine with the object received.
     *
     * @param otherObject the object to be compared with the type of vaccine.
     * @return true if the object received represents another vaccine type equivalent to the vaccine type.
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

        VaccineType otherVaccineType = (VaccineType) otherObject;
        return (this.code.equalsIgnoreCase(otherVaccineType.code))
                && (this.description.equalsIgnoreCase(otherVaccineType.description))
                && (this.technology.equalsIgnoreCase(otherVaccineType.technology));
    }

    /**
     * Compares the type of vaccine with the other type of vaccine received by parameter.
     *
     * @param otherVaccineType the type of vaccine to be compared.
     * @return the value 0 if the received otherVaccineType is equal to the vaccine type.
     * the value -1 if the otherVaccineType is later than the vaccine type.
     * the value 1 if the otherVaccineType is earlier than the vaccine type.
     */
    @Override
    public int compareTo(VaccineType otherVaccineType) {
        return this.code.compareTo(otherVaccineType.code);
    }
}
