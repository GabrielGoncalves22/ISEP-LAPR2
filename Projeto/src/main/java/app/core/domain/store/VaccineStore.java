package app.core.domain.store;

import app.core.domain.model.*;
import app.core.domain.shared.SerializeClasses;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents the vaccine store.
 *
 * @author Group 40
 */
public class VaccineStore {
    /**
     * The vaccine list.
     */
    private List<Vaccine> listV;

    /**
     * The name of the file who has all the information to keep data persistence for vaccines.
     */
    private final String FILE_WITH_ALL_DATA = "Vaccine.ser";

    /**
     * Constructor to build a VaccineStore instance object.
     */
    public VaccineStore() {
        this.listV = loadVaccineListFromFile();
    }

    /**
     * Function to return a new instance of Vaccine.
     *
     * @param name  the name of the vaccine.
     * @param brand the brand of the vaccine.
     * @param vt    the vaccine type of the vaccine.
     * @return the vaccine.
     */
    public Vaccine createVaccine(String name, String brand, VaccineType vt) {
        return new Vaccine(name, brand, vt);
    }

    /**
     * Function to validate a vaccine.
     *
     * @param v the vaccine.
     * @return true if the received Vaccine object is neither null nor does it belong to the existing list. Otherwise,
     * returns false.
     */
    public boolean validateVaccine(Vaccine v) {
        if (v == null) {
            return false;
        } else {
            this.listV = this.loadVaccineListFromFile();
            return !this.listV.contains(v);
        }
    }

    /**
     * Function to return a new instance of AgeGroup.
     *
     * @param minimumAge the minimum age of the age group.
     * @param maximumAge the maximum age of the age group.
     * @return the age group.
     */
    public AgeGroup createAgeGroup(int minimumAge, int maximumAge) {
        return new AgeGroup(minimumAge, maximumAge);
    }

    /**
     * Function to add an age group to the vaccine.
     *
     * @param v  the vacine.
     * @param ag the age group.
     * @return the vaccine with the age group added.
     */
    public Vaccine addAgeGroup(Vaccine v, AgeGroup ag) {
        v.addAgeGroup(ag);
        return v;
    }

    /**
     * Function to validate an age group.
     *
     * @param agList the ag list
     * @param ag     the age group.
     * @return true if the received AgeGroup object is neither null nor does it belong to the existing list. Otherwise,
     * returns false.
     */
    public boolean validateAgeGroup(List<AgeGroup> agList, AgeGroup ag) {
        if (ag == null) {
            return false;
        } else {
            return !agList.contains(ag);
        }
    }

    /**
     * Function to return a new instance of Dose.
     *
     * @param doseNumber                  the dose number of the dose.
     * @param dosage                      the dosage of the dose.
     * @param timeIntervalBetweenLastDose the time interval between last dose of the dose.
     * @return the dose.
     */
    public Dose createDose(int doseNumber, int dosage, int timeIntervalBetweenLastDose) {
        return new Dose(doseNumber, dosage, timeIntervalBetweenLastDose);
    }

    /**
     * Function to add a dose to the age group.
     *
     * @param ag the age group.
     * @param ap the dose.
     * @return the age group with the dose added.
     */
    public AgeGroup addDose(AgeGroup ag, Dose ap) {
        ag.addDose(ap);
        return ag;
    }

    /**
     * Function to validate a dose.
     *
     * @param apList the ap list
     * @param ap     the dose.
     * @return true if the received Dose object is neither null nor does it belong to the existing list. Otherwise,
     * returns false.
     */
    public boolean validateDose(List<Dose> apList, Dose ap) {
        if (ap == null) {
            return false;
        } else {
            return !apList.contains(ap);
        }
    }

