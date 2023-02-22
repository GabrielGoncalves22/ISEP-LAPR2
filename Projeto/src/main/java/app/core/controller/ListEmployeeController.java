package app.core.controller;

import app.core.domain.model.Company;
import app.core.domain.model.Employee;
import app.core.domain.model.EmployeeRole;
import app.core.domain.store.EmployeeRoleStore;
import app.core.domain.store.EmployeeStore;

import java.util.List;

/**
 * Represents the list of employees controller.
 *
 * @author Group 40
 */
public class ListEmployeeController {

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
     * Builds an instance of ListEmployeeController.
     */
    public ListEmployeeController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of ListEmployeeController receiving the company as parameter.
     *
     * @param company The company.
     */
    public ListEmployeeController(Company company) {
        this.company = company;
        this.employeeStore = this.company.getEmployeeStore();
        this.employeeRoleStore = this.company.getEmployeeRolesStore();
        this.employee = null;
        this.employeeRole = null;
    }

    /**
     * Function that returns all roles an employee can have.
     *
     * @return The list of existing employee roles.
     */
    public List<EmployeeRole> getEmployeeRoles() {
        this.employeeRoleStore = this.company.getEmployeeRolesStore();
        return this.employeeRoleStore.getEmployeeRoles();
    }

    /**
     * Function that returns the list of employees with a given role. Also returns null if the requested role does not
     * exist.
     *
     * @param role An employee role object.
     * @return A list of employees with the asked role (if it exists), or null if the role doesn't exist.
     */
    public List<Employee> getEmployeeListByRole(EmployeeRole role) {

        boolean valid = this.employeeRoleStore.checkIfRoleExists(role);

        if (valid) {
            this.employeeStore = this.company.getEmployeeStore();
            return this.employeeStore.getEmployeesByRole(role);
        }

        return null;
    }

}
