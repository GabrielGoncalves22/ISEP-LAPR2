package app.core.domain.shared;

import app.core.dto.SNSUserDTO;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Interface that represents the file handler to import SNS Users into the system.
 *
 * @author Group 40
 */
public interface FileHandler {
    /**
     * The amount of different data that is expected to have in each line of the file.
     */
    int NUMBER_DATA_SNSUSER = 8;

    /**
     * Read the data present in the file and transform it in a list of objects of type SNSUserDTO.
     *
     * @return A list of object of type SNSUserDTO.
     * @throws FileNotFoundException If file isn't found in system.
     */
    List<SNSUserDTO> readDataToSNSUserDTO() throws FileNotFoundException;
}
