package app.core.domain.shared;

import java.util.List;

/**
 * Interface that implements a method to read data from a legacy system file.
 *
 * @author Group 40
 */
public interface LegacySysFileReader {
    /**
     * Method that read the data from the file and store it in a sublist inside a list.
     *
     * @return A List with sublists that contain the information of each line of the file
     */
    List<List<String>> readDataToStringList(String filePath);
}