    /**
     * Function to store a Vaccine object.
     *
     * @param v the vaccine.
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveVaccine(Vaccine v) {
        if (validateVaccine(v)) {
            addVaccine(v);
            if (this.saveVaccineListInFile(this.listV)) {
                return true;
            } else {
                this.removeVaccine(v);
            }
        }

        return false;
    }

    /**
     * Removes a certain vaccine from the list.
     *
     * @param v The vaccine.
     */
    private void removeVaccine(Vaccine v) {
        this.listV.remove(v);
    }

    /**
     * Method to save all the vaccines registered in the system into the file to keep data persistence.
     *
     * @param vaccineList The list with all the vaccines.
     * @return true if the operation is successfully completed or false otherwise.
     */
    private boolean saveVaccineListInFile(List<Vaccine> vaccineList) {
        return SerializeClasses.saveObjectIntoDisk(vaccineList, this.FILE_WITH_ALL_DATA);
    }

    /**
     * Method to load into memory all the vaccines registered in the system.
     *
     * @return The list with all the vaccines.
     */
    private List<Vaccine> loadVaccineListFromFile() {
        return SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
    }

    /**
     * Function to add a Vaccine object to the existing list.
     *
     * @param v the vaccine.
     * @return true if the object is successfully added. Otherwise, returns false.
     */
    private boolean addVaccine(Vaccine v) {
        return this.listV.add(v);
    }

    /**
     * Returns a vaccine with a given id.
     *
     * @param id the id of the vaccine.
     * @return the vaccine.
     */
    public Vaccine getVaccine(int id) {

        Vaccine v = null;

        for (Vaccine list : this.listV) {
            if (list.getId() == id) {
                v = list;
            }
        }

        return v;
    }

    /**
     * Function to return a list with the vaccines of a type of vaccine in an age group.
     *
     * @param vt  the vaccine type of the vaccine.
     * @param age the age.
     * @return the list with the vaccines of a type of vaccine in an age group.
     */
    public List<Vaccine> getListSuggestedVaccines(VaccineType vt, int age) {
        List<Vaccine> listV = new ArrayList<>();
        boolean addedVaccine = false;

        this.listV = loadVaccineListFromFile();

        for (Vaccine listVaccine : this.listV) {
            if (listVaccine.getVt().equals(vt)) {

                for (AgeGroup listAgeGroup : listVaccine.getAgList()) {
                    if (age >= listAgeGroup.getMinimumAge() && age <= listAgeGroup.getMaximumAge() && !addedVaccine) {
                        addedVaccine = true;
                        listV.add(listVaccine);
                    }
                }

                addedVaccine = false;
            }
        }

        return listV;
    }

    /**
     * Function to return a list with the doses of a vaccine in an age group.
     *
     * @param v   the vaccine.
     * @param age the age.
     * @return the list with the doses of a vaccine in an age group.
     */
    public List<Dose> getListDose(Vaccine v, int age) {
        List<Dose> listV = new ArrayList<>();

        for (AgeGroup listAgeGroup : v.getAgList()) {
            if (age >= listAgeGroup.getMinimumAge() && age <= listAgeGroup.getMaximumAge()) {
                listV = listAgeGroup.getDoseList();
            }
        }

        return listV;
    }

    /**
     * Method to get all the registered vaccines.
     *
     * @return The vaccine list.
     */
    public List<Vaccine> getListV() {
        this.listV = this.loadVaccineListFromFile();
        return this.listV;
    }

    /**
     * Method to find and return the dose with the conditions indicated in the given parameters.
     *
     * @param v          The vaccine.
     * @param age        Age of the SNS User.
     * @param doseNumber The dose number.
     * @return The correspondent dose object.
     */
    public Dose getDose(Vaccine v, int age, int doseNumber) {
        Dose d = null;

        for (AgeGroup listAgeGroup : v.getAgList()) {
            if (age >= listAgeGroup.getMinimumAge() && age <= listAgeGroup.getMaximumAge()) {

                for (Dose listDose : listAgeGroup.getDoseList()) {
                    if (listDose.getDoseNumber() == doseNumber) {
                        d = listDose;
                    }
                }
            }
        }

        return d;
    }

}
