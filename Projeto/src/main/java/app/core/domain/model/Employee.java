package app.core.domain.model;

import org.apache.commons.lang3.StringUtils;
import pt.isep.lei.esoft.auth.domain.model.Email;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Represents an employee through name, address, phoneNumber, email, citizenCardNumber and role.
 *
 * @author Group 40
 */
public class Employee implements Serializable {

    /**
     * The variable id holds the id of the employee.
     */
    private int id;

    /**
     * The variable user holds the authentication conditions for the employee.
     */
    transient private User user;

    /**
     * The variable name holds the name of the employee.
     */
    private String name;

    /**
     * The variable address holds the address or the employee.
     */
    private String address;

    /**
     * The variable phoneNumber holds the phone number of the employee.
     */
    private int phoneNumber;

    /**
     * The variable citizenCardNumber holds the citizen card number of the employee.
     */
    private int citizenCardNumber;

    /**
     * The variable email holds the email of the employee.
     */
    transient private Email email;

    /**
     * The variable option holds the option made for the role of the employee.
     */
    private EmployeeRole role;

    /**
     * The number of existing employees.
     */
    private static int numberEmployees;

    /**
     * This function creates an instance receiving name, address, phoneNumber, email, citizenCardNumber and role as parameters.
     *
     * @param name              the variable name holds the name of the employee.
     * @param address           the variable address holds the address of the employee.
     * @param phoneNumber       the variable phoneNumber holds the phone number of the employee.
     * @param citizenCardNumber the variable citizenCardNumber holds the citizen card number of the employee.
     * @param role              the variable role holds the id and designation of the role.
     */
    public Employee(String name, String address, int phoneNumber, String email, int citizenCardNumber, EmployeeRole role) {

        checkName(name);
        checkAddress(address);
        checkPhoneNumber(phoneNumber);
        checkCitizenCardNumber(citizenCardNumber);
        checkRole(role);

        this.id = ++numberEmployees;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.citizenCardNumber = citizenCardNumber;
        this.email = new Email(email);
        this.role = role;
    }

    /**
     * Method to return the employee id
     *
     * @return The id of the employee
     */
    public int getId() {
        return id;
    }

    /**
     * Method to return the employee name
     *
     * @return The name of the employee
     */
    public String getName() {
        return name;
    }

    /**
     * Method to return the email of this employee
     *
     * @return The email of the employee
     */
    public Email getEmail() {
        return email;
    }

    /**
     * Method to return the employee role designation
     *
     * @return The Employee role designation
     */
    public String getRoleDesignation() {
        return this.role.getDesignation();
    }

    /**
     * Method to set the employee user information
     *
     * @param user The user corresponding to this employee
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * This function constructs and returns a String representative of the object
     *
     * @return A String representing this object
     */
    @Override
    public String toString() {
        String text = String.format("Name: %s%n", name);
        text += String.format("Address: %s%n", address);
        text += String.format("Phone Number: %d%n", phoneNumber);
        text += String.format("E-mail: %s%n", this.email.getEmail());
        text += String.format("Citizen Card Number: %d%n", citizenCardNumber);
        text += String.format("Role: %s%n", role.getDesignation());

        return text;
    }

    /**
     * This function makes the comparison between the current employee's role with another existing role in the object
     * passed by parameter
     *
     * @param e It is an object that contains information related to EmployeeRole
     * @return 0 if the roles of the employees are the same
     */
    public int compareRoleWithOtherEmployeeRole(Object e) {
        return this.role.compareTo(e);
    }

    /**
     * This function checks if the variable name is null.
     *
     * @param name the variable name holds the name of the employee.
     */
    private void checkName(String name) {
        if ((StringUtils.isBlank(name))) {
            throw new IllegalArgumentException("The name can't be blank.");
        }
    }

    /**
     * This function checks if the variable address is null.
     *
     * @param address the variable address holds the address of the employee.
     */
    private void checkAddress(String address) {
        if ((StringUtils.isBlank(address))) {
            throw new IllegalArgumentException("The address can't be blank.");
        }
    }

    /**
     * This function checks if the variable phone number has nine digits.
     *
     * @param phoneNumber the variable phoneNumber holds the phone number of the employee.
     */
    private void checkPhoneNumber(int phoneNumber) {
        if (phoneNumber < 100000000 || phoneNumber > 999999999) {

            throw new IllegalArgumentException("The phone number must be 9 digits.");
        }
    }

    /**
     * This function checks if the variable citizen card number has nine digits.
     *
     * @param citizenCardNumber the variable citizenCardNumber holds the citizen card number of the employee.
     */
    private void checkCitizenCardNumber(int citizenCardNumber) {
        if (citizenCardNumber < 100000000 || citizenCardNumber > 999999999) {

            throw new IllegalArgumentException("The citizen card number must be 9 digits.");
        }
    }

    /**
     * This function checks if the variable role is null.
     *
     * @param role the variable role holds the id and designation of the role.
     */
    private void checkRole(EmployeeRole role) {
        if (role == null) {

            throw new IllegalArgumentException("The role can't be blank.");
        }
    }

    /**
     * Method to check if another employee object is equals to this one.
     *
     * @param otherObject The other (employee) object
     * @return True if they are equals to each other, or False otherwise
     */
    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }

        if (otherObject == null || this.getClass() != otherObject.getClass()) {
            return false;
        }

        Employee otherEmployee = (Employee) otherObject;

        return email.getEmail().equals(otherEmployee.email.getEmail()) &&
                this.name.equals(otherEmployee.name) &&
                this.address.equals(otherEmployee.address) &&
                this.phoneNumber == otherEmployee.phoneNumber &&
                this.citizenCardNumber == otherEmployee.citizenCardNumber &&
                this.compareRoleWithOtherEmployeeRole(otherEmployee.role) == 0;

    }

    /**
     * Method to personalize the serialization write process of the employee
     *
     * @param os The ObjectOutputStream to the employee file
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
     * Method to personalize the serialization read process of the employee
     *
     * @param is The ObjectInputStream to the employee file
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
