package app.ui.console;

import app.core.controller.RegisterArrivalSNSUserController;
import app.core.dto.ScheduleVaccineDTO;
import app.ui.console.utils.Utils;

import java.util.Collections;
import java.util.List;

/**
 * Represents the SNS user arrival UI(User interface).
 *
 * @author Group 40
 */
public class RegisterArrivalSNSUserUI implements Runnable {
    /**
     * The arrival SNS user controller.
     */
    RegisterArrivalSNSUserController registerArrivalSNSUserController;

    public RegisterArrivalSNSUserUI(int vcId) {
        registerArrivalSNSUserController = new RegisterArrivalSNSUserController(vcId);
    }

    /**
     * Main method of the UI.
     */
    @Override
    public void run() {
        try {
            if (registerArrivalSNSUserController.createSNSUser(readSNSUserNumber())) {

                List<ScheduleVaccineDTO> listSVDto = registerArrivalSNSUserController.getListSNSUserVaccinationSchedule();

                if (!listSVDto.isEmpty()) {
                    Collections.sort(listSVDto);
                    Utils.showList(listSVDto, "Vaccination schedule list: ");

                    if (Utils.confirm("Accept the arrival of the SNS user? (y/n)")) {
                        if (registerArrivalSNSUserController.createArrivalSNSUser()) {
                            showData();
                            confirmData();
                        } else {
                            System.out.println("No duplicate entry is possible for the same SNS user on the same day or vaccination period.");
                        }
                    }

                } else {
                    System.out.println("There is no schedule of vaccines for the indicated SNS user.");
                }

            } else {
                System.out.println("The SNS user number indicated does not correspond to any registered user.");
            }
        } catch (Exception e) {
            System.out.println("Unable to register arrival of SNS user.");
        }
    }

    /**
     * Function to read the SNS user number.
     *
     * @return the SNS user.
     */
    private String readSNSUserNumber() {
        String snsUserNumber = "";
        boolean state = false;

        while (!state) {
            snsUserNumber = Utils.readLineFromConsole("Insert SNS User Number: ");
            if (snsUserNumber.matches("[0-9]+") && snsUserNumber.length() == 9) {
                state = true;
            }
        }

        return snsUserNumber;
    }

    /**
     * Method to request confirmation of data.
     */
    private void confirmData() {
        if (Utils.confirm("Do you want to confirm the arrival of the sns user? (y/n)")) {
            registerArrivalSNSUserController.saveArrivalSNSUser();
            System.out.println("Successfully registered the arrival of the SNS user.");
        } else {
            System.out.println("The arrival of the SNS user was not successfully registered!");
        }
    }

    /**
     * Method to show the data of the SNS user arrival.
     */
    private void showData() {
        System.out.println("\n*** SNS User Arrival Data ***");
        System.out.println(registerArrivalSNSUserController.getArrivalSNSUser());
    }
}
