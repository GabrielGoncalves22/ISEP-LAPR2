package app.core.domain.store;

import app.core.domain.model.*;
import app.core.domain.shared.SerializeClasses;
import app.core.dto.ArrivalSNSUserDTO;
import app.core.mapper.ArrivalSNSUserMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the SNS user arrival store.
 *
 * @author Group 40
 */
public class ArrivalSNSUserStore {

    /**
     * The arrival SNS user type list.
     */
    private List<ArrivalSNSUser> listAU;

    /**
     * This variable holds a temporary arrival SNS User list.
     */
    private List<ArrivalSNSUser> tempListASU = new ArrayList<>();

    /**
     * The name of the file who has all the information to keep data persistence for the SNS Users arrivals.
     */
    private final String FILE_WITH_ALL_DATA = "ArrivalSNSUser.ser";

    /**
     * Constructor to build a ArrivalSNSUserStore instance object.
     */
    public ArrivalSNSUserStore() {
        this.listAU = this.loadSNSUsersArrivalsListFromFile();
    }

    /**
     * Function to return a new instance of ArrivalSNSUser.
     *
     * @param auDto the SNS user arrival dto.
     * @return the SNS user arrival.
     */
    public ArrivalSNSUser createArrivalSNSUser(ArrivalSNSUserDTO auDto) {
        ArrivalSNSUser au = ArrivalSNSUserMapper.toModel(auDto);
        return au;
    }

    /**
     * Method to instantiate a new Arrival SNS User.
     *
     * @param sU          The SNS User.
     * @param arrivalDate The arrival date.
     * @param arrivalTime The arrival time.
     * @param vc          The vaccination center.
     * @return The new Arrival SNS User object.
     */
    public ArrivalSNSUser instantiateArrivalSNSUser(SNSUser sU, Date arrivalDate, Time arrivalTime,
                                                    VaccinationCenter vc) {
        return new ArrivalSNSUser(sU, arrivalDate, arrivalTime, vc);
    }

    /**
     * Function to check if arrival of an SNS user exists in the existing list.
     *
     * @param au the arrival of the SNS user.
     * @return true if the received ArrivalSNSUser object is neither null nor does it belong to the existing list.
     * Otherwise, returns false.
     */
    public boolean validateArrivalSNSUser(ArrivalSNSUser au) {
        if (au == null) {
            return false;
        } else {
            return !checkDuplicates(au);
        }
    }

    /**
     * @param au the arrival of the SNS user.
     * @return true if the incoming ArrivalSNSUser object exists in the existing list.
     * Otherwise, returns false.
     */
    public boolean checkDuplicates(ArrivalSNSUser au) {
        return this.listAU.contains(au);
    }

    /**
     * Function to store a ArrivalSNSUser object.
     *
     * @param au the arrival of the SNS user.
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveArrivalSNSUser(ArrivalSNSUser au) {
        if (!validateArrivalSNSUser(au)) {
            return false;
        } else {
            return this.addArrivalSNSUser(au);
        }
    }

    /**
     * Removes a certain Arrival SNS User from the list.
     *
     * @param au The arrival of the SNS User.
     */
    public void removeArrivalSNSUser(ArrivalSNSUser au) {
        this.listAU.remove(au);
    }

    /**
     * Method to save all the SNS User arrivals registered in the system into the file to keep data persistence.
     *
     * @return true if the operation is successfully completed or false otherwise.
     */
    public boolean saveArrivalSNSUserListInFile() {
        return SerializeClasses.saveObjectIntoDisk(this.listAU, this.FILE_WITH_ALL_DATA);
    }

    /**
     * Method to load into memory all the SNS Users arrivals registered in the system.
     *
     * @return The list with all the arrivals of SNS Users.
     */
    public List<ArrivalSNSUser> loadSNSUsersArrivalsListFromFile() {
        return SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
    }

