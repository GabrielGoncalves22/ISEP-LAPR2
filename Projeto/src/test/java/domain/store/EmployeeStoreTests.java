package domain.store;

import app.core.domain.model.Employee;
import app.core.domain.model.EmployeeRole;
import app.core.domain.store.EmployeeStore;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to test the methods of the EmployeeStore class
 *
 * @author Group 40
 */
public class EmployeeStoreTests {
    private static EmployeeStore employeeStore = new EmployeeStore();

    EmployeeRole testRole1 = new EmployeeRole("2", "Receptionist");
    EmployeeRole testRole2 = new EmployeeRole("3", "Center Coordinator");

    /**
     * Tests the creation of multiple instances.
     */
    @Test
    public void testRegisterEmployee() {

        Employee employee1 = employeeStore.registerEmployee("XXXXXXXX XXXXXXXX XSilva", "XXXXX XXXX X", 123456789,
                "XXXXX@gmail.com", 987654321, testRole1);
        Employee employee2 = employeeStore.registerEmployee("YYYYYYYYY YYYYYYY YAntunes", "YYYYY YYYY Y", 123123123,
                "YYYYY@gmail.com", 987987987, testRole1);
        Employee employee3 = employeeStore.registerEmployee("ZZZZZZZZZ ZZZZZZZ ZBogalho", "ZZZZZ ZZZZ Z", 132547698,
                "ZZZZZ@gmail.com", 978563412, testRole2);

        Employee expectedResult1 = new Employee("XXXXXXXX XXXXXXXX XSilva", "XXXXX XXXX X", 123456789,
                "XXXXX@gmail.com", 987654321, testRole1);
        Employee expectedResult2 = new Employee("YYYYYYYYY YYYYYYY YAntunes", "YYYYY YYYY Y", 123123123,
                "YYYYY@gmail.com", 987987987, testRole1);
        Employee expectedResult3 = new Employee("ZZZZZZZZZ ZZZZZZZ ZBogalho", "ZZZZZ ZZZZ Z", 132547698,
                "ZZZZZ@gmail.com", 978563412, testRole2);

        Assert.assertEquals(expectedResult1, employee1);
        Assert.assertEquals(expectedResult2, employee2);
        Assert.assertEquals(expectedResult3, employee3);
    }

    /**
     * Tests the validation of multiple instances.
     */
    @Test
    public void testValidateEmployee() {
        boolean validateTest1 = employeeStore.validateEmployee(null);
        boolean validateTest2 = employeeStore.validateEmployee(new Employee("YYYYYYYYY Y Antunes", "YYYYY YYYY Y", 123123123,
                "YYYYY@gmail.com", 987987987, testRole1));
        boolean validateTest3 = employeeStore.validateEmployee(new Employee("ZZZZZZZZZ Z Bogalho", "ZZZZZ ZZZZ Z", 132547698,
                "ZZZZZ@gmail.com", 978563412, testRole2));

        Assert.assertFalse(validateTest1);
        Assert.assertTrue(validateTest2);
        Assert.assertTrue(validateTest3);
    }

    /**
     * Tests the saving of multiple instances.
     */
    @Test
    public void testSaveEmployee() {
        boolean saveTest1 = employeeStore.saveEmployee(null);
        boolean saveTest2 = employeeStore.saveEmployee(new Employee("XXXXXXXX XXXXXXXX Silva", "XXXXX XXXX X", 123456789,
                "XXXXX@gmail.com", 987654321, testRole1));
        boolean saveTest3 = employeeStore.saveEmployee(new Employee("XXXXXXXX XXXXXXXX Oliveira", "XXXXX XXXX X", 987654321,
                "oliveira@gmail.com", 982652222, testRole1));
        boolean saveTest4 = employeeStore.saveEmployee(new Employee("XXXXXXXX XXXXXXXX Oliveira", "XXXXX XXXX X", 987654321,
                "oliveira@gmail.com", 982652222, testRole1));

        Assert.assertFalse(saveTest1);
        Assert.assertTrue(saveTest2);
        Assert.assertTrue(saveTest3);
        Assert.assertFalse(saveTest4);
    }

    /**
     * Test the method that builds a list of employees that contain a certain role
     */
    @Test
    public void getEmployeesByRoleTest() {
        Employee employee = new Employee("XXXXXXXX XXXXXXXX Silva", "XXXXX XXXX X", 123456789,
                "XXXXX@gmail.com", 987654321, testRole1);
        Employee employee1 = new Employee("XXXXXXXX XXXXXXXX Oliveira", "XXXXX XXXX X", 987654321,
                "oliveira@gmail.com", 982652222, testRole1);

        List<Employee> expectedEmployeeList = new ArrayList<Employee>();
        expectedEmployeeList.add(employee);
        expectedEmployeeList.add(employee1);

        List<Employee> resultEmployeeList = employeeStore.getEmployeesByRole(testRole1);

        int n = 0;
        while (n < expectedEmployeeList.size()) {
            boolean result = expectedEmployeeList.get(n).equals(resultEmployeeList.get(n));
            Assert.assertTrue(result);
            n++;
        }
    }

}
