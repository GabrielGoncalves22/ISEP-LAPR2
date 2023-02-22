package app.core.domain.model;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * It represents a vaccine through id, name, brand, vaccine type and a list of age groups.
 *
 * @author Group 40
 */
public class Vaccine implements Comparable<Vaccine>, Serializable {
    /**
     * The id of the vaccine.
     */
    private int id;

    /**
     * The name of the vaccine.
     */
    private String name;

    /**
     * The brand of the vaccine.
     */
    private String brand;

    /**
     * The vaccine type of the vaccine.
     */
    private VaccineType vt;

    /**
     * The list of vaccine age group.
     */
    private List<AgeGroup> agList;

    /**
     * The number of existing vaccines.
     */
    private static int numberVaccines;

    /**
     * Builds an instance of Vaccine receiving the name, brand and vaccine type.
     *
     * @param name  the name of the vaccine.
     * @param brand the brand of the vaccine.
     * @param vt    the vaccine type of the vaccine.
     */
    public Vaccine(String name, String brand, VaccineType vt) {
        checkNameRules(name);
        checkBrandRules(name);
        checkVaccineTypeRules(vt);
        this.id = ++numberVaccines;
        this.name = name;
        this.brand = brand;
        this.vt = vt;
        this.agList = new ArrayList<>();
    }

    /**
     * Builds an instance of Vaccine receiving the id, name, brand and vaccine type.
     *
     * @param id     the id of the vaccine.
     * @param name   the name of the vaccine.
     * @param brand  the brand of the vaccine.
     * @param vt     the vaccine type of the vaccine.
     * @param agList the list of vaccine age group.
     */
    public Vaccine(int id, String name, String brand, VaccineType vt, List<AgeGroup> agList) {
        checkNameRules(name);
        checkBrandRules(name);
        checkVaccineTypeRules(vt);

        this.id = id;
        this.name = name;
        this.brand = brand;
        this.vt = vt;
        this.agList = agList;
    }

    /**
     * Method for checking the rules of the vaccine name.
     *
     * @param name the name of the vaccine.
     */
    private void checkNameRules(String name) {
        if (StringUtils.isBlank(name))
            throw new IllegalArgumentException("Name cannot be blank.");
    }

    /**
     * Method for checking the rules of the vaccine brand.
     *
     * @param brand the brand of the vaccine.
     */
    private void checkBrandRules(String brand) {
        if (StringUtils.isBlank(brand))
            throw new IllegalArgumentException("Brand cannot be blank.");
    }

    /**
     * Method to check the rules of vaccine type of vaccine.
     *
     * @param vt the vaccine type of the vaccine.
     */
    private void checkVaccineTypeRules(VaccineType vt) {
        if (vt == null) {
            throw new IllegalArgumentException("Vaccine Type cannot be blank.");
        }
    }

    /**
     * Add an age group to the existing list.
     *
     * @param ag the age group.
     */
    public void addAgeGroup(AgeGroup ag) {
        this.agList.add(ag);
    }

    /**
     * Function to return the id of the vaccine.
     *
     * @return the id of the vaccine.
     */
    public int getId() {
        return id;
    }

    /**
     * Function to return the name of the vaccine.
     *
     * @return the name of the vaccine.
     */
    public String getName() {
        return name;
    }

    /**
     * Function to return the brand of the vaccine.
     *
     * @return the brand of the vaccine.
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Returns the list of age group.
     *
     * @return the list of vaccine age group.
     */
    public List<AgeGroup> getAgList() {
        return agList;
    }

    /**
     * Function to return the vaccine type of the vaccine.
     *
     * @return the vaccine type of the vaccine.
     */
    public VaccineType getVt() {
        return vt;
    }

    /**
     * Returns the textual description of the vaccine.
     *
     * @return characteristics of the vaccine.
     */
    @Override
    public String toString() {
        return String.format("Id: %d | Name: %s | Brand: %s \n\nVaccine type \n%s %s",
                this.id, this.name, this.brand, this.vt, agListToString());
    }

    /**
     * Returns the textual description of the list of vaccine age group.
     *
     * @return characteristics of the list of vaccine age group.
     */
    private String agListToString() {
        String text = "";
        int number = 0;

        for (AgeGroup ag : agList) {
            text += String.format("\n\n%dº Age group \n%s", ++number, ag);
        }

        return text;
    }

    /**
     * Compares the vaccine with the object received.
     *
     * @param otherObject the object to be compared with the vaccine.
     * @return true if the object received represents another vaccine equivalent to the vaccine.
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

        Vaccine otherVaccine = (Vaccine) otherObject;
        return (this.id == otherVaccine.id)
                && (this.name.equals(otherVaccine.name))
                && (this.brand.equals(otherVaccine.brand))
                && (this.vt.equals(otherVaccine.vt));
    }

    /**
     * Compares the vaccine with the other vaccine received by parameter.
     *
     * @param otherVaccine the vaccine to be compared.
     * @return the value 0 if the received otherVaccine is equal to the vaccine;
     * the value -1 if the otherVaccine is later than the vaccine;
     * the value 1 if the otherVaccine is earlier than the vaccine.
     */
    @Override
    public int compareTo(Vaccine otherVaccine) {
        return this.id - otherVaccine.id;
    }
}
