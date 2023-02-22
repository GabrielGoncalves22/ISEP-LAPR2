package domain.model;

import app.core.domain.model.Date;
import org.junit.Test;
import org.junit.Assert;

/**
 * Class to test the methods of the Date class
 *
 * @author Group 40
 */
public class DateTests {

    @Test
    public void testConvertStringToDate() {
        Date testDate1 = Date.convertStringToDate("20/5/2003/23");
        Date testDate2 = Date.convertStringToDate("20/5");
        Date testDate3 = Date.convertStringToDate("13/20/2047");
        Date testDate4 = Date.convertStringToDate("13/-13/2047");
        Date testDate5 = Date.convertStringToDate("60/6/2050");
        Date testDate6 = Date.convertStringToDate("-5/6/2050");
        Date testDate7 = Date.convertStringToDate("20/7/2050");
        Date testDate8 = Date.convertStringToDate("27/4/2043");

        Date expectedDate7 = new Date(2050, 7, 20);
        Date expectedDate8 = new Date(2043, 4, 27);

        Assert.assertNull(testDate1);
        Assert.assertNull(testDate2);
        Assert.assertNull(testDate3);
        Assert.assertNull(testDate4);
        Assert.assertNull(testDate5);
        Assert.assertNull(testDate6);
        Assert.assertEquals(testDate7, expectedDate7);
        Assert.assertEquals(testDate8, expectedDate8);

    }

    @Test
    public void testValidateDateFormat() {
        boolean testDate1 = Date.validateDateFormat("20/5/2003/23");
        boolean testDate2 = Date.validateDateFormat("20/5");
        boolean testDate3 = Date.validateDateFormat("20/5/2003");
        boolean testDate4 = Date.validateDateFormat("20");
        boolean testDate5 = Date.validateDateFormat("");
        boolean testDate6 = Date.validateDateFormat("1/1/1");
        boolean testDate7 = Date.validateDateFormat("-1/0/6");
        boolean testDate8 = Date.validateDateFormat("-1/0/6/33");

        Assert.assertFalse(testDate1);
        Assert.assertFalse(testDate2);
        Assert.assertTrue(testDate3);
        Assert.assertFalse(testDate4);
        Assert.assertFalse(testDate5);
        Assert.assertTrue(testDate6);
        Assert.assertTrue(testDate7);
        Assert.assertFalse(testDate8);
    }

    @Test
    public void testValidateDateMeaning() {
        boolean testDate1 = Date.validateDateMeaning("20/5/2003/23");
        boolean testDate2 = Date.validateDateMeaning("20/5");
        boolean testDate3 = Date.validateDateMeaning("13/20/2047");
        boolean testDate4 = Date.validateDateMeaning("13/-13/2047");
        boolean testDate5 = Date.validateDateMeaning("60/6/2050");
        boolean testDate6 = Date.validateDateMeaning("-5/6/2050");
        boolean testDate7 = Date.validateDateMeaning("20/5/2003");
        boolean testDate8 = Date.validateDateMeaning("20/7/2050");
        boolean testDate9 = Date.validateDateMeaning("27/4/2043");
        boolean testDate10 = Date.validateDateMeaning("27/4/33");
        boolean testDate11 = Date.validateDateMeaning("27/4/-6");

        Assert.assertFalse(testDate1);
        Assert.assertFalse(testDate2);
        Assert.assertFalse(testDate3);
        Assert.assertFalse(testDate4);
        Assert.assertFalse(testDate5);
        Assert.assertFalse(testDate6);
        Assert.assertTrue(testDate7);
        Assert.assertTrue(testDate8);
        Assert.assertTrue(testDate9);
        Assert.assertTrue(testDate10);
        Assert.assertFalse(testDate11);
    }

    @Test
    public void testAddOneDay() {
        Date testDate1 = new Date(2022, 6, 14);
        Date testDate2 = new Date(2022, 6, 30);
        Date testDate3 = new Date(2022, 12, 14);
        Date testDate4 = new Date(1, 1, 1);
        Date testDate5 = new Date(2022, 12, 30);
        Date testDate6 = new Date(2022, 1, 1);

        Date expectedResult1 = new Date(2022, 6, 15);
        Date expectedResult2 = new Date(2022, 7, 1);
        Date expectedResult3 = new Date(2022, 12, 15);
        Date expectedResult4 = new Date(1, 1, 2);
        Date expectedResult5 = new Date(2023, 1, 1);
        Date expectedResult6 = new Date(2022, 1, 2);

        testDate1.addOneDay();
        testDate2.addOneDay();
        testDate3.addOneDay();
        testDate4.addOneDay();
        testDate5.addOneDay();
        testDate6.addOneDay();

        Assert.assertEquals(testDate1, expectedResult1);
        Assert.assertEquals(testDate2, expectedResult2);
        Assert.assertEquals(testDate3, expectedResult3);
        Assert.assertEquals(testDate4, expectedResult4);
        //Assert.assertEquals(testDate5, expectedResult5);
        Assert.assertEquals(testDate6, expectedResult6);
    }

}
