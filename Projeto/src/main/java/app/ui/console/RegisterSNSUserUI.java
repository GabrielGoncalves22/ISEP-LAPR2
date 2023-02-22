package app.ui.console;

import app.core.controller.RegisterSNSUserController;
import app.core.domain.model.Date;
import app.core.domain.shared.SNSUserSex;
import app.ui.console.utils.Utils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents the SNS User UI(User interface).
 *
 * @author Group 40
 */
public class RegisterSNSUserUI implements Runnable {
    /**
     * The SNS User controller.
     */
    private static RegisterSNSUserController registerSNSUserController = new RegisterSNSUserController();


    @Override
    public void run() {
        System.out.println("\n*** New SNS User ***");
        String name = Utils.readLineFromConsole("Insert name: ");
        String address = Utils.readLineFromConsole("Insert address: ");

        int sexOrder = Utils.showAndSelectIndex(new ArrayList<>(Arrays.asList(SNSUserSex.values())), "Select sex: ");

        String birthDate = Utils.readLineFromConsole("Insert birth date: ");
        String phoneNumber = Utils.readLineFromConsole("Insert phone number: ");
        String email = Utils.readLineFromConsole("Insert email: ");
        String snsUserNumber = Utils.readLineFromConsole("Insert SNS user number: ");
        String citizenCardNumber = Utils.readLineFromConsole("Insert citizen card number: ");

        try {
            registerSNSUserController.createSNSUser(name, address, SNSUserSex.sexDesignation(sexOrder), phoneNumber, new Email(email), Date.convertStringToDate(birthDate), snsUserNumber, citizenCardNumber);

            showData();
            confirmData();
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
            System.out.println("The new SNS User was not created!");
        }
    }

    /**
     * Method to request confirmation of data.
     */
    private void confirmData() {
        if (Utils.confirm("Want to confirm the data? (y/n)")) {
            if (registerSNSUserController.saveSNSUser()) {
                System.out.println("New SNS User successfully ads!");
            } else {
                System.out.println("Could not create new SNS User!");
            }
        } else {
            System.out.println("The new SNS User was not created!");
        }
    }

    /**
     * Method to show the data of the new SNS User.
     */
    private void showData() {
        System.out.println("*** New SNS User Data ***");
        System.out.println(registerSNSUserController.getSNSUser());
    }
}
