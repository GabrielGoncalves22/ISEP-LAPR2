package app.core.domain.store;

import app.core.domain.model.Time;
import app.core.domain.model.VaccinationCenter;
import app.core.domain.shared.SerializeClasses;
import pt.isep.lei.esoft.auth.domain.model.Email;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the vaccine center store.
 *
 * @author Group 40
 */
public class VaccinationCenterStore {

    /**
     * The vaccine center list.
     */
    private List<VaccinationCenter> listVC;

    /**
     * The name of the file who has all the information to keep data persistence for vaccination centers.
     */
    private final String FILE_WITH_ALL_DATA = "VaccinationCenter.ser";

    /**
     * Constructor to build a VaccinationCenterStore instance object.
     */
    public VaccinationCenterStore() {
        this.listVC = this.loadVaccinationCenterListFromFile();
    }

    /**
     * Create vaccine center.
     *
     * @param name             the name of the vaccine center.
     * @param address          the address of the vaccine center.
     * @param phoneNumber      the phone number of the vaccine center.
     * @param email            the email of the vaccine center.
     * @param faxNumber        the fax number of the vaccine center.
     * @param website          the website of the vaccine center.
     * @param openingHours     the opening hours of the vaccine center.
     * @param closingHours     the closing hours of the vaccine center.
     * @param slotDuration     the slot duration of the vaccine center.
     * @param slotVaccineLimit the slot vaccine limit of the vaccine center.
     * @return the vaccine center
     */
    public VaccinationCenter createVaccinationCenter(String name, String address, int phoneNumber, Email email,
                                                     int faxNumber, String website, Time openingHours,
                                                     Time closingHours, int slotDuration, int slotVaccineLimit) {
        return new VaccinationCenter(name, address, phoneNumber, email, faxNumber, website, openingHours, closingHours,
                slotDuration, slotVaccineLimit);
    }

    /**
     * Create time.
     *
     * @param hours   the hours of the time.
     * @param minutes the minutes of the time.
     * @return the time.
     */
    public Time createTime(int hours, int minutes) {
        return new Time(hours, minutes);
    }


    /**
     * Function to validate a vaccine center.
     *
     * @param vc the vaccine center.
     * @return true if the received vaccine center object is neither null nor does it belong to the existing list.
     * Otherwise, returns false.
     */
    public boolean validateVaccinationCenter(VaccinationCenter vc) {
        if (vc == null) {
            return false;
        } else {
            this.listVC = this.loadVaccinationCenterListFromFile();
            return !this.listVC.contains(vc);
        }
    }

    /**
     * Function to store a vaccine center object.
     *
     * @param vc the vaccine center.
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveVaccinationCenter(VaccinationCenter vc) {
        if (validateVaccinationCenter(vc)) {
            addVaccinationCenter(vc);
            if (this.saveVaccinationCenterListInFile(this.listVC)) {
                return true;
            } else {
                this.removeVaccinationCenter(vc);
            }
        }

        return false;
    }

    /**
     * Removes a certain vaccination center from the list.
     *
     * @param vc The vaccination center.
     */
    private void removeVaccinationCenter(VaccinationCenter vc) {
        this.listVC.remove(vc);
    }

    /**
     * Method to save all the vaccination centers registered in the system into the file to keep data persistence.
     *
     * @param listVC The list with all the vaccination centers.
     * @return true if the operation is successfully completed or false otherwise.
     */
    private boolean saveVaccinationCenterListInFile(List<VaccinationCenter> listVC) {
        return SerializeClasses.saveObjectIntoDisk(listVC, this.FILE_WITH_ALL_DATA);
    }

    /**
     * Method to load into memory all the vaccination centers registered in the system.
     *
     * @return The list with all the vaccination centers.
     */
    private List<VaccinationCenter> loadVaccinationCenterListFromFile() {
        return SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
    }

    /**
     * Function to add vaccination center to the existing list.
     *
     * @param vc the vaccination center.
     * @return true if the object is successfully added. Otherwise, returns false.
     */
    private boolean addVaccinationCenter(VaccinationCenter vc) {
        return this.listVC.add(vc);
    }

    /**
     * Returns the list of the vaccination centers.
     *
     * @return the vaccination center list.
     */
    public List<VaccinationCenter> getVaccinationCenterList() {
        this.listVC = this.loadVaccinationCenterListFromFile();
        return listVC;
    }

    /**
     * Returns the vaccination center list without center coordinator.
     *
     * @return the vaccination center list without center coordinator.
     */
    public List<VaccinationCenter> getVaccinationCenterListWithoutCenterCoordinator() {
        List<VaccinationCenter> listVaccinationCenters = new ArrayList<>();

        this.listVC = this.loadVaccinationCenterListFromFile();

        for (VaccinationCenter list : this.listVC) {
            if (list.getCenterCoordinatorId() == 0) {
                listVaccinationCenters.add(list);
            }
        }

        return listVaccinationCenters;
    }

    /**
     * Method to define the vaccination center coordinator.
     *
     * @param centerCoordinatorId the id of the vaccination center coordinator.
     * @param vc                  the vaccination center.
     */
    public void defineVaccinationCenterCoordinator(int centerCoordinatorId, VaccinationCenter vc) {
        this.listVC = this.loadVaccinationCenterListFromFile();

        for (VaccinationCenter list : this.listVC) {
            if (list.equals(vc)) {
                list.setCenterCoordinatorId(centerCoordinatorId);
            }
        }

        this.saveVaccinationCenterListInFile(this.listVC);
    }

    /**
     * Returns a vaccination center with a given id.
     *
     * @param vcId the id of the vaccine center.
     * @return the vaccination center.
     */
    public VaccinationCenter getVaccinationCenter(int vcId) {
        this.listVC = this.loadVaccinationCenterListFromFile();

        VaccinationCenter vc = null;

        for (VaccinationCenter list : listVC) {
            if (list.getId() == vcId) {
                vc = list;
            }
        }

        return vc;
    }

    /**
     * Function to check if an id of a vaccination center exists.
     *
     * @param vcId the id of the vaccine center.
     * @return true if it exists. Otherwise, false.
     */
    public boolean existsVaccinationCenter(int vcId) {
        boolean exists = false;
        this.listVC = this.loadVaccinationCenterListFromFile();

        for (VaccinationCenter list : this.listVC) {
            if (list.getId() == vcId) {
                exists = true;
            }
        }

        return exists;
    }

    /**
     * Function to return the vaccination center from a vaccination center coordinator.
     *
     * @param centerCoordinatorId the id of the vaccination center coordinator.
     * @return the id of the vaccination center.
     */
    public int getVaccinationCenterIdCenterCoordinator(int centerCoordinatorId) {
        int vcId = 0;
        this.listVC = this.loadVaccinationCenterListFromFile();

        for (VaccinationCenter list : this.listVC) {
            if (list.getCenterCoordinatorId() == centerCoordinatorId) {
                vcId = list.getId();
            }
        }

        return vcId;
    }
}
