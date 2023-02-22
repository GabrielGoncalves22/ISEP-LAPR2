package app.ui.console;

import app.core.controller.SpecifyNewVaccineTypeController;
import app.core.domain.shared.VaccineTypeTechnology;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the vaccine type UI(User interface).
 *
 * @author Group 40
 */
public class SpecifyNewVaccineTypeUI implements Runnable {
    /**
     * The vaccine type controller.
     */
    private static SpecifyNewVaccineTypeController specifyNewVaccineTypeController = new SpecifyNewVaccineTypeController();

    /**
     * Main method of the UI.
     */
    public void run() {
        System.out.println("\n*** New Vaccine Type ***");
        String code = Utils.readLineFromConsole("Insert code: ");
        String description = Utils.readLineFromConsole("Insert description: ");

        int technologyOrder = Utils.showAndSelectIndex(new ArrayList<>(Arrays.asList(VaccineTypeTechnology.values())), "Select technology: ");

        try {
            specifyNewVaccineTypeController.createVaccineType(code, description, VaccineTypeTechnology.technologyDesignation(technologyOrder));
            showData();
            confirmData();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Could not create new type of vaccine!");
        }
    }

    /**
     * Method to request confirmation of data.
     */
    private void confirmData() {
        if (Utils.confirm("Want to confirm the data? (y/n)")) {
            if (specifyNewVaccineTypeController.saveVaccineType()) {
                System.out.println("New vaccine type successfully ads!");
            } else {
                System.out.println("Could not create new type of vaccine!");
            }
        } else {
            System.out.println("The new type of vaccine was not created!");
        }
    }

    /**
     * Method to show the data of the new type of vaccine.
     */
    private void showData() {
        System.out.println("*** New Vaccine Type Data ***");
        System.out.println(specifyNewVaccineTypeController.getVaccineType());
    }
}
