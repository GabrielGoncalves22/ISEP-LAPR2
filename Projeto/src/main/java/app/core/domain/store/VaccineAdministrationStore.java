package app.core.domain.store;

import app.core.domain.model.*;
import app.core.domain.shared.SerializeClasses;
import app.core.dto.VaccinationCenterDTO;
import app.core.dto.VaccineAdministrationDTO;
import app.core.mapper.VaccineAdministrationMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * Represents the vacine administration store.
 *
 * @author Group 40
 */
public class VaccineAdministrationStore {

    /**
     * The vacine administration list.
     */
    private List<VaccineAdministration> listVA = new ArrayList<>();

    /**
     * This variable holds a temporary vaccine administration.
     */
    private List<VaccineAdministration> tempListVA = new ArrayList<>();

    /**
     * The name of the file who has all the information to keep data persistence for the vaccine administration.
     */
    private final String FILE_WITH_ALL_DATA = "VaccineAdministration.ser";

    public VaccineAdministrationStore() {
        this.listVA = this.loadVaccineAdministrationListFromFile();
    }

    /**
     * Function to return a new instance of VaccineAdministration.
     *
     * @param avDto the vacine administration dto.
     * @return the vacine administration.
     */
    public VaccineAdministration createVaccineAdministration(VaccineAdministrationDTO avDto) {
        return VaccineAdministrationMapper.toModel(avDto);
    }

    /**
     * Method to instantiate a new object of Vaccine administration
     *
     * @param sU            The SNS User.
     * @param v            The vaccine.
     * @param dose               The dose.
     * @param administrationDate The administration date.
     * @param administrationTime The administration time.
     * @param leavingDate        The leaving date.
     * @param leavingTime        The leaving time.
     * @param vc            The vaccination center.
     * @param lotNumber          The lot number of the vaccine.
     * @return The vaccine center instance.
     */
    public VaccineAdministration instantiateVaccineAdministration(SNSUser sU, Vaccine v, Dose dose,
                                                                  Date administrationDate, Time administrationTime,
                                                                  Date leavingDate, Time leavingTime,
                                                                  VaccinationCenter vc, String lotNumber) {
        return new VaccineAdministration(sU, v, dose, administrationDate, administrationTime, leavingDate,
                leavingTime, vc, lotNumber);
    }

    /**
     * Function to check if vacine administration in the existing list and is valid.
     *
     * @param va the vacine administration.
     * @return true if the received AdministrationVaccine object is neither null nor does it belong to the existing list.
     * Otherwise, returns false.
     */
    public boolean validateVaccineAdministration(VaccineAdministration va) {
        if (va == null) {
            return false;
        } else {
            return !checkDuplicates(va);
        }
    }

    /**
     * Function to check if vacine administration in the existing list.
     *
     * @param va the vacine administration.
     * @return true if the incoming AdministrationVaccine object exists in the existing list.
     * Otherwise, returns false.
     */
    public boolean checkDuplicates(VaccineAdministration va) {
        return this.listVA.contains(va);
    }

    /**
     * Function to store a AdministrationVaccine object.
     *
     * @param va the vacine administration.
     * @return true if the object is successfully saved. Otherwise, returns false.
     */
    public boolean saveVaccineAdministration(VaccineAdministration va) {
        if (!validateVaccineAdministration(va)) {
            return false;
        } else {
            return addAdministrationVaccine(va);
        }
    }

    /**
     * Removes a certain vaccine administration from the list.
     *
     * @param va The vaccine administration.
     */
    public void removeVaccineAdministration(VaccineAdministration va) {
        this.listVA.remove(va);
    }

    /**
     * Function to add a AdministrationVaccine object to the existing list.
     *
     * @param av the vacine administration.
     * @return true if the object is successfully added. Otherwise, returns false.
     */
    private boolean addAdministrationVaccine(VaccineAdministration av) {
        return this.listVA.add(av);
    }

    /**
     * Method to save all the vaccine administrations registered in the system into the file to keep data persistence.
     *
     * @return true if the operation is successfully completed or false otherwise.
     */
    public boolean saveVaccineAdministrationListInFile() {
        return SerializeClasses.saveObjectIntoDisk(this.listVA, this.FILE_WITH_ALL_DATA);
    }

    /**
     * Method to load into memory all the vaccine administrations registered in the system.
     *
     * @return The list with all the vaccine administrations.
     */
    public List<VaccineAdministration> loadVaccineAdministrationListFromFile() {
        return SerializeClasses.loadObjectIntoMemory(this.FILE_WITH_ALL_DATA);
    }

