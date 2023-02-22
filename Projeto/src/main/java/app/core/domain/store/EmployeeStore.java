package app.core.domain.store;

import app.core.domain.model.Employee;
import app.core.domain.model.EmployeeRole;
import app.core.domain.shared.SerializeClasses;
import pt.isep.lei.esoft.auth.domain.model.Password;
import pt.isep.lei.esoft.auth.domain.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents were the employees are stored.
 *
 * @author Group 40
 */
public class EmployeeStore {

    /**
     * The list containing the employees registered in the system.
     */
    private List<Employee> employeesList;

    /**
     * The name of the file who has all the information to keep data persistence for employees.
     */
    private final String FILE_WITH_ALL_DATA = "Employee.ser";

    /**
     * Constructor to build a EmployeeStore instance object.
     */
    public EmployeeStore() {
        this.employeesList = this.loadEmployeeListFromFile();
    }

    /**
     * Method to get all the employees with a certain role.
     *
     * @param role The role that the employee should have to be returned.
     * @return The employees with the given role.
     */
    public List<Employee> getEmployeesByRole(EmployeeRole role) {

        //this.employeesList = this.loadEmployeesListFromFile();

        List<Employee> employeeListByRole = new ArrayList<Employee>();

        for (Employee e : this.employeesList) {
            if (e.compareRoleWithOtherEmployeeRole(role) == 0) {
                employeeListByRole.add(e);
            }
        }

        return employeeListByRole;
    }

    /**
     * This function creates a new instance of an employee.
     *
     * @param name              the variable name holds the name of the employee.
     * @param address           the variable address holds the address of the employee.
     * @param phoneNumber       the variable phoneNumber holds the phone number of the employee.
     * @param citizenCardNumber the variable citizenCardNumber holds the citizen card number of the employee.
     * @param role              the variable role holds the id and designation of the role.
     * @return a new instance of an employee.
     */
    public Employee registerEmployee(String name, String address, int phoneNumber, String email, int citizenCardNumber, EmployeeRole role) {
        return new Employee(name, address, phoneNumber, email, citizenCardNumber, role);
    }

    /**
     * This function adds the employee to the list if it is validated.
     *
     * @param employee represents an employee through name, address, phoneNumber, email, citizenCardNumber and role.
     * @return false if the validation process is not passed, if it passes the process it will call the function
     * addEmployee and pass employee as parameter.
     */
    public boolean saveEmployee(Employee employee) {
        if (!validateEmployee(employee)) {
            return false;
        } else {
            return addEmployee(employee);
        }
    }

    /**
     * Method to link the user information to a certain employee.
     *
     * @param employee The employee.
     * @param password The password for the employee user.
     * @return The employee with the user information added.
     */
    public Employee saveEmployeeUser(Employee employee, String password) {
        employee.setUser(new User(employee.getEmail(), new Password(password), employee.getName()));
        return employee;
    }

    /**
     * Method to save all the employees registered in the system into the file to keep data persistence.
     *
     * @return true if the operation is successfully completed or false otherwise.
     */
    public boolean saveEmployeeListInFile() {
        return SerializeClasses.saveObjectIntoDisk(this.employeesList, this.FILE_WITH_ALL_DATA);
    }

    /**
     * Method to load into memory all the employees registered in the system.
     *
     * @return The list with all the employees.
     */
    private List<Employee> loadEmployeeListFromFile() {
        return SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
    }

    /**
     * This function validates the employee if it's not null.
     *
     * @param employee represents an employee through name, address, phoneNumber, email, citizenCardNumber and role.
     * @return false if the instance employee is empty, if not it will return !this.employeesList.contains(employee).
     */
    public boolean validateEmployee(Employee employee) {
        if (employee == null) {
            return false;
        } else {
            //this.employeesList = this.loadEmployeesListFromFile();
            return !this.employeesList.contains(employee);
        }
    }

    /**
     * Method to find an employee with a certain email.
     *
     * @param email The email to be searched through the employees.
     * @return The employee with the given email, or null if it doesn't exist.
     */
    public Employee findEmployeeByEmail(String email) {

        Employee employee = null;
        int i = 0;
        boolean found = false;

        while (i < this.employeesList.size() && !found) {
            Employee e = this.employeesList.get(i);
            if (e.getEmail().getEmail().equals(email)) {
                employee = e;
                found = true;
            }
            i++;
        }

        return employee;

    }

    /**
     * This function will add an employee to the employeeList.
     *
     * @param employee represents an employee through name, address, phoneNumber, email, citizenCardNumber and role.
     * @return the call of a function that add, the instance of an employee, to the employeeList.
     */
    private boolean addEmployee(Employee employee) {
        return this.employeesList.add(employee);
    }

}
