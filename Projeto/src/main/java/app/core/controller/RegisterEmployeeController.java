package app.core.controller;

import app.core.domain.model.Company;
import app.core.domain.model.Employee;
import app.core.domain.model.EmployeeRole;
import app.core.domain.model.VaccinationCenter;
import app.core.domain.store.EmployeeRoleStore;
import app.core.domain.store.EmployeeStore;
import app.core.domain.store.VaccinationCenterStore;

import java.util.List;

/**
 * Represents the controller of the register employee functionality.
 *
 * @author Group 40
 */
public class RegisterEmployeeController {

    /**
     * Represents the company.
     */
    private Company company;

    /**
     * Represents the employee store.
     */
    private EmployeeStore employeeStore;

    /**
     * Represents the employee role store.
     */
    private EmployeeRoleStore employeeRoleStore;

    /**
     * Represents the employee.
     */
    private Employee employee;

    /**
     * Represents the employee role.
     */
    private EmployeeRole employeeRole;

    /**
     * Represents the vaccination center store.
     */
    private VaccinationCenterStore vaccinationCenterStore;

    /**
     * Builds an instance of RegisterEmployeeController.
     */
    public RegisterEmployeeController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of RegisterEmployeeController receiving the company as parameter.
     *
     * @param company represents the company.
     */
    public RegisterEmployeeController(Company company) {
        this.company = company;
        this.employeeStore = this.company.getEmployeeStore();
        this.employeeRoleStore = this.company.getEmployeeRolesStore();
        this.employee = null;
        this.employeeRole = null;
    }

    /**
     * This function finds all roles an employee can have.
     *
     * @return all the roles an employee can have.
     */
    public List<EmployeeRole> getEmployeeRoles() {
        this.employeeRoleStore = this.company.getEmployeeRolesStore();
        return this.employeeRoleStore.getEmployeeRoles();
    }

    /**
     * This function returns an employee if it passes the validation process.
     *
     * @param name              the variable name holds the name of the employee.
     * @param address           the variable address holds the address of the employee.
     * @param phoneNumber       the variable phoneNumber holds the phone number of the employee.
     * @param citizenCardNumber the variable citizenCardNumber holds the citizen card number of the employee.
     * @param role              the variable role holds the id and designation of the role.
     */
    public boolean registerEmployee(String name, String address, int phoneNumber, String email, int citizenCardNumber, EmployeeRole role) {
        this.employee = this.employeeStore.registerEmployee(name, address, phoneNumber, email, citizenCardNumber, role);
        return this.employeeStore.validateEmployee(employee);
    }

    /**
     * This function calls the saveEmployee from the employeeStore.
     *
     * @return The password if employee is successfully registered.
     */
    public String saveEmployee() {

        String password = "";

        if (this.employeeStore.saveEmployee(this.employee)) {
            password = this.company.getUserStore().addNewUser(this.employee.getName(), this.employee.getEmail().getEmail(), this.employee.getRoleDesignation());
            this.employee = this.employeeStore.saveEmployeeUser(employee, password);
            boolean success = this.employeeStore.saveEmployeeListInFile();
        }

        return password;
    }

    /**
     * Returns the vaccination center list without center coordinator.
     *
     * @return the vaccination center list without center coordinator.
     */
    public List<VaccinationCenter> getVaccinationCenterListWithoutCenterCoordinator() {
        this.vaccinationCenterStore = this.company.getVaccinationCenterStore();
        return vaccinationCenterStore.getVaccinationCenterListWithoutCenterCoordinator();
    }

    /**
     * Method to define the vaccination center coordinator to store
     *
     * @param vc the vaccination center.
     */
    public void defineVaccinationCenterCoordinator(VaccinationCenter vc) {
        this.vaccinationCenterStore = this.company.getVaccinationCenterStore();
        this.vaccinationCenterStore.defineVaccinationCenterCoordinator(this.employee.getId(), vc);
    }
}
