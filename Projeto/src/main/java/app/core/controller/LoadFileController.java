package app.core.controller;

import app.core.dto.SNSUserDTO;
import app.core.domain.model.Company;
import app.core.domain.model.SNSUser;
import app.core.domain.shared.FileHandler;
import app.core.domain.store.SNSUserStore;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for coordinating and distributing the actions performed on the LoadFileUI
 *
 * @author Group 40
 */
public class LoadFileController {

    /**
     * Represents the company.
     */
    private Company company;

    /**
     * Contains all the representations of the classes that can handle a type of file.
     */
    private String[] fileHandlerClasses = {"app.core.domain.model.FileHandlerType1", "app.core.domain.model.FileHandlerType2"};

    /**
     * Builds an instance of LoadFileController.
     */
    public LoadFileController() {
        this(App.getInstance().getCompany());
    }

    /**
     * Builds an instance of LoadFileController receiving the company as parameter.
     *
     * @param company The company.
     */
    public LoadFileController(Company company) {
        this.company = company;
    }

    /**
     * Method responsible to call the necessary operations to read the data present in the file of a certain path.
     *
     * @param filePath The path to the location of the file.
     * @return A list of objects of type SNSUserDTO.
     * @throws Exception In case of an error happen when doing this operation (file is not found or an error on file
     *                   is noticed)
     */
    public List<SNSUserDTO> getDataFromFile(String filePath) throws Exception {
        List<SNSUserDTO> lSUDto = new ArrayList<SNSUserDTO>();
        int numberFile = 0;
        boolean validFileHandler = false;

        while (numberFile < this.fileHandlerClasses.length && !validFileHandler) {

            try {
                FileHandler fHandler = createFileHandler(filePath, this.fileHandlerClasses[numberFile]);

                try {
                    lSUDto = fHandler.readDataToSNSUserDTO();
                    validFileHandler = true;
                } catch (IllegalArgumentException ex) {
                    if (numberFile == this.fileHandlerClasses.length - 1) {
                        throw new RuntimeException(ex.getMessage());
                    }
                } finally {
                    numberFile++;
                }

            } catch (RuntimeException ex) {
                throw new RuntimeException(ex.getMessage());
            } catch (Exception ex) {
                throw new IllegalArgumentException("Invalid file!");
            }

        }

        return lSUDto;
    }

    /**
     * Method to create an instance of a given representation of a class.
     *
     * @param filePath        The path to the location of the file.
     * @param classReflection A String representing a class that can handle a type of files.
     * @return The instance of the class with the given representation.
     * @throws Exception In case of an error happen (i.e. the class with the given representation isn't found)
     */
    public FileHandler createFileHandler(String filePath, String classReflection) throws Exception {
        Class<?> oClass = Class.forName(classReflection);
        Constructor<?> cons = oClass.getConstructor(String.class);
        return (FileHandler) cons.newInstance(filePath);
    }

    /**
     * Method responsible to call the necessary operations to transform SNSUsersDTO in SNSUsers and load them in
     * the system (save).
     *
     * @param lUSDto A list with objects of the type SNSUserDTO.
     * @return True if the SNS users are successfully transformed into SNSUsers model and saved in the system.
     */
    public boolean loadSNSUsers(List<SNSUserDTO> lUSDto) {
        boolean success = true;
        SNSUserStore store = this.company.getSNSUserStore();

        try {

            List<SNSUser> lSU = store.lSUDtoToModelList(lUSDto);
            for (SNSUser snsUser : lSU) {
                store.saveSNSUser(snsUser);
            }

            store.saveSNSUserListInFile();

        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

        return success;
    }

}
