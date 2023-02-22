package app.ui.console;

import app.core.controller.ListArrivalSNSUserController;
import app.core.dto.ArrivalSNSUserDTO;

import java.util.List;

/**
 * Represents the list of Arrival SNS User UI(User interface).
 *
 * @author Group 40
 */
public class ListArrivalSNSUserUI implements Runnable {
    ListArrivalSNSUserController controller;

    public ListArrivalSNSUserUI(int vcId) {
        controller = new ListArrivalSNSUserController(vcId);
    }

    /**
     * Main method of the UI
     */
    @Override
    public void run() {
        printSNSUserInformation(controller.getListArrivalSNSUser());
    }

    /**
     * Function to list the arrival SNS User of a given list. It also prints other information if the list is empty or null
     * (null means that the role entered does not exist, and empty if there are no employees in the entered vaccine center).
     *
     * @param listArrivalSNSUserDto Contains a list of SNS User to be displayed.
     */
    private static void printSNSUserInformation(List<ArrivalSNSUserDTO> listArrivalSNSUserDto) {
        if (listArrivalSNSUserDto.isEmpty()) {
            System.out.println("There is no one on the waiting list for this vaccination center !");
        } else {
            System.out.println("|                                LIST OF USERS                                 |");
            int n = 1;
            for (ArrivalSNSUserDTO e : listArrivalSNSUserDto) {
                System.out.printf("%s %04d %s%n", "|--------------------------------- User ", n, "---------------------------------|");
                System.out.print(e.toString());
                System.out.println("|______________________________________________________________________________|");
                n++;
            }
        }
    }

}