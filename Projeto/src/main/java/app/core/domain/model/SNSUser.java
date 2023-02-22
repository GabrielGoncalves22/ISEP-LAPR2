package app.core.domain.model;

import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * It represents a SNSUser through name, address, sex, phoneNumber, email, birthdate, SNSUserNumber and citizenCardNumber.
 *
 * @author Group 40
 */
public class SNSUser implements Serializable {
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
    transient private Email email;

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
     * Instantiates a new SNS User.
     *
     * @param name              the name of the SNS User.
     * @param address           the address of the SNS User.
     * @param sex               the sex of SNS User.
     * @param phoneNumber       the phoneNumber of SNS User.
     * @param email             the email of the SNS User.
     * @param birthdate         the birth-date of the SNS User.
     * @param snsUserNumber     the SNS User number of SNS User.
     * @param citizenCardNumber the citizen card number of SNS User.
     */
    public SNSUser(String name, String address, String sex, String phoneNumber, Email email,
                   Date birthdate, String snsUserNumber, String citizenCardNumber) {

        checkName(name);
        checkAddress(address);
        checkPhoneNumber(phoneNumber);
        checkSnsUserNumber(snsUserNumber);
        checkCitizenCardNumber(citizenCardNumber);

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
     * Instantiates a new SNS User.
     *
     * @param name          the name of the SNS User.
     * @param sex           the sex of SNS User.
     * @param phoneNumber   the phoneNumber of SNS User.
     * @param birthdate     the birth-date of the SNS User.
     * @param snsUserNumber the SNS User number of SNS User.
     */
    public SNSUser(String name, String sex, String phoneNumber, Date birthdate, String snsUserNumber) {
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.birthdate = birthdate;
        this.snsUserNumber = snsUserNumber;
    }

    /**
     * Method to check if the name is in a valid state.
     *
     * @param name the name of the SNS User.
     */
    private void checkName(String name) {
        if (name.equals("")) {
            throw new IllegalArgumentException("The SNS User name cannot be blank!");
        }
    }

    /**
     * Method to check if the address is in a valid state.
     *
     * @param address the address of the SNS User.
     */
    private void checkAddress(String address) {
        if (address.equals("")) {
            throw new IllegalArgumentException("The SNS User address cannot be blank!");
        }
    }

    /**
     * Method to check if the phone number is in a valid state.
     *
     * @param phoneNumber the phoneNumber of SNS User.
     */
    private void checkPhoneNumber(String phoneNumber) {
        if (phoneNumber.equals("")) {
            throw new IllegalArgumentException("The SNS User phone number cannot be blank!");
        }
    }

    /**
     * Method to check if the SNS user number is in a valid state.
     *
     * @param snsUserNumber the SNS User number of SNS User.
     */
    private void checkSnsUserNumber(String snsUserNumber) {
        if (snsUserNumber.equals("")) {
            throw new IllegalArgumentException("The SNS User number cannot be blank!");
        }
    }

    /**
     * Method to check if the citizen card number is in a valid state.
     *
     * @param citizenCardNumber the citizen card number of SNS User.
     */
    private void checkCitizenCardNumber(String citizenCardNumber) {
        if (citizenCardNumber.equals("")) {
            throw new IllegalArgumentException("The SNS User citizen card number cannot be blank!");
        }
    }

    /**
     * Function to return the name.
     *
     * @return the name of SNS User.
     */
    public String getName() {
        return name;
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
     * Function to return the sex.
     *
     * @return the sex of SNS User.
     */
    public String getSex() {
        return sex;
    }

    /**
     * Function to return the phone number.
     *
     * @return the phone number of SNS User.
     */
    public String getPhoneNumber() {
        return phoneNumber;
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
     * Function to return the birthdate.
     *
     * @return the birthdate of SNS User.
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
     * Function to return the citizen card number of SNS User.
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
        return String.format("Name: %s \nAddress: %s \nSex: %s \nPhone Number: %s \nEmail: %s \nBirth date: %s " +
                        "\nSNS user number: %s \nCitizen card number: %s",
                this.name, this.address, this.sex, this.phoneNumber, this.email, this.birthdate, this.snsUserNumber, this.citizenCardNumber);
    }

    /**
     * Compares the SNS User with the object received.
     *
     * @param otherObject the object to be compared with the SNS User.
     * @return true if the object received represents another SNS User equivalent to the SNS User.
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

        SNSUser otherSNSUser = (SNSUser) otherObject;
        return (this.phoneNumber.equals(otherSNSUser.phoneNumber)
                && (this.email.equals(otherSNSUser.email))
                && (this.snsUserNumber.equals(otherSNSUser.snsUserNumber))
                && (this.citizenCardNumber.equals(otherSNSUser.citizenCardNumber)));
    }

    /**
     * Method to personalize the serialization write process of the SNS User.
     *
     * @param os The ObjectOutputStream to the SNS User file.
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
     * Method to personalize the serialization read process of the SNS User.
     *
     * @param is The ObjectInputStream to the SNS User file.
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
