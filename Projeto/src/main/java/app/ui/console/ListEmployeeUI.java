package app.ui.console;

import app.core.controller.ListEmployeeController;
import app.core.domain.model.Employee;
import app.core.domain.model.EmployeeRole;
import app.ui.console.utils.Utils;

import java.util.List;

/**
 * Represents the list of employees UI(User interface)
 *
 * @author Group 40
 */
public class ListEmployeeUI implements Runnable {

    /**
     * Main method of the UI
     */
    @Override
    public void run() {
        ListEmployeeController controller = new ListEmployeeController();

        EmployeeRole role = readEmployeeRole(controller);

        if (role != null) {
            List<Employee> employeeListByRole = controller.getEmployeeListByRole(role);
            printEmployeesInformation(employeeListByRole);
        } else {
            System.out.println("Operation canceled.");
        }
    }

    /**
     * Function to request and read the role desired by the administrator to make the list of employees by their role
     *
     * @param controller The list employee controller
     * @return The EmployeeRole that corresponds to this new employee's role
     */
    private static EmployeeRole readEmployeeRole(ListEmployeeController controller) {

        System.out.println("List employees");

        List<EmployeeRole> employeeRolesList = controller.getEmployeeRoles();

        return (EmployeeRole) Utils.showAndSelectOne(employeeRolesList, "Choose the type of employee you want to list: ");

    }

    /**
     * Function to list the employees of a given list. It also prints other information if the list is empty or null
     * (null means that the role entered does not exist, and empty if there are no employees with the inserted role).
     *
     * @param employeeListByRole Contains a list of employees to be displayed.
     */
    private static void printEmployeesInformation(List<Employee> employeeListByRole) {
        if (employeeListByRole.size() == 0) {
            System.out.println("There is no employee registered with the role inserted!");
        } else if (employeeListByRole == null) {
            System.out.println("The inserted role doesn't exists!");
        } else {
            int n = 1;
            for (Employee e : employeeListByRole) {
                System.out.println("----- " + n + " -----");
                System.out.print(e);
                System.out.println("----- -----");
                n++;
            }
        }
    }

}
