package app.ui.console;

import app.core.controller.RegisterEmployeeController;
import app.core.domain.model.EmployeeRole;
import app.core.domain.model.VaccinationCenter;
import app.core.domain.shared.Constants;
import app.ui.console.utils.Utils;

import java.util.List;

/**
 * This class is the user interface the user will interact to register a new employee.
 *
 * @author Group 40
 */
public class RegisterEmployeeUI implements Runnable {
    @Override
    public void run() {
        RegisterEmployeeController controller = new RegisterEmployeeController();

        try {
            // The variable name holds the name of the employee.
            String name = Utils.readLineFromConsole("Insert name:");

            // The variable address holds the address of the employee.
            String address = Utils.readLineFromConsole("Insert address:");

            // The variable phoneNumber holds the phone number of the employee.
            int phoneNumber = Utils.readIntegerFromConsole("Insert phone number:");

            // The variable email holds the e-mail of the employee.
            String email = Utils.readLineFromConsole("Insert email:");

            // The variable citizenCardNumber holds the citizen card number of the employee.
            int citizenCardNumber = Utils.readIntegerFromConsole("Insert citizen card number:");

            // The variable holds the role for the new employee
            EmployeeRole employeeRole = readEmployeeRole(controller);

            controller.registerEmployee(name, address, phoneNumber, email, citizenCardNumber, employeeRole);

            if (employeeRole.getDesignation().equals(Constants.ROLE_CENTER_COORDINATOR)) {
                VaccinationCenter vaccinationCenter = readVaccinationCenter(controller);

                if (vaccinationCenter != null) {
                    controller.defineVaccinationCenterCoordinator(vaccinationCenter);
                    dataPrinting(name, address, phoneNumber, email, citizenCardNumber, employeeRole);
                    askForConfirmation(controller);

                } else {
                    System.out.println("Due to the vaccination center not being selected, it was not possible to create the employee");
                }

            } else {
                dataPrinting(name, address, phoneNumber, email, citizenCardNumber, employeeRole);
                askForConfirmation(controller);
            }

        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * This function shows all the data entered by the user.
     *
     * @param name              the variable name holds the name of the employee.
     * @param address           the variable address holds the address of the employee.
     * @param phoneNumber       the variable phoneNumber holds the phone number of the employee.
     * @param email             the variable email holds the e-mail of the employee.
     * @param citizenCardNumber the variable citizenCardNumber holds the citizen card number of the employee.
     * @param employeeRole      the variable employeeRole holds the id and designation of the role.
     */
    public void dataPrinting(String name, String address, int phoneNumber, String email, int citizenCardNumber, EmployeeRole employeeRole) {
        System.out.println("");
        System.out.println("Confirm the data:");
        System.out.println("The name: " + name);
        System.out.println("The address: " + address);
        System.out.println("The phoneNumber: " + phoneNumber);
        System.out.println("The email: " + email);
        System.out.println("The citizenCardNumber: " + citizenCardNumber);
        System.out.println("The Employee role: " + employeeRole.getDesignation());
    }

    /**
     * Function to request and read the role that this user will have
     *
     * @param controller The register employee controller
     * @return The EmployeeRole that corresponds to this new employee's role
     */
    private static EmployeeRole readEmployeeRole(RegisterEmployeeController controller) {
        System.out.println("Employees types");

        List<EmployeeRole> employeeRolesList = controller.getEmployeeRoles();

        EmployeeRole er = (EmployeeRole) Utils.showAndSelectOne(employeeRolesList, "Choose the type of employee you want to create: ");

        return er;
    }

    private static VaccinationCenter readVaccinationCenter(RegisterEmployeeController controller) {
        List<VaccinationCenter> listVC = controller.getVaccinationCenterListWithoutCenterCoordinator();
        VaccinationCenter vc = null;

        if (listVC.isEmpty()) {
            System.out.println("There are no registered vaccination centers, so it is not possible to advance.");
        } else {
            vc = (VaccinationCenter) Utils.showAndSelectOne(listVC, "Choose the vaccination center: ");
        }

        return vc;
    }

    /**
     * This function asks for confirmation, if the user aproves it, the function will save the data, if not it won't.
     *
     * @param controller represents the controller.
     */
    public void askForConfirmation(RegisterEmployeeController controller) {
        if (Utils.confirm("Is the data correct? (Y/N)")) {
            String password = controller.saveEmployee();
            if (!password.equals("")) {
                System.out.println("The employee password for login is: " + password);
                System.out.println("Operation complete");
            } else {
                System.out.println("Operation incomplete, please try again");
            }

        } else {
            System.out.println("Operation incomplete, please try again");
        }
    }
}
