package app.ui.console;

import app.core.controller.RegisterVaccinationCenterController;
import app.core.domain.model.Time;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;

/**
 * Represents the vaccine center UI(User interface).
 *
 * @author Group 40
 */
public class RegisterVaccinationCenterUI implements Runnable {

    /**
     * The vaccine center controller.
     */
    RegisterVaccinationCenterController registerVaccinationCenterController = new RegisterVaccinationCenterController();


    /**
     * Main method of the UI.
     */
    @Override
    public void run() {


        try {
            System.out.println("\n*** Vaccination Center ***");
            String name = Utils.readLineFromConsole("Insert name: ");
            String address = Utils.readLineFromConsole("Insert address: ");
            int phoneNumber = Utils.readIntegerFromConsole("Insert phone number: ");
            String emailString = Utils.readLineFromConsole("Insert email: ");
            Email email = new Email(emailString);
            int faxNumber = Utils.readIntegerFromConsole("Insert fax number: ");
            String website = Utils.readLineFromConsole("Insert website: ");

            System.out.println("Insert Opening Hours: ");
            int hoursOpeningHours = Utils.readIntegerFromConsole("Insert hours: ");
            int minutesOpeningHours = Utils.readIntegerFromConsole("Insert minutes: ");
            Time openingHours = registerVaccinationCenterController.createSchedule(hoursOpeningHours, minutesOpeningHours);

            System.out.println("Insert Closing Hours: ");
            int hoursClosingHours = Utils.readIntegerFromConsole("Insert hours: ");
            int minutesClosingHours = Utils.readIntegerFromConsole("Insert minutes: ");
            Time closingHours = registerVaccinationCenterController.createSchedule(hoursClosingHours, minutesClosingHours);

            int slotDuration = Utils.readIntegerFromConsole("Insert slot duration: ");
            int slotVaccineLimit = Utils.readIntegerFromConsole("Insert slot vaccine limit: ");

            registerVaccinationCenterController.createVaccinationCenter(name, address, phoneNumber, email, faxNumber, website, openingHours, closingHours,
                    slotDuration, slotVaccineLimit);

            showData(name, address, phoneNumber, emailString, faxNumber, website, openingHours, closingHours,
                    slotDuration, slotVaccineLimit);

            confirmData();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }

    }

    /**
     * Method to request confirmation of data.
     */
    private void confirmData() {
        if (Utils.confirm("Want to confirm the data? (y/n)")) {
            if (registerVaccinationCenterController.saveVaccinationCenter()) {
                System.out.println("New vaccine center successfully ads!");
            } else {
                System.out.println("Could not create new vaccine center!");
            }
        } else {
            System.out.println("The new vaccine center was not created!");
        }
    }

    /**
     * Method to show the data of the new vaccine center.
     */
    private void showData(String name, String address, int phoneNumber, String email,
                          int faxNumber, String website, Time openingHours, Time closingHours,
                          int slotDuration, int slotVaccineLimit) {

        System.out.println("*** New Vaccination Center Data ***");
        System.out.println("Name: " + name);
        System.out.println("Address: " + address);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Fax Number: " + faxNumber);
        System.out.println("Website: " + website);
        System.out.println("Opening Hours: " + openingHours);
        System.out.println("Closing Hours: " + closingHours);
        System.out.println("Slot Duration: " + slotDuration);
        System.out.println("Slot Vaccine Limit: " + slotVaccineLimit);
    }
}

