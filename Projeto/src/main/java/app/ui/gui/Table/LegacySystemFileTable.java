package app.ui.gui.table;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Class that represents the table properties for the LegacySystemFile
 *
 * @author Group 40
 */
public class LegacySystemFileTable {

    /**
     * Property that represents the SNS user number.
     */
    private StringProperty snsUserNumber;

    /**
     * Property that represents the SNS user name.
     */
    private StringProperty snsUserName;

    /**
     * Property that represents the vaccine name.
     */
    private StringProperty vaccineName;

    /**
     * Property that represents the vaccine type short description.
     */
    private StringProperty vaccineTypeShortDescription;

    /**
     * Property that represents the dose of the vaccine.
     */
    private StringProperty dose;

    /**
     * Property that represents the lot number.
     */
    private StringProperty lotNumber;

    /**
     * Property that represents the scheduled date and time.
     */
    private StringProperty scheduledDateTime;

    /**
     * Property that represents the arrival date and time to the center.
     */
    private StringProperty arrivalDateTime;

    /**
     * Property that represents the date and time of nurse administration of the vaccine.
     */
    private StringProperty nurseAdministrationDateTime;

    /**
     * Property that represents the leaving date and time from the user.
     */
    private StringProperty leavingDateTime;

    /**
     * Constructor to build an instance of this class.
     *
     * @param snsUserNumber               Represents the SNS user number.
     * @param snsUserName                 Represents the SNS user name.
     * @param vaccineName                 Represents the vaccine name.
     * @param vaccineTypeShortDescription Represents the vaccine type short description.
     * @param dose                        Represents the dose of the vaccine.
     * @param lotNumber                   Represents the lot number.
     * @param scheduledDateTime           Represents the scheduled date and time.
     * @param arrivalDateTime             Represents the arrival date and time to the center.
     * @param nurseAdministrationDateTime Represents the date and time of nurse administration of the vaccine.
     * @param leavingDateTime             Represents the leaving date and time from the user.
     */
    public LegacySystemFileTable(String snsUserNumber, String snsUserName, String vaccineName,
                                 String vaccineTypeShortDescription, String dose, String lotNumber,
                                 String scheduledDateTime, String arrivalDateTime, String nurseAdministrationDateTime,
                                 String leavingDateTime) {
        this.snsUserNumber = new SimpleStringProperty(snsUserNumber);
        this.snsUserName = new SimpleStringProperty(snsUserName);
        this.vaccineName = new SimpleStringProperty(vaccineName);
        this.vaccineTypeShortDescription = new SimpleStringProperty(vaccineTypeShortDescription);
        this.dose = new SimpleStringProperty(dose);
        this.lotNumber = new SimpleStringProperty(lotNumber);
        this.scheduledDateTime = new SimpleStringProperty(scheduledDateTime);
        this.arrivalDateTime = new SimpleStringProperty(arrivalDateTime);
        this.nurseAdministrationDateTime = new SimpleStringProperty(nurseAdministrationDateTime);
        this.leavingDateTime = new SimpleStringProperty(leavingDateTime);
    }

    /**
     * Method to return the SNS user number property.
     *
     * @return The SNS user number property.
     */
    public StringProperty snsUserNumberProperty() {
        return snsUserNumber;
    }

    /**
     * Method to return the SNS user name property.
     *
     * @return The SNS user name property.
     */
    public StringProperty snsUserNameProperty() {
        return snsUserName;
    }

    /**
     * Method to return the vaccine name property.
     *
     * @return The vaccine name property.
     */
    public StringProperty vaccineNameProperty() {
        return vaccineName;
    }

    /**
     * Method to return the vaccine type short description property.
     *
     * @return The vaccine type short description property.
     */
    public StringProperty vaccineTypeShortDescriptionProperty() {
        return vaccineTypeShortDescription;
    }

    /**
     * Method to return the dose property.
     *
     * @return The dose property.
     */
    public StringProperty doseProperty() {
        return dose;
    }

    /**
     * Method to return the lot number property.
     *
     * @return The lot number property.
     */
    public StringProperty lotNumberProperty() {
        return lotNumber;
    }

    /**
     * Method to return the scheduled date and time property.
     *
     * @return The scheduled date and time property.
     */
    public StringProperty scheduledDateTimeProperty() {
        return scheduledDateTime;
    }

    /**
     * Method to return the SNS user arrival date and time property.
     *
     * @return The SNS user arrival date and time property.
     */
    public StringProperty arrivalDateTimeProperty() {
        return arrivalDateTime;
    }

    /**
     * Method to return the nurse vaccine administration date and time property.
     *
     * @return The nurse vaccine administration date and time property.
     */
    public StringProperty nurseAdministrationDateTimeProperty() {
        return nurseAdministrationDateTime;
    }

    /**
     * Method to return the leaving date and time by the SNS user property.
     *
     * @return The leaving date and time by the SNS user property.
     */
    public StringProperty leavingDateTimeProperty() {
        return leavingDateTime;
    }
}
