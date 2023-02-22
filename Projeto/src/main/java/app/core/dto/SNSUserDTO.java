package app.core.dto;

import app.core.domain.model.Date;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;

/**
 * Represents the SNS user DTO.
 *
 * @author Group 40
 */
public class SNSUserDTO {

    /**
     * The name of the SNS User.
     */
    private String name;

    /**
     * The address of the SNS User.
     */
    private String address;

    /**
     * The sex of SNS User.
     */
    private String sex;

    /**
     * The phoneNumber of SNS User.
     */
    private String phoneNumber;

    /**
     * The email of the SNS User.
     */
    private Email email;

    /**
     * The birth-date of the SNS User.
     */
    private Date birthdate;

    /**
     * The SNS User number of SNS User.
     */
    private String snsUserNumber;

    /**
     * The citizen card number of SNS User.
     */
    private String citizenCardNumber;

    /**
     * Instantiates a new SNS User with all fields.
     *
     * @param name              the name of the SNS User DTO.
     * @param address           the address of SNS User DTO.
     * @param sex               the sex of SNS User DTO.
     * @param phoneNumber       the phoneNumber of SNS User DTO.
     * @param email             the email of SNS User DTO.
     * @param birthdate         the birth-date of the SNS User DTO.
     * @param snsUserNumber     the SNS User number of SNS User DTO.
     * @param citizenCardNumber the citizen card number of SNS User DTO.
     */
    public SNSUserDTO(String name, String address, String sex, String phoneNumber, Email email, Date birthdate,
                      String snsUserNumber, String citizenCardNumber) {
        this.name = name;
        this.address = address;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthdate = birthdate;
        this.snsUserNumber = snsUserNumber;
        this.citizenCardNumber = citizenCardNumber;
    }

    /**
     * Function to return the name.
     *
     * @return the name.
     */
    public String getName() {
        return name;
    }

    /**
     * Function to return the sex.
     *
     * @return the sex.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Function to return the phone number.
     *
     * @return the phone number.
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Function to return the birthdate.
     *
     * @return the birthdate.
     */
    public Date getBirthdate() {
        return birthdate;
    }

    /**
     * Function to return the SNS User number.
     *
     * @return the SNS User number of SNS User.
     */
    public String getSnsUserNumber() {
        return snsUserNumber;
    }

    /**
     * Function to return the address.
     *
     * @return the address of SNS User.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Function to return the email.
     *
     * @return the email of SNS User.
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Function to return the citizen card number.
     *
     * @return the citizen card number of SNS User.
     */
    public String getCitizenCardNumber() {
        return citizenCardNumber;
    }

    /**
     * Returns the textual description of the SNS User.
     *
     * @return characteristics of the SNS User.
     */
    @Override
    public String toString() {
        String text = String.format("Name: %s%n", name);
        text += String.format("Sex: %s%n", sex);
        text += String.format("Phone Number: %s%n", phoneNumber);
        text += String.format("Birth date: %s%n", birthdate);
        text += String.format("Sns Number: %s%n", snsUserNumber);
        return text;
    }

    /**
     * Returns the textual description of the SNS User when it is imported from a file.
     */
    public String toStringFileImported() {
        return String.format("| %-20s | %-10s | %-10s | %-30s | %-12s | %-20s | %-15s | %-17s |",
                Utils.stringLimiter(this.name, 17, "..."), this.sex,
                this.birthdate.toDayMonthYearString(), Utils.stringLimiter(this.address, 27, "..."),
                this.phoneNumber, Utils.stringLimiter(this.email.getEmail(), 17, "..."),
                this.snsUserNumber, this.citizenCardNumber);
    }
}
