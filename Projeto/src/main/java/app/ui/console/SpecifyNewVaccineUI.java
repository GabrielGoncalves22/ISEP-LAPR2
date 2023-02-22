package app.ui.console;

import app.core.controller.SpecifyNewVaccineController;
import app.core.controller.SpecifyNewVaccineTypeController;
import app.core.domain.model.VaccineType;
import app.ui.console.utils.Utils;

import java.util.List;

/**
 * Represents the vaccine UI(User interface).
 *
 * @author Group 40
 */
public class SpecifyNewVaccineUI implements Runnable {
    /**
     * The vaccine controller.
     */
    private static SpecifyNewVaccineController specifyNewVaccineController = new SpecifyNewVaccineController();

    /**
     * Main method of the UI.
     */
    public void run() {
        System.out.println("\n*** New Vaccine ***");
        String name = Utils.readLineFromConsole("Insert name: ");
        String brand = Utils.readLineFromConsole("Insert brand: ");

        try {
            specifyNewVaccineController.createVaccine(name, brand, selectVaccineType());

            readAgeGroup(readNumberAgeGroup());

            showData();
            confirmData();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            System.out.println("The new vaccine was not created!");
        }
    }

    /**
     * Method for selecting the type of vaccine.
     *
     * @return the vaccine type of the vaccine.
     */
    private VaccineType selectVaccineType() {
        SpecifyNewVaccineTypeController specifyNewVaccineTypeController = new SpecifyNewVaccineTypeController();

        List<VaccineType> vtList = specifyNewVaccineTypeController.getVaccineTypeList();
        return (VaccineType) Utils.showAndSelectOne(vtList, "Select vaccine type: ");
    }

    /**
     * Function for reading information about number of age groups.
     *
     * @return number of age groups.
     */
    private int readNumberAgeGroup() {
        int numberAgeGroup = Utils.readIntegerFromConsole("Insert number of age groups: ");

        if (numberAgeGroup <= 0) {
            throw new IllegalArgumentException("Number of age groups cannot be negative, nor be zero..");
        }

        return numberAgeGroup;
    }

    /**
     * Method for reading information about age groups.
     *
     * @param numberAgeGroup the number of age groups.
     */
    private void readAgeGroup(int numberAgeGroup) {
        for (int ageGroup = 1; ageGroup <= numberAgeGroup; ageGroup++) {
            System.out.println("\n*** " + ageGroup + "º Age group ***");
            int minimumAge = Utils.readIntegerFromConsole("Insert minimum age: ");
            int maximumAge = Utils.readIntegerFromConsole("Insert maximum age: ");

            specifyNewVaccineController.createAgeGroup(minimumAge, maximumAge);

            int dosesAdministered = Utils.readIntegerFromConsole("Insert doses to administered: ");
            readDoses(dosesAdministered);
        }
    }

    /**
     * Method for reading information relating to doses.
     *
     * @param dosesAdministered the doses administered of the age group.
     */
    private void readDoses(int dosesAdministered) {
        for (int dose = 1; dose <= dosesAdministered; dose++) {
            System.out.println("*** " + dose + "º Dose ***");
            int doseNumber = Utils.readIntegerFromConsole("Insert dose number: ");
            int dosage = Utils.readIntegerFromConsole("Insert dosage: ");
            int timeIntervalBetweenLastDose = Utils.readIntegerFromConsole("Insert time interval between last dose: ");

            specifyNewVaccineController.createDose(doseNumber, dosage, timeIntervalBetweenLastDose);
        }
    }

    /**
     * Method to request confirmation of data.
     */
    private void confirmData() {
        if (Utils.confirm("Want to confirm the data? (y/n)")) {
            if (specifyNewVaccineController.saveVaccine()) {
                System.out.println("New vaccine successfully adds!");
            } else {
                System.out.println("Could not add the new vaccine!");
            }
        } else {
            System.out.println("The new vaccine was not created!");
        }
    }

    /**
     * Method to show the data of the new type of vaccine.
     */
    private void showData() {
        System.out.println("\n*** New Vaccine Data ***");
        System.out.println(specifyNewVaccineController.getVaccine());
    }
}
