package app.core.domain.shared;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Class to read a file into a lists inside a list of Strings.
 *
 * @author Group 40
 */
public class LegacySysFileReaderType1 implements LegacySysFileReader {

    /**
     * The character that separate the data.
     */
    private final String DATA_SEPARATOR = ";";

    /**
     * The number of data fields present in each line of the file.
     */
    private final int NUMBER_OF_DATA = 8;

    /**
     * Method to read the data in the file into a lists inside a list of Strings.
     *
     * @param filePath The path to the file.
     * @return The lists inside a lists with all read data.
     */
    @Override
    public List<List<String>> readDataToStringList(String filePath) {

        List<List<String>> fileData = new ArrayList<>();

        File file = new File(filePath);

        Scanner scannerFile;
        try {
            scannerFile = new Scanner(file);
        } catch (FileNotFoundException ex) {
            throw new IllegalArgumentException("File not found.");
        }

        String header = "";

        try {
            // Read header
            header = scannerFile.nextLine();
        } catch (IllegalStateException ex) {
            scannerFile.close();
            throw new IllegalStateException(ex.getMessage());
        }

        if (!this.validHeader(header)) {
            scannerFile.close();
            throw new IllegalArgumentException("The file is invalid!");
        }

        int line = 2;

        while (scannerFile.hasNextLine()) {

            String[] lineValues = scannerFile.nextLine().split(DATA_SEPARATOR);

            if (lineValues.length == NUMBER_OF_DATA) {

                List<String> data = new ArrayList<>();
                for (int i = 0; i < NUMBER_OF_DATA; i++) {
                    data.add(lineValues[i]);
                }

                fileData.add(data);
                line++;

            } else {
                scannerFile.close();
                throw new IllegalArgumentException("The file is invalid!");
            }

        }

        scannerFile.close();

        return fileData;
    }

    /**
     * Method to check if the header is valid.
     *
     * @param header The header.
     * @return true if the header is valid, or false otherwise.
     */
    private boolean validHeader(String header) {
        return header.split(DATA_SEPARATOR).length == NUMBER_OF_DATA;
    }
}
