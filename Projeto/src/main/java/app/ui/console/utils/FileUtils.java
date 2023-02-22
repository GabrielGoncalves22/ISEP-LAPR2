package app.ui.console.utils;

import java.io.File;

/**
 * This class has useful methods to handle some file peculiarities
 *
 * @author Group 40
 */
public class FileUtils {

    /**
     * This method validate a file (check if it exists and if has the desired extension)
     *
     * @param file                The file to be validated
     * @param supportedExtensions The extensions the file can have
     * @return A String message with some error or empty if the file was validated successfully
     */
    public static String validateFile(File file, String[] supportedExtensions) {
        String message = "";

        if (!FileUtils.fileExists(file)) {
            message = "The file doesn't exists!";
        } else if (!FileUtils.hasSupportedExtension(file, supportedExtensions)) {
            message = "The extension of the file isn't supported!";
        }

        return message;
    }

    /**
     * Check if the given file exists
     *
     * @param file The file to check if exists
     * @return true if file exists or false otherwise
     */
    public static boolean fileExists(File file) {
        return file.exists();
    }

    /**
     * Function to check if a certain fil has a desired extension
     *
     * @param file                The file to check the extension
     * @param supportedExtensions An array with the supported extensions
     * @return true if the extension is supported or false otherwise
     */
    public static boolean hasSupportedExtension(File file, String[] supportedExtensions) {
        boolean validExtension = false;
        String filePath = file.getPath();
        int extensionIndex = 0;

        while (extensionIndex < supportedExtensions.length && !validExtension) {
            String extension = supportedExtensions[extensionIndex];
            if (filePath.length() > extension.length()) {
                String fileExtension = filePath.substring(filePath.length() - extension.length());
                if (extension.equalsIgnoreCase(fileExtension)) {
                    validExtension = true;
                }
            }
            extensionIndex++;
        }

        return validExtension;

    }

}
