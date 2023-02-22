package domain.model;

import app.core.domain.model.EmployeeRole;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class to test the methods of the EmployeeRole class
 *
 * @author Group 40
 */
public class EmployeeRoleTest {

    /**
     * This method performs the comparison test between two employee roles that are equal
     */
    @Test
    public void compareEmployeeRoleTest1() {

        EmployeeRole employeeRole1 = new EmployeeRole("1", "Nurse");
        EmployeeRole employeeRole2 = new EmployeeRole("1", "Nurse");
        int expectedResult = 0;
        int result = employeeRole1.compareTo(employeeRole2);

        Assert.assertEquals(expectedResult, result);

    }

    /**
     * This method performs the comparison test between two employee roles that are different
     */
    @Test
    public void compareEmployeeRoleTest2() {

        EmployeeRole employeeRole1 = new EmployeeRole("1", "Nurse");
        EmployeeRole employeeRole2 = new EmployeeRole("5", "Center Coordinator");
        int expectedResult = 0;
        int result = employeeRole1.compareTo(employeeRole2);

        Assert.assertNotEquals(expectedResult, result);

    }

}
