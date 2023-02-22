package app.core.domain.store;

import app.core.domain.shared.SerializeClasses;
import app.core.dto.SNSUserDTO;
import app.core.mapper.SNSUserMapper;
import app.core.domain.model.Date;
import app.core.domain.model.SNSUser;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.List;

/**
 * Represents all the SNS Users stored in the system.
 *
 * @author Group 40
 */
public class SNSUserStore {

    /**
     * The vaccine center list.
     */
    private List<SNSUser> snsUserList;

    /**
     * The name of the file who has all the information to keep data persistence for SNS Users.
     */
    private final String FILE_WITH_ALL_DATA = "SNSUser.ser";

    /**
     * Constructor to build a SNSUserStore instance object.
     */
    public SNSUserStore() {
        this.snsUserList = this.loadSNSUsersListFromFile();
    }

    /**
     * Create SNS User.
     *
     * @param name              the name of the SNS User.
     * @param address           the address of the SNS User.
     * @param sex               the sex of SNS User.
     * @param phoneNumber       the phoneNumber of SNS User.
     * @param email             the email of the SNS User.
     * @param birthdate         the birth-date of the SNS User.
     * @param snsUserNumber     the SNS User number of SNS User.
     * @param citizenCardNumber the citizen card number of SNS User.
     * @return the SNS User.
     */
    public SNSUser createSNSUser(String name, String address, String sex, String phoneNumber, Email email,
                                 Date birthdate, String snsUserNumber, String citizenCardNumber) {
        return new SNSUser(name, address, sex, phoneNumber, email, birthdate, snsUserNumber, citizenCardNumber);
    }

    /**
     * Function to validate an SNS User.
     *
     * @param snsUser the SNS User.
     * @return true if the received SNS User object is neither null nor does it belong to the existing list.
     * Otherwise, returns false.
     */
    public boolean validateSNSUser(SNSUser snsUser) {
        if (snsUser == null) {
            return false;
        } else {
            return !checkDuplicates(snsUser);
        }
    }

    /**
     * Check the duplicated Sns Users.
     *
     * @param snsUser The Sns User
     * @return true if already registered, false otherwise.
     */
    public boolean checkDuplicates(SNSUser snsUser) {
        return this.snsUserList.contains(snsUser);
    }

    /**
     * Function to store an SNS User object.
     *
     * @param snsUser the SNS User.
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveSNSUser(SNSUser snsUser) {
        if (!validateSNSUser(snsUser)) {
            return false;
        } else {
            return addSNSUser(snsUser);
        }
    }

    /**
     * Removes a certain SNS User from the list.
     *
     * @param snsUser The SNS User.
     */
    public void removeSNSUser(SNSUser snsUser) {
        this.snsUserList.remove(snsUser);
    }

    /**
     * Method to save all the SNS Users registered in the system into the file to keep data persistence.
     *
     * @return true if the operation is successfully completed or false otherwise.
     */
    public boolean saveSNSUserListInFile() {
        return SerializeClasses.saveObjectIntoDisk(this.snsUserList, this.FILE_WITH_ALL_DATA);
    }

    /**
     * Function to add an SNS User center to the existing list.
     *
     * @param snsUser the SNS User.
     * @return true if the object is successfully added. Otherwise, returns false.
     */
    private boolean addSNSUser(SNSUser snsUser) {
        return this.snsUserList.add(snsUser);
    }

    /**
     * Method to load into memory all the SNS User registered in the system.
     *
     * @return The list with all the SNS Users.
     */
    public List<SNSUser> loadSNSUsersListFromFile() {
        return SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
    }

    /**
     * Returns the list of the SNS Users.
     *
     * @return the SNS User list.
     */
    public List<SNSUser> getSNSUserList() {
        this.snsUserList = this.loadSNSUsersListFromFile();
        return snsUserList;
    }

    /**
     * Function to check if there is a sns user through SNS user Number.
     *
     * @param snsUserNumber the SNS user number of SNS user.
     * @return the SNS user.
     */
    public SNSUser getSNSUser(String snsUserNumber) {

        SNSUser su = null;

        for (SNSUser item : snsUserList) {
            if (item.getSnsUserNumber().equals(snsUserNumber)) {
                su = item;
            }
        }

        return su;
    }

    /**
     * Function with the purpose of asking to SNSUserMapper to transform a list of SNSUserDTO objects into a list of
     * SNSUser objects.
     *
     * @param lSUDto List of objects of type SNSUserDTO.
     * @return A list with objects of type SNSUser.
     */
    public List<SNSUser> lSUDtoToModelList(List<SNSUserDTO> lSUDto) {
        return SNSUserMapper.toModelList(lSUDto);
    }

    /**
     * Function to return the age of the SNS user.
     *
     * @param su the SNS user.
     * @return the age of the SNS user.
     */
    public int currentAgeSNSUser(SNSUser su) {
        return Date.currentAge(su.getBirthdate());
    }
}
