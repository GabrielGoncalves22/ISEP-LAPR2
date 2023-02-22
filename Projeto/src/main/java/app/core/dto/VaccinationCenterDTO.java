package app.core.dto;

import app.core.domain.model.Time;
import pt.isep.lei.esoft.auth.domain.model.Email;

/**
 * Represents the vaccination center DTO.
 *
 * @author Group 40
 */
public class VaccinationCenterDTO {
    /**
     * The id of the vaccination center DTO.
     */
    private int id;

    /**
     * The name of the vaccination center DTO.
     */
    private String name;

    /**
     * The address of the vaccination center DTO.
     */
    private String address;

    /**
     * The phoneNumber of the vaccination center DTO.
     */
    private int phoneNumber;

    /**
     * The email of the vaccination center DTO.
     */
    private Email email;

    /**
     * The faxNumber of the vaccination center DTO.
     */
    private int faxNumber;

    /**
     * The website of the vaccination center DTO.
     */
    private String website;

    /**
     * The openingHours of the vaccination center DTO.
     */
    private Time openingHours;

    /**
     * The closingHours of the vaccination center DTO.
     */
    private Time closingHours;

    /**
     * The slotDuration of the vaccination center DTO.
     */
    private int slotDuration;

    /**
     * The slotVaccineLimit of the vaccination center DTO.
     */
    private int slotVaccineLimit;

    /**
     * Instantiates a new vaccination center DTO.
     *
     * @param id          the id of the vaccination center DTO.
     * @param name        the name of the vaccination center DTO.
     * @param phoneNumber the phone number of the vaccination center DTO.
     * @param email       the email of the vaccination center DTO.
     */
    public VaccinationCenterDTO(int id, String name, int phoneNumber, Email email) {
        this.id = id;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    /**
     * Instantiates a new vaccination center.
     *
     * @param id               the id of the vaccination center DTO.
     * @param name             the name of the vaccination center DTO.
     * @param address          the address of the vaccination center DTO.
     * @param phoneNumber      the phone number of the vaccination center DTO.
     * @param email            the email of the vaccination center DTO.
     * @param faxNumber        the fax number of the vaccination center DTO.
     * @param website          the website of the vaccination center DTO.
     * @param openingHours     the opening hours of the vaccination center DTO.
     * @param closingHours     the closing hours of the vaccination center DTO.
     * @param slotDuration     the slot duration of the vaccination center DTO.
     * @param slotVaccineLimit the slot vaccine limit of the vaccination center DTO.
     */
    public VaccinationCenterDTO(int id, String name, String address, int phoneNumber, Email email, int faxNumber, String website, Time openingHours, Time closingHours, int slotDuration, int slotVaccineLimit) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.faxNumber = faxNumber;
        this.website = website;
        this.openingHours = openingHours;
        this.closingHours = closingHours;
        this.slotDuration = slotDuration;
        this.slotVaccineLimit = slotVaccineLimit;
    }

    /**
     * Method to return the id of the vaccination center DTO.
     *
     * @return the vaccination center name.
     */
    public int getId() {
        return id;
    }

    /**
     * Method to return the name of the vaccination center DTO.
     *
     * @return the name of the vaccination center DTO.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to return the address of the vaccination center DTO.
     *
     * @return the address of the vaccination center DTO.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method to return the phoneNumber of the vaccination center DTO.
     *
     * @return the phoneNumber of the vaccination center DTO.
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Method to return the email of the vaccination center DTO.
     *
     * @return the email of the vaccination center DTO.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Method to return the faxNumber of the vaccination center DTO.
     *
     * @return the faxNumber of the vaccination center DTO.
     */
    public int getFaxNumber() {
        return faxNumber;
    }

    /**
     * Method to return the website of the vaccination center DTO.
     *
     * @return the website of the vaccination center DTO.
     */
    public String getWebsite() {
        return website;
    }

    /**
     * Method to return the openingHours of the vaccination center DTO.
     *
     * @return the openingHours of the vaccination center DTO.
     */
    public Time getOpeningHours() {
        return openingHours;
    }

    /**
     * Method to return the closingHours of the vaccination center DTO.
     *
     * @return the closingHours of the vaccination center DTO.
     */
    public Time getClosingHours() {
        return closingHours;
    }

    /**
     * Method to return the slotDuration of the vaccination center DTO.
     *
     * @return the slotDuration of the vaccination center DTO.
     */
    public int getSlotDuration() {
        return slotDuration;
    }

    /**
     * Method to return the slotVaccineLimit of the vaccination center DTO.
     *
     * @return the slotVaccineLimit of the vaccination center DTO.
     */
    public int getSlotVaccineLimit() {
        return slotVaccineLimit;
    }

    /**
     * Returns the textual description of the vaccination center.
     *
     * @return characteristics of the vaccination center.
     */
    @Override
    public String toString() {
        String text = String.format("Name: %s%n", name);
        text += String.format("Phone Number: %s%n", phoneNumber);
        text += String.format("Email: %s%n", email);
        return text;
    }
}
