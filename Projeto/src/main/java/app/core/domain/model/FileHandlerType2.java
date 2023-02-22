package app.core.domain.model;

import app.core.dto.SNSUserDTO;
import app.core.domain.shared.FileHandler;
import app.core.domain.shared.exception.InvalidLineInFileException;
import app.ui.console.utils.FileUtils;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class handle files with type 2 (comma separation and csv type)
 *
 * @author Group 40
 */
public class FileHandlerType2 implements FileHandler {

    /**
     * The variable file holds the file object.
     */
    private File file;

    /**
     * The variable SUPPORTED_EXTENSIONS holds the supported extensions for files handled in this class.
     */
    private final String[] SUPPORTED_EXTENSIONS = {"csv"};

    /**
     * The variable DATA_SEPARATOR_SNSUSER holds the data separator that should be present in file.
     */
    private final String DATA_SEPARATOR_SNSUSER = ",";

    /**
     * Constructor which creates an instance receiving the path to the file as parameter.
     *
     * @param filePath The path to the file.
     */
    public FileHandlerType2(String filePath) {
        File f = new File(filePath);
        String message = FileUtils.validateFile(f, this.SUPPORTED_EXTENSIONS);
        if (message.equals("")) {
            this.file = f;
        } else {
            throw new IllegalArgumentException("The file doesn't exists");
        }
    }

    /**
     * Read the data present in the file and transform it in a list of objects of type SNSUserDTO.
     *
     * @return A list of object of type SNSUserDTO.
     * @throws FileNotFoundException If file isn't found in system.
     */
    @Override
    public List<SNSUserDTO> readDataToSNSUserDTO() throws FileNotFoundException {
        List<SNSUserDTO> lSUDto = new ArrayList<SNSUserDTO>();

        Scanner scannerFile = new Scanner(new FileInputStream(this.file), StandardCharsets.UTF_8);
        int line = 1;

        while (scannerFile.hasNextLine()) {

            String[] lineValues = scannerFile.nextLine().split(DATA_SEPARATOR_SNSUSER);

            if (lineValues.length == FileHandler.NUMBER_DATA_SNSUSER) {

                Date birthDate;
                Email email;

                String name = lineValues[0];
                String sex = lineValues[1];

                birthDate = Date.convertStringToDate(lineValues[2]);
                if (birthDate == null || birthDate.isMajor(Date.currentDate())) {
                    scannerFile.close();
                    throw new InvalidLineInFileException(line, "Date");
                }

                String address = lineValues[3];
                String phoneNumber = lineValues[4];

                try {
                    email = new Email(lineValues[5]);
                } catch (IllegalArgumentException ex) {
                    scannerFile.close();
                    throw new InvalidLineInFileException(line, "Email");
                }

                String snsUserNumber = lineValues[6];
                String citizenCardNumber = lineValues[7];

                SNSUserDTO snsUserDTO = new SNSUserDTO(name, address, sex, phoneNumber, email, birthDate, snsUserNumber, citizenCardNumber);
                lSUDto.add(snsUserDTO);

                line++;

            } else {
                scannerFile.close();
                throw new IllegalArgumentException("The file is invalid!");
            }

        }

        scannerFile.close();

        return lSUDto;
    }
}
