package app.core.domain.store;

import app.core.domain.model.EmployeeRole;
import app.core.domain.shared.SerializeClasses;

import java.util.List;

/**
 * Represents the employee role store.
 *
 * @author Group 40
 */
public class EmployeeRoleStore {

    /**
     * The employee role list.
     */
    private List<EmployeeRole> employeeRoleStoreList;

    /**
     * The name of the file who has all the information to keep data persistence for employees roles.
     */
    private final String FILE_WITH_ALL_DATA = "EmployeeRoles.ser";

    /**
     * Constructor to build a EmployeeRoleStore instance object.
     */
    // temporary construtor --> ignore it | TODO delete from here
    public EmployeeRoleStore() {
        //this.employeeRoleStoreList.add(new EmployeeRole("1", Constants.ROLE_NURSE));
        //this.employeeRoleStoreList.add(new EmployeeRole("2", Constants.ROLE_RECEPTIONIST));
        //this.employeeRoleStoreList.add(new EmployeeRole("3", Constants.ROLE_CENTER_COORDINATOR));
        //SerializeClasses.saveObjectIntoDisk(this.employeeRoleStoreList, this.FILE_WITH_ALL_DATA);
        this.employeeRoleStoreList = SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
    }
    // to here

    /**
     * Function that returns the list of roles an employee can have.
     *
     * @return the employee role store list.
     */
    public List<EmployeeRole> getEmployeeRoles() {
        this.employeeRoleStoreList = SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
        return this.employeeRoleStoreList;
    }

    /**
     * The role checks whether a certain role of an employee exists.
     *
     * @param role An employee role object.
     * @return true if the role exists or false if not.
     */
    public boolean checkIfRoleExists(EmployeeRole role) {
        boolean valid = false;
        int rolePosition = 0;

        while (rolePosition < this.employeeRoleStoreList.size() && !valid) {
            EmployeeRole er = this.employeeRoleStoreList.get(rolePosition);
            if (er.compareTo(role) == 0) {
                valid = true;
            }
            rolePosition++;
        }

        return valid;
    }

}
