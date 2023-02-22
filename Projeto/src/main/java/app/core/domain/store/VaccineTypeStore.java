package app.core.domain.store;

import app.core.domain.model.VaccineType;
import app.core.domain.shared.SerializeClasses;

import java.util.List;

/**
 * Represents the vaccine type store.
 *
 * @author Group 40
 */
public class VaccineTypeStore {
    /**
     * The vaccine type list.
     */
    private List<VaccineType> vtList;

    /**
     * The name of the file who has all the information to keep data persistence for the vaccine types.
     */
    private final String FILE_WITH_ALL_DATA = "VaccineType.ser";

    /**
     * Constructor to build a VaccineTypeStore instance object.
     */
    public VaccineTypeStore() {
        this.vtList = this.loadVaccineTypeListFromFile();
    }

    /**
     * Function to return a new instance of VaccineType.
     *
     * @param code        the code of the vaccine type.
     * @param description the description of the vaccine type.
     * @param technology  the technology of the vaccine type.
     * @return the vaccine type.
     */
    public VaccineType createVaccineType(String code, String description, String technology) {
        return new VaccineType(code, description, technology);
    }

    /**
     * Function to validate a type of vaccine.
     *
     * @param vt the vaccine type.
     * @return true if the received VaccineType object is neither null nor does it belong to the existing list.
     * Otherwise, returns false.
     */
    public boolean validateVaccineType(VaccineType vt) {
        if (vt == null) {
            return false;
        } else {
            return !this.vtList.contains(vt);
        }
    }

    /**
     * Function to store a VaccineType object.
     *
     * @param vt the vaccine type.
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveVaccineType(VaccineType vt) {
        if (validateVaccineType(vt)) {
            addVaccineType(vt);
            if (this.saveVaccineTypeListInFile(this.vtList)) {
                return true;
            } else {
                this.removeVaccineType(vt);
            }
        }

        return false;
    }

    /**
     * Removes a certain vaccine type from the list.
     *
     * @param vt The vaccine type.
     */
    private void removeVaccineType(VaccineType vt) {
        this.vtList.remove(vt);
    }

    /**
     * Method to save all the vaccine types registered in the system into the file to keep data persistence.
     *
     * @param vtList The list with all the vaccine types.
     * @return true if the operation is successfully completed or false otherwise.
     */
    private boolean saveVaccineTypeListInFile(List<VaccineType> vtList) {
        return SerializeClasses.saveObjectIntoDisk(vtList, this.FILE_WITH_ALL_DATA);
    }

    /**
     * Method to load into memory all the vaccine types registered in the system.
     *
     * @return The list with all the vaccine types.
     */
    private List<VaccineType> loadVaccineTypeListFromFile() {
        return SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
    }

    /**
     * Function to add a VaccineType object to the existing list.
     *
     * @param vt the vaccine type.
     * @return true if the object is successfully added. Otherwise, returns false.
     */
    private boolean addVaccineType(VaccineType vt) {
        return this.vtList.add(vt);
    }

    /**
     * Returns the list of the type of vaccines.
     *
     * @return the vaccine type list.
     */
    public List<VaccineType> getVaccineTypeList() {
        this.vtList = this.loadVaccineTypeListFromFile();
        return vtList;
    }

    /**
     * Method to search and return a vaccine type from the list by the code.
     *
     * @param code The code of vaccine type.
     * @return The vaccine type.
     */
    public VaccineType getVaccineTypeByCode(String code) {

        VaccineType vT = null;
        int i = 0;
        boolean found = false;

        while (i < this.vtList.size() && !found) {

            if (this.vtList.get(i).getCode().equals(code)) {
                vT = this.vtList.get(i);
                found = true;
            }
            i++;

        }

        return vT;

    }

}