    /**
     * Function to return the vaccine that an SNS user is taking from a type of vaccine.
     *
     * @param su the SNS user.
     * @param vt the vaccine type.
     * @return the vaccine.
     */
    public Vaccine currentVaccine(SNSUser su, VaccineType vt) {
        Vaccine v = null;

        this.listVA = this.loadVaccineAdministrationListFromFile();

        for (VaccineAdministration list : this.listVA) {
            if (list.getSnsUser().equals(su)
                    && list.getVaccine().getVt().equals(vt)) {

                v = list.getVaccine();
            }
        }

        return v;
    }

    /**
     * Function to return the list of doses that are missing in the administration of a vaccine to an SNS user.
     *
     * @param su    su the SNS user.
     * @param v     the vaccine.
     * @param listD the list of doses.
     * @return the list of doses.
     */
    public List<Dose> missingDoses(SNSUser su, Vaccine v, List<Dose> listD) {
        this.listVA = this.loadVaccineAdministrationListFromFile();

        for (VaccineAdministration list : this.listVA) {
            if (list.getSnsUser().equals(su)
                    && list.getVaccine().equals(v)) {

                listD.remove(list.getDose());
            }
        }

        return listD;
    }

    /**
     * Function to return the vaccine administration list on a particular day.
     *
     * @param intendedDate the intended date.
     * @param vc           the vaccine administration vaccination center.
     * @return the vaccine administration list on a particular day.
     */
    public List<VaccineAdministration> getVaccineAdministrationList(Date intendedDate, VaccinationCenter vc) {
        List<VaccineAdministration> listVA = new ArrayList<>();

        this.listVA = this.loadVaccineAdministrationListFromFile();

        for (int i = 0; i < this.listVA.size(); i++) {
            VaccineAdministration au = this.listVA.get(i);

            if (au.getLeavingDate().equals(intendedDate) && au.getVaccinationCenter().equals(vc)) {
                listVA.add(au);
            }
        }

        return listVA;
    }

    /**
     * Function to return the total number of vaccinated in a day in a vaccination center.
     *
     * @param listSU The SNS user list.
     * @param listV  The vaccine list.
     * @param date   the date.
     * @param vc     the vaccine administration vaccination center.
     * @return the total number of vaccinated in a day
     */
    public int totalNumberFullyVaccinatedInDay(List<SNSUser> listSU, List<Vaccine> listV, Date date, VaccinationCenter vc) {
        int totalFullyVaccinated = 0;

        List<VaccineAdministration> listVA = vaccineAdministrationListBetweenTwoDates(date, date);

        for (SNSUser su : listSU) {
            for (Vaccine v : listV) {
                int totalVaccineAdministration = totalVaccineAdministration(listVA, su, v, date, vc);
                if (snsUserTotalVaccinated(su, v, totalVaccineAdministration)) {
                    totalFullyVaccinated++;
                }
            }
        }

        return totalFullyVaccinated;
    }

    /**
     * Function to return the total number of vaccinated in a given interval in a vaccination center.
     *
     * @param listSU    The SNS user list.
     * @param listV     The vaccine list.
     * @param startDate the start date of the range.
     * @param endDate   the end date of the range.
     * @param vc        the vaccine administration vaccination center.
     * @return total number of vaccinated in a given interval.
     */
    public List<Integer> totalNumberFullyVaccinatedInInterval(List<SNSUser> listSU, List<Vaccine> listV, Date startDate, Date endDate, VaccinationCenter vc) {
        List<Integer> totalFullyVaccinatedList = new ArrayList<>();
        Date date = startDate;

        List<VaccineAdministration> listVA = vaccineAdministrationListBetweenTwoDates(startDate, endDate);

        do {
            int totalFullyVaccinated = 0;

            for (SNSUser su : listSU) {
                for (Vaccine v : listV) {
                    int totalVaccineAdministration = totalVaccineAdministration(listVA, su, v, date, vc);
                    if (snsUserTotalVaccinated(su, v, totalVaccineAdministration)) {
                        totalFullyVaccinated++;
                    }
                }
            }

            totalFullyVaccinatedList.add(totalFullyVaccinated);
            date.addOneDay();

        } while (endDate.isMajorOrEquals(date));

        return totalFullyVaccinatedList;
    }

