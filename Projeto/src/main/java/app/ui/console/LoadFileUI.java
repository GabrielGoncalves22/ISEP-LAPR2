package app.ui.console;

import app.core.controller.LoadFileController;
import app.core.dto.SNSUserDTO;
import app.ui.console.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * Represents the load file UI(User interface)
 *
 * @author Group 40
 */
public class LoadFileUI implements Runnable {

    /**
     * The controller responsible for this UI
     */
    LoadFileController controller = new LoadFileController();

    /**
     * Main method of the UI
     */
    @Override
    public void run() {

        System.out.println("Load a set of SNS Users from file");
        String filePath = readFilePath();
        List<SNSUserDTO> lUSDto = this.loadDataFromFile(filePath);

        if (lUSDto != null) {
            showImportedRegisters(lUSDto);
            boolean confirmation = Utils.confirm("Are you sure that you want to load the file in the entered" +
                    " path, with the registers shown above? (y/n)");
            if (confirmation) {
                boolean success = this.loadDataToSystem(lUSDto);
                if (success) {
                    System.out.println("The file was loaded with success!");
                }
            } else {
                System.out.println("Operation canceled!");
            }
        }

    }

    /**
     * Method to print all the SNS Users data imported
     *
     * @param lUSDto List with objects of type SNSUserDTO
     */
    private void showImportedRegisters(List<SNSUserDTO> lUSDto) {

        System.out.println(fillString("_", 159));
        System.out.printf("| %20s | %10s | %10s | %30s | %12s | %20s | %15s | %17s |%n",
                StringUtils.center("Name", 20), StringUtils.center("Sex", 10),
                StringUtils.center("BirthDate", 10), StringUtils.center("Address", 30),
                StringUtils.center("PhoneNumber", 12), StringUtils.center("Email", 20),
                StringUtils.center("SNSUSerNumber", 15), StringUtils.center("CitizenCardNumber", 17));

        for (SNSUserDTO snsUserDTO : lUSDto) {
            makeTableSeparations("|", "-");
            System.out.println(snsUserDTO.toStringFileImported());
        }

        makeTableSeparations("|", "_");
    }

    /**
     * This method print a table separator
     *
     * @param vSeparators The String used to make the vertical separation
     * @param hSeparators The String used to make the horizontal separation
     */
    private void makeTableSeparations(String vSeparators, String hSeparators) {
        System.out.print(vSeparators + fillString(hSeparators, 22) + vSeparators + fillString(hSeparators, 12) + vSeparators
                + fillString(hSeparators, 12) + vSeparators + fillString(hSeparators, 32) + vSeparators
                + fillString(hSeparators, 14) + vSeparators + fillString(hSeparators, 22) + vSeparators
                + fillString(hSeparators, 17) + vSeparators + fillString(hSeparators, 19) + vSeparators + "\n");
    }

    /**
     * Fill a string with a certain character and with a given size
     *
     * @param character The character to build the String
     * @param size      The size that String must have
     * @return The string with a certain amount of a character
     */
    private String fillString(String character, int size) {
        return String.valueOf(character).repeat(size);
    }

    /**
     * Method to ask the administrator the path to the file to be loaded
     *
     * @return The String path introduced by the administrator
     */
    private String readFilePath() {
        return Utils.readLineFromConsole("Insert the name of the file to be loaded:");
    }

    /**
     * Method responsible to call the functionality to load the data from file and handle the response
     *
     * @param filePath The String path to the file where the information is
     * @return A list of object of the type SNSUserDTO
     */
    private List<SNSUserDTO> loadDataFromFile(String filePath) {

        List<SNSUserDTO> lUSDto = null;

        try {
            lUSDto = this.controller.getDataFromFile(filePath);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            //System.out.println("An error occurred, please try again later! If the error persists, please, contact us!");
        }

        return lUSDto;
    }

    /**
     * Method responsible to call controller for load SNS Users into system
     *
     * @param lUSDto List containing SNSUserDTO objects
     * @return true if the data is successfully loaded or false otherwise
     */
    private boolean loadDataToSystem(List<SNSUserDTO> lUSDto) {
        return this.controller.loadSNSUsers(lUSDto);
    }
}
