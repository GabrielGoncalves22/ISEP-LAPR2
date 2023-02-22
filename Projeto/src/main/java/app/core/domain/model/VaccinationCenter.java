package app.core.domain.model;

import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * It represents a vaccination center through name, address, phone Number, email, fax Number, website, opening hours,
 * closing hours, slot duration and slot vaccine limit.
 *
 * @author Group 40
 */
public class VaccinationCenter implements Serializable {
    /**
     * The id of the vaccination center.
     */
    private int id;

    /**
     * The name of the vaccination center.
     */
    private String name;

    /**
     * The address of the vaccination center.
     */
    private String address;

    /**
     * The phoneNumber of the vaccination center.
     */
    private int phoneNumber;

    /**
     * The email of the vaccination center.
     */
    transient private Email email;

    /**
     * The faxNumber of the vaccination center.
     */
    private int faxNumber;

    /**
     * The website of the vaccination center.
     */
    private String website;

    /**
     * The openingHours of the vaccination center.
     */
    private Time openingHours;

    /**
     * The closingHours of the vaccination center.
     */
    private Time closingHours;

    /**
     * The slotDuration of the vaccination center.
     */
    private int slotDuration;

    /**
     * The slotVaccineLimit of the vaccination center.
     */
    private int slotVaccineLimit;

    /**
     * The id of the vaccination center coordinator.
     */
    private int centerCoordinatorId;

    /**
     * The number of existing vaccination centers.
     */
    private static int numberVaccinationCenters;