    /**
     * Function to return the total number of vaccines administered to an SNS user.
     *
     * @param su   the vaccine administration SNS user.
     * @param v    the vaccine administration vaccine.
     * @param date the vaccine administration date.
     * @param vc   the vaccine administration vaccination center.
     * @return total number of vaccines administered to an SNS user.
     */
    private int totalVaccineAdministration(List<VaccineAdministration> listVA, SNSUser su, Vaccine v, Date date, VaccinationCenter vc) {
        int total = 0;

        for (VaccineAdministration list : listVA) {
            if (list.getSnsUser().equals(su) && list.getVaccine().equals(v)
                    && date.isMajorOrEquals(list.getAdministrationDate())
                    && list.getVaccinationCenter().equals(vc)) {
                total++;
            }
        }

        return total;
    }

    /**
     * Function to check if an SNS user is fully vaccinated.
     *
     * @param su                         the vaccine administration SNS user.
     * @param v                          the vaccine administration vaccine.
     * @param totalVaccineAdministration the total number of vaccines administered.
     * @return true if the SNS user is fully vaccinated. Otherwise, returns false.
     */
    private boolean snsUserTotalVaccinated(SNSUser su, Vaccine v, int totalVaccineAdministration) {
        boolean snsUserTotalVaccinated = false;

        for (AgeGroup ag : v.getAgList()) {
            int age = Date.currentAge(su.getBirthdate());

            if (age >= ag.getMinimumAge() && age <= ag.getMaximumAge()) {
                snsUserTotalVaccinated = ag.getDoseList().size() == totalVaccineAdministration;
            }
        }

        return snsUserTotalVaccinated;
    }


    /**
     * Function that returns the vaccine administration list between two dates.
     *
     * @param startDate the start date.
     * @param endDate   the end date.
     * @return the vaccine administration
     */
    private List<VaccineAdministration> vaccineAdministrationListBetweenTwoDates(Date startDate, Date endDate) {
        List<VaccineAdministration> listVA = new ArrayList<>();

        this.listVA = this.loadVaccineAdministrationListFromFile();

        for (VaccineAdministration va : listVA) {
            if (va.getAdministrationDate().isMajorOrEquals(startDate) && endDate.isMajorOrEquals(va.getAdministrationDate())) {
                listVA.add(va);
            }
        }
        return listVA;
    }

    /**
     * Method to add items to the temporary list.
     *
     * @param sU The SNS User.
     * @param v The vaccine.
     * @param dose    The dose.
     * @param lSF     The LegacySystemFile;
     * @param vc The vaccination center.
     * @return true if successfully adds the data, or false otherwise.
     */
    public boolean createLegacySystemTemporaryData(SNSUser sU, Vaccine v, Dose dose, LegacySystemFile lSF,
                                                   VaccinationCenter vc) {
        VaccineAdministration vaccineAdministration = this.instantiateVaccineAdministration(sU, v, dose,
                lSF.getNurseAdministrationDate(), lSF.getNurseAdministrationTime(), lSF.getLeavingDate(),
                lSF.getLeavingTime(), vc, lSF.getLotNumber());

        if (!this.validateVaccineAdministration(vaccineAdministration)) {
            return false;
        }

        return this.tempListVA.add(vaccineAdministration);
    }

    /**
     * Method to save and persist the data recorded in the temporary list.
     */
    public void saveLegacySystemTemporaryData() {

        if (this.tempListVA.size() > 0) {
            for (VaccineAdministration va : this.tempListVA) {
                this.saveVaccineAdministration(va);
            }

            this.saveVaccineAdministrationListInFile();

            this.cleanTempList();
        }

    }

    /**
     * Cleans the temporary list.
     */
    public void cleanTempList() {
        this.tempListVA = new ArrayList<>();
    }

    /**
     * Function to send a message regarding the end of the recovery time.
     *
     * @param phoneNumber the phone number.
     * @param message     the message to send.
     * @param delay       The message delay.
     */
    public void sendMessage(String phoneNumber, String message, int delay) {
        SMSMessageTask task = new SMSMessageTask(phoneNumber, message);

        Timer timer = new Timer();
        timer.schedule(task, delay);
    }

    /**
     * Function to return the list of fully vaccinated by vaccination center.
     *
     * @param listSU the SNS user list.
     * @param listV  the vaccine list.
     * @param listVC the vaccination center list.
     * @param date   the date.
     * @return the list of fully vaccinated.
     */
    public List<FullVaccinated> getFullVaccinatedPerCenter(List<SNSUser> listSU, List<Vaccine> listV, List<VaccinationCenter> listVC, Date date) {
        List<FullVaccinated> fullVaccinatedList = new ArrayList<>();

        for (VaccinationCenter vc : listVC) {
            int numberOfVaccinated = totalNumberFullyVaccinatedInDay(listSU, listV, date, vc);
            fullVaccinatedList.add(new FullVaccinated(date, vc.getName(), numberOfVaccinated));
        }

        return fullVaccinatedList;
    }
}
