package domain.model;

import app.ui.console.utils.FileUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

/**
 * Represents the tests of the FileUtils class.
 */
public class FileUtilsTests {

    /**
     * This test will test the function of validate a file (check if it exists and if has the desired extension)
     */
    @Test
    public void testValidateFile() {
        final String[] SUPPORTED_EXTENSIONS = {"csv"};
        String test1 = FileUtils.validateFile(new File("src/files/a.csv"), SUPPORTED_EXTENSIONS);
        String test2 = FileUtils.validateFile(new File("src/files/b.csv"), SUPPORTED_EXTENSIONS);
        String test3 = FileUtils.validateFile(new File("src/files/c.txt"), SUPPORTED_EXTENSIONS);
        String mensage1 = "The file doesn't exists!";
        String mensage2 = "The extension of the file isn't supported!";

        Assert.assertEquals(mensage1, test1);
        Assert.assertEquals(mensage1, test2);
        Assert.assertEquals(mensage2, test3);

    }

    /**
     * This test will test the function of Check if the given file exists
     */
    @Test
    public void testFileExists() {
        boolean test1 = FileUtils.fileExists(new File("src/files/a.csv"));
        boolean test2 = FileUtils.fileExists(new File("src/files/b.csv"));
        boolean test3 = FileUtils.fileExists(new File("src/files/c.txt"));
        boolean test4 = FileUtils.fileExists(new File("src/files/XXXXX.txt"));

        Assert.assertTrue(test1);
        Assert.assertTrue(test2);
        Assert.assertTrue(test3);
        Assert.assertFalse(test4);

    }


    /**
     * This test will test the function to check if a certain fil has a desired extension
     */
    @Test
    public void testHasSupportedExtension() {
        final String[] SUPPORTED_EXTENSIONS = {"csv"};

        boolean test1 = FileUtils.hasSupportedExtension(new File("src/files/a.csv"), SUPPORTED_EXTENSIONS);
        boolean test2 = FileUtils.hasSupportedExtension(new File("src/files/b.csv"), SUPPORTED_EXTENSIONS);
        boolean test3 = FileUtils.hasSupportedExtension(new File("src/files/c.txt"), SUPPORTED_EXTENSIONS);

        Assert.assertTrue(test1);
        Assert.assertTrue(test2);
        Assert.assertFalse(test3);
    }

}