    /**
     * Instantiates a new Vaccine center.
     *
     * @param name             the name of the vaccination center.
     * @param address          the address of the vaccination center.
     * @param phoneNumber      the phone number of the vaccination center.
     * @param email            the email of the vaccination center.
     * @param faxNumber        the fax number of the vaccination center.
     * @param website          the website of the vaccination center.
     * @param openingHours     the opening hours of the vaccination center.
     * @param closingHours     the closing hours of the vaccination center.
     * @param slotDuration     the slot duration of the vaccination center.
     * @param slotVaccineLimit the slot vaccine limit of the vaccination center.
     */
    public VaccinationCenter(String name, String address, int phoneNumber, Email email,
                             int faxNumber, String website, Time openingHours, Time closingHours,
                             int slotDuration, int slotVaccineLimit) {

        checkName(name);
        checkAddress(address);
        checkPhoneNumber(phoneNumber);
        checkFaxNumber(faxNumber);
        checkWebsite(website);
        checkSlotDuration(slotDuration);
        checkSlotVaccineLimit(slotVaccineLimit);

        this.id = ++numberVaccinationCenters;
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
     * Instantiates a new vaccination center.
     *
     * @param id               the id of the vaccination center.
     * @param name             the name of the vaccination center.
     * @param address          the address of the vaccination center.
     * @param phoneNumber      the phone number of the vaccination center.
     * @param email            the email of the vaccination center.
     * @param faxNumber        the fax number of the vaccination center.
     * @param website          the website of the vaccination center.
     * @param openingHours     the opening hours of the vaccination center.
     * @param closingHours     the closing hours of the vaccination center.
     * @param slotDuration     the slot duration of the vaccination center.
     * @param slotVaccineLimit the slot vaccine limit of the vaccination center.
     */
    public VaccinationCenter(int id, String name, String address, int phoneNumber, Email email,
                             int faxNumber, String website, Time openingHours, Time closingHours, int slotDuration,
                             int slotVaccineLimit) {
        checkName(name);
        checkAddress(address);
        checkPhoneNumber(phoneNumber);
        checkFaxNumber(faxNumber);
        checkWebsite(website);
        checkSlotDuration(slotDuration);
        checkSlotVaccineLimit(slotVaccineLimit);

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
     * Method for checking the rules of the vaccination center name.
     *
     * @param name the name of the vaccination center.
     */
    private void checkName(String name) {
        if ((StringUtils.isBlank(name))) {
            throw new IllegalArgumentException("Name cannot be blank.");
        }
    }

    /**
     * Method for checking the rules of the vaccination center address.
     *
     * @param address the address of the vaccination center.
     */
    private void checkAddress(String address) {
        if ((StringUtils.isBlank(address))) {
            throw new IllegalArgumentException("Address cannot be blank.");
        }
    }

    /**
     * Method for checking the rules of the vaccination center phoneNumber.
     *
     * @param phoneNumber the phoneNumber of the vaccination center.
     */
    private void checkPhoneNumber(int phoneNumber) {
        if (phoneNumber < 100000000 || phoneNumber > 999999999) {

            throw new IllegalArgumentException("The phone number must be 9 digits.");
        }
    }

    /**
     * Method for checking the rules of the vaccination center faxNumber.
     *
     * @param faxNumber the faxNumber of the vaccination center.
     */
    private void checkFaxNumber(int faxNumber) {
        if (faxNumber < 100000000 || faxNumber > 999999999) {

            throw new IllegalArgumentException("The fax number must be 9 digits.");
        }
    }

    /**
     * Method for checking the rules of the vaccination center website.
     *
     * @param website the website of the vaccination center.
     */
    private void checkWebsite(String website) {
        if ((StringUtils.isBlank(website))) {
            throw new IllegalArgumentException("Website cannot be blank.");
        }
    }

    /**
     * Method for checking the rules of the vaccination center slotDuration.
     *
     * @param slotDuration the slotDuration of the vaccination center .
     */
    private void checkSlotDuration(int slotDuration) {
        if (slotDuration < 0) {
            throw new IllegalArgumentException("Value cannot be negative.");
        }
    }

    /**
     * Method for checking the rules of the vaccination center slotVaccineLimit.
     *
     * @param slotVaccineLimit the slotVaccineLimit of the vaccination center .
     */
    private void checkSlotVaccineLimit(int slotVaccineLimit) {
        if (slotVaccineLimit < 0) {
            throw new IllegalArgumentException("Value cannot be negative.");
        }
    }

    /**
     * Method to assign the id of the vaccination center coordinator.
     *
     * @param centerCoordinatorId the id of the vaccination center coordinator.
     */
    public void setCenterCoordinatorId(int centerCoordinatorId) {
        this.centerCoordinatorId = centerCoordinatorId;
    }

    /**
     * Method to return the vaccination center name.
     *
     * @return The vaccination center name.
     */
    public String getName() {
        return name;
    }

    /**
     * Method to return the vaccination center phone number.
     *
     * @return The vaccination center phone number.
     */
    public int getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Method to return the vaccination center email.
     *
     * @return The vaccination center email.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Method to return the id of the vaccination center coordinator.
     *
     * @return The id of the vaccination center coordinator.
     */
    public int getCenterCoordinatorId() {
        return centerCoordinatorId;
    }

    /**
     * Returns the textual description of the vaccination center.
     *
     * @return characteristics of the vaccination center.
     */
    @Override
    public String toString() {
        return String.format("Name: %s \nAddress: %s \nPhone Number: %d \nEmail: %s \nFax Number: %d " +
                        "\nWebsite: %s \nOpening Hours: %s \nClosing Hours: %s \nSlot Duration: %d \nSlot Vaccine Limit: %d",
                this.name, this.address, this.phoneNumber, this.email, this.faxNumber, this.website,
                this.openingHours, this.closingHours, this.slotDuration, this.slotVaccineLimit);
    }

    /**
     * Compares the vaccination center with the object received.
     *
     * @param otherObject the object to be compared with the vaccination center.
     * @return true if the object received represents another vaccination center equivalent to the vaccination center.
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

        VaccinationCenter otherVaccinationCenter = (VaccinationCenter) otherObject;
        return (this.id == otherVaccinationCenter.id)
                && (this.name.equals(otherVaccinationCenter.name))
                && (this.phoneNumber == otherVaccinationCenter.phoneNumber)
                && (this.email.equals(otherVaccinationCenter.email));
    }

    /**
     * This function gets the id of the vaccination center.
     *
     * @return the id.
     */
    public int getId() {
        return id;
    }

    /**
     * This function gets the opening hours of the vaccination center.
     *
     * @return the opening hours.
     */
    public Time getOpeningHours() {
        return openingHours;
    }

    /**
     * This function gets the closing hours of the vaccination center.
     *
     * @return the closing hours.
     */
    public Time getClosingHours() {
        return closingHours;
    }

    /**
     * This function gets the slot duration of the vaccination center.
     *
     * @return the slot duration.
     */
    public int getSlotDuration() {
        return slotDuration;
    }

    /**
     * This function gets the address of the vaccination center.
     *
     * @return the address.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Method to personalize the serialization write process of the Vaccination center.
     *
     * @param os The ObjectOutputStream to the Vaccination center file.
     */
    private void writeObject(ObjectOutputStream os) {
        try {
            os.defaultWriteObject();
            os.writeObject(this.email.getEmail());
        } catch (Exception ex) {
            //
        }
    }

    /**
     * Method to personalize the serialization read process of the Vaccination center.
     *
     * @param is The ObjectInputStream to the Vaccination center file.
     */
    private void readObject(ObjectInputStream is) {
        try {
            is.defaultReadObject();
            String email = (String) is.readObject();
            this.email = new Email(email);
        } catch (Exception ex) {
            //
        }
    }
}
