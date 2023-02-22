package domain.store;

import app.core.domain.model.EmployeeRole;
import app.core.domain.store.EmployeeRoleStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to test the methods of the EmployeeRoleStore class
 *
 * @author Group 40
 */
public class EmployeeRoleStoreTests {

    /**
     * This test will check if a role that is not registered in the EmployeeRoleStore exists
     */
    @Test
    public void checkIfRoleExistsInTheListTest1() {
        EmployeeRoleStore employeeRoleStore = new EmployeeRoleStore();
        EmployeeRole employeeRole = new EmployeeRole("5", "New Role");
        boolean expectedResult = false;
        boolean result = employeeRoleStore.checkIfRoleExists(employeeRole);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * This test will check if a role that is registered in the EmployeeRoleStore exists
     */
    @Test
    public void checkIfRoleExistsInTheListTest2() {
        EmployeeRoleStore employeeRoleStore = new EmployeeRoleStore();
        EmployeeRole employeeRole = new EmployeeRole("1", "Nurse");
        boolean expectedResult = true;
        boolean result = employeeRoleStore.checkIfRoleExists(employeeRole);
        Assert.assertEquals(expectedResult, result);
    }

    /**
     * This test will check if the total number of all employee roles exists in the system
     */
    @Test
    public void getEmployeeRolesTest1() {
        List<EmployeeRole> employeeRoleList = new ArrayList<EmployeeRole>();
        employeeRoleList.add(new EmployeeRole("1", "Nurse"));
        employeeRoleList.add(new EmployeeRole("2", "Receptionist"));
        employeeRoleList.add(new EmployeeRole("3", "Center Coordinator"));
        EmployeeRoleStore employeeRoleStore = new EmployeeRoleStore();

        int expectedResult = employeeRoleList.size();
        int result = employeeRoleStore.getEmployeeRoles().size();

        Assert.assertEquals(expectedResult, result);
    }

    /**
     * This test will check if the right employee roles exists in the system
     */
    @Test
    public void getEmployeeRolesTest2() {
        List<EmployeeRole> employeeRoleList = new ArrayList<EmployeeRole>();
        employeeRoleList.add(new EmployeeRole("1", "Nurse"));
        employeeRoleList.add(new EmployeeRole("2", "Receptionist"));
        employeeRoleList.add(new EmployeeRole("3", "Center Coordinator"));
        EmployeeRoleStore employeeRoleStore = new EmployeeRoleStore();
        List<EmployeeRole> employeeRoleStoreList = employeeRoleStore.getEmployeeRoles();

        int n = 0;
        int expectedResult = 0;

        while (n < employeeRoleList.size()) {
            int result = employeeRoleList.get(n).compareTo(employeeRoleStoreList.get(n));
            Assert.assertEquals(expectedResult, result);
            n++;
        }

    }
}