    /**
     * Function to add a ArrivalSNSUser object to the existing list.
     *
     * @param arrivalSNSUser the arrival of the SNS user.
     * @return true if the object is successfully added. Otherwise, returns false.
     */
    private boolean addArrivalSNSUser(ArrivalSNSUser arrivalSNSUser) {
        return this.listAU.add(arrivalSNSUser);
    }

    /**
     * Function to return the list arrival sns user from a certain center.
     *
     * @param vc the vaccination center.
     * @return the list arrival sns user.
     */
    public List<ArrivalSNSUser> getListArrivalSNSUser(VaccinationCenter vc) {
        this.listAU = this.loadSNSUsersArrivalsListFromFile();
        List<ArrivalSNSUser> listAU = new ArrayList<>();

        for (int i = 0; i < this.listAU.size(); i++) {
            ArrivalSNSUser au = this.listAU.get(i);

            if (criteriaVaccinationCenter(au, vc)) {
                listAU.add(au);
            }
        }

        return listAU;
    }

    /**
     * Method to verify that an sns user arrival at a vaccination center meets certain criteria.
     *
     * @param au the arrival sns user.
     * @param vc the vaccination center
     * @return true if the criterion is met, otherwise false.
     */
    private boolean criteriaVaccinationCenter(ArrivalSNSUser au, VaccinationCenter vc) {
        return au.getArrivalDate().equals(Date.currentDate()) && au.getVaccinationCenter().equals(vc) && au.getWaitingRoom();
    }

    /**
     * Method to remove an SNS user from the waiting room.
     *
     * @param snsUser the SNS user.
     */
    public void removeWaitingRoom(SNSUser snsUser) {
        this.listAU = this.loadSNSUsersArrivalsListFromFile();

        for (ArrivalSNSUser item : listAU) {
            if (item.getSnsUser().equals(snsUser) && item.getWaitingRoom()) {
                item.setWaitingRoom(false);
            }
        }

        this.saveArrivalSNSUserListInFile();
    }

    /**
     * Method to return the list of SNS user arrivals on a specific date at a vaccination center.
     *
     * @param intendedDate the intended date
     * @param vc           the vaccination center.
     * @return the list arrival sns user.
     */
    public List<ArrivalSNSUser> getArrivalSNSUserList(Date intendedDate, VaccinationCenter vc) {
        this.listAU = this.loadSNSUsersArrivalsListFromFile();
        List<ArrivalSNSUser> listAU = new ArrayList<>();

        for (int i = 0; i < this.listAU.size(); i++) {
            ArrivalSNSUser au = this.listAU.get(i);

            if (au.getArrivalDate().equals(intendedDate) && au.getVaccinationCenter().equals(vc)) {
                listAU.add(au);
            }
        }

        return listAU;
    }

    /**
     * Method to add items to the temporary list.
     *
     * @param sU  The SNS User.
     * @param lSF The LegacySystemFile.
     * @param vc  The vaccination center.
     * @return true if successfully adds the data, or false otherwise.
     */
    public boolean createLegacySystemTemporaryData(SNSUser sU, LegacySystemFile lSF, VaccinationCenter vc) {
        ArrivalSNSUser asu = this.instantiateArrivalSNSUser(sU, lSF.getArrivalDate(),
                lSF.getArrivalTime(), vc);

        if (!this.validateArrivalSNSUser(asu)) {
            return false;
        }

        return this.tempListASU.add(asu);
    }

    /**
     * Method to save and persist the data recorded in the temporary list.
     */
    public void saveLegacySystemTemporaryData() {
        for (ArrivalSNSUser asu : this.tempListASU) {
            this.saveArrivalSNSUser(asu);
        }

        this.saveArrivalSNSUserListInFile();

        this.cleanTempList();

    }

    /**
     * Cleans the temporary list.
     */
    public void cleanTempList() {
        this.tempListASU = new ArrayList<>();
    }

}
