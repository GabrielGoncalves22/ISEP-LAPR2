package app.core.domain.model;

import java.io.Serializable;

/**
 * It represents an employee role through id and designation.
 *
 * @author Group 40
 */
public class EmployeeRole implements Comparable, Serializable {

    /**
     * The variable id holds the identifier of the role.
     */
    private String id;

    /**
     * The variable designation holds the description of the role.
     */
    private String designation;

    /**
     * Builds an instance of EmployeeRole receiving the id and the designation.
     *
     * @param id          the identifier of employee role.
     * @param designation the designation of employee role.
     */
    public EmployeeRole(String id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    /**
     * Method to return the employee's role designation.
     *
     * @return This employee role designation.
     */
    public String getDesignation() {
        return designation;
    }

    /**
     * This function constructs and returns a String representative of the object.
     *
     * @return A String representing this object.
     */
    @Override
    public String toString() {
        return String.format("ID: %s | Description: %s", this.id, this.designation);
    }

    /**
     * Method to perform the comparison between two employee roles.
     *
     * @param otherEmployeeRole The role of the employee to which we are going to compare with this current one.
     * @return 0 if they are the same (same ID).
     */
    @Override
    public int compareTo(Object otherEmployeeRole) {
        EmployeeRole er = (EmployeeRole) otherEmployeeRole;

        if (this.id.compareTo(er.id) == 0 && this.designation.compareTo(er.designation) == 0) {
            return 0;
        }

        return -1;
    }
}
