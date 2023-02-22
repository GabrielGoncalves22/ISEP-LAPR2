package domain.model;

import app.core.domain.model.Employee;
import app.core.domain.model.EmployeeRole;
import org.junit.Assert;
import org.junit.Test;

/**
 * Class to test the methods of the Employee class
 *
 * @author Group 40
 */
public class EmployeeTest {

    EmployeeRole testRole = new EmployeeRole("1", "Nurse");

    /**
     * Tests the checkName function.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckName() {
        Employee employee = new Employee(null, "XXXXXX XXX X", 123456789, "XXXXXXXX@gmail.com",
                987654321, testRole);
    }

    /**
     * Tests the checkAddress function.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckAddress() {
        Employee employee = new Employee("XXXXXX XXXXXXXX Silva", null, 123456789, "XXXXXXX@gmail.com",
                987654321, testRole);
    }

    /**
     * Tests the checkPhoneNumber function.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckPhoneNumber() {
        Employee employee = new Employee("XXXXXX XXXXXXXX Silva", "XXXXXX XXX X", 123456, "XXXXXXX@gmail.com",
                987654321, testRole);
    }

    /**
     * Tests the checkCitizenCardNumber function.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckCitizenCardNumber() {
        Employee employee = new Employee("XXXXXX XXXXXXXX Silva", "XXXXXX XXX X", 123456789, "XXXXXXX@gmail.com",
                987654, testRole);
    }

    /**
     * Tests the checkRole function.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCheckRole() {
        Employee employee = new Employee("XXXXXX XXXXXXXX Silva", "XXXXXX XXX X", 123456789, "XXXXXXX@gmail.com",
                987654321, null);
    }

    /**
     * Tests the creation of an instance without "@gmail.com" on the email.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmailWithoutSecondPart() {
        Employee employee = new Employee("XXXXXX XXXXXXXX Silva", "XXXXXX XXX X", 123456789, "XXXXXXX",
                987654321, testRole);
    }

    /**
     * Tests the creation of an instance without ".com" on the email.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmailWithoutFinalPart() {
        Employee employee = new Employee("XXXXXX XXXXXXXX Silva", "XXXXXX XXX X", 123456789, "XXXXXXX@gmail",
                987654321, testRole);
    }

    /**
     * Tests the creation of an instance with the email null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testEmailNull() {
        Employee employee = new Employee("XXXXXX XXXXXXXX Silva", "XXXXXX XXX X", 123456789, null,
                987654321, testRole);
    }

    /**
     * Tests the creation of an instance with the password null
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPassword() {
        Employee employee = new Employee("XXXXXX XXXXXXXX Silva", "XXXXXX XXX X", 123456789, "XXXXXXX@gmail.com",
                987654, testRole);
    }

    /**
     * Tests the creation of an instance with every string null and every integer 0.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAllParametersNull() {
        Employee employee = new Employee(null, null, 0, null,
                0, null);
    }

    /**
     * This method tests whether an employee's role is the same as another
     */
    @Test
    public void compareRoleWithOtherEmployeeRoleTest() {
        EmployeeRole employeeRole = new EmployeeRole("1", "Nurse");
        int expectedResult = 0;
        int result = employeeRole.compareTo(this.testRole);

        Assert.assertEquals(expectedResult, result);

        EmployeeRole employeeRole1 = new EmployeeRole("2", "Receptionist");
        result = employeeRole1.compareTo(employeeRole);
        Assert.assertNotEquals(expectedResult, result);
    }

}
