package app.core.controller;

import app.core.domain.algorithms.SortDataAlgorithm;

import app.core.domain.model.*;
import app.core.domain.shared.LegacySysFileReaderType1;
import app.core.domain.shared.exception.InvalidDataInLegacySystemFileException;
import app.core.domain.store.LegacySystemFileStore;
import app.core.domain.store.SNSUserStore;
import app.core.domain.store.VaccinationCenterStore;
import app.core.domain.store.VaccineStore;
import app.core.domain.store.*;
import app.core.dto.SNSUserDTO;
import app.core.dto.VaccineDTO;
import app.core.mapper.SNSUserMapper;
import app.core.mapper.VaccineMapper;
import app.ui.console.utils.FileUtils;

import java.io.File;
import java.util.List;

/**
 * Class responsible for coordinating and distributing the actions performed on the ImportManageCenterDataUI.
 *
 * @author Group 40
 */
public class ImportManageCenterDataController {

    /**
     * Represents the company.
     */
    private Company company;

    /**
     * Represents the extensions the file can have to perform the import of data.
     */
    private final String[] SUPPORTED_EXTENSIONS = {"csv"};

    /**
     * The vaccination center store.
     */
    private VaccinationCenterStore vcStore;

    /**
     * The vaccination center.
     */
    private VaccinationCenter vc;

    /**
     * Builds an instance of ImportManageCenterDataController.
     *
     * @param vcId the id of the vaccination center.
     */
    public ImportManageCenterDataController(int vcId) {
        this(App.getInstance().getCompany());
        this.vcStore = company.getVaccinationCenterStore();
        this.vc = vcStore.getVaccinationCenter(vcId);
    }

    /**
     * Builds an instance of ImportManageCenterDataController receiving the company as parameter.
     *
     * @param company The company.
     */
    public ImportManageCenterDataController(Company company) {
        this.company = company;
    }

    /**
     * Method to validate the file present in a given path.
     *
     * @param filePath The path to the file where the data is stored.
     * @return A String reporting some error or an empty String if no errors are found
     */
    public String validateFile(String filePath) {
        File file = new File(filePath);
        return FileUtils.validateFile(file, this.SUPPORTED_EXTENSIONS);
    }

    /**
     * Method to read the data to be imported from a file.
     *
     * @param filePath The path to the file.
     * @return The list with the data present in the file.
     */
    public List<LegacySystemFile> readFileData(String filePath) {
        List<List<String>> fileData = new LegacySysFileReaderType1().readDataToStringList(filePath);

        SNSUserStore sUStore = this.company.getSNSUserStore();
        List<SNSUser> sUList = sUStore.getSNSUserList();
        List<SNSUserDTO> suListDto = SNSUserMapper.toListDTO(sUList);

        VaccineStore vStore = this.company.getVaccineStore();
        List<Vaccine> listV = vStore.getListV();
        List<VaccineDTO> listVDto = VaccineMapper.toListDTO(listV);

        LegacySystemFileStore lSFStore = this.company.getLegacySystemFileStore();
        lSFStore.storeData(fileData, suListDto, listVDto);

        return lSFStore.getlSFList();

    }

    /**
     * Method to sort the list stored in the LegacySystemFileStore following a certain criteria.
     *
     * @param sortingCriteria The sorting criteria.
     * @return The list sorted by the criteria.
     */
    public List<LegacySystemFile> sortDataByCriteria(String sortingCriteria) {
        LegacySystemFileStore lSFStore = this.company.getLegacySystemFileStore();
        List<LegacySystemFile> lSFList = lSFStore.getlSFList();
        boolean exception = false;
        SortDataAlgorithm sortDataAlgorithm = null;
        try {
            sortDataAlgorithm = this.company.getSortDataAlgorithm(sortingCriteria);
        } catch (Exception ex) {
            exception = true;
        }

        if (exception) {
            throw new IllegalArgumentException("Something went wrong. Please try again later.");
        }

        lSFStore.changeSortOrder(LegacySystemFileStore.ORDER_ASC);

        return sortDataAlgorithm.sortData(lSFList);
    }

    /**
     * Method to change the order of the list stored in the LegacySystemFileStore.
     *
     * @param order The needed order.
     * @return The list ordered.
     */
    public List<LegacySystemFile> changeSortOrder(String order) {
        LegacySystemFileStore lSFStore = this.company.getLegacySystemFileStore();
        lSFStore.changeSortOrder(order);
        return lSFStore.getlSFList();
    }

    /**
     * Import the data present in the LegacySystemFileStore.
     */
    public void importDataToTheSystem() {
        LegacySystemFileStore lSFStore = this.company.getLegacySystemFileStore();
        SNSUserStore sUStore = this.company.getSNSUserStore();
        VaccineStore vStore = this.company.getVaccineStore();
        VaccineTypeStore vTStore = this.company.getVaccineTypeStore();
        VaccineAdministrationStore vaStore = this.company.getVaccineAdministrationStore();
        ScheduleVaccineStore sVStore = this.company.getScheduleVaccineStore();
        ArrivalSNSUserStore aSUStore = this.company.getArrivalSNSUserStore();

        List<LegacySystemFile> lSFList = lSFStore.getlSFList();
        int line = 2;

        for (LegacySystemFile lSF : lSFList) {

            boolean success;

            SNSUser sU = sUStore.getSNSUser(lSF.getSnsUserDTO().getSnsUserNumber());
            Vaccine v = vStore.getVaccine(lSF.getVaccineDTO().getId());
            VaccineType vt = vTStore.getVaccineTypeByCode(lSF.getVaccineDTO().getVtDto().getCode());

            // Vaccine administration
            int age = Date.yearsBetweenTwoDates(sU.getBirthdate(), lSF.getScheduledDate());
            Dose dose = vStore.getDose(v, age, Dose.mapDoseValue(lSF.getDose()));
            try {
                success = vaStore.createLegacySystemTemporaryData(sU, v, dose, lSF, vc);
                this.throwExceptionIfNotSuccess(success, line, "Invalid vaccine administration data.");

                // Schedule Vaccine
                success = sVStore.createLegacySystemTemporaryData(lSF, sU, this.vc, vt);
                this.throwExceptionIfNotSuccess(success, line, "Invalid schedule vaccine data.");

                // Arrival SNS User
                success = aSUStore.createLegacySystemTemporaryData(sU, lSF, this.vc);
                this.throwExceptionIfNotSuccess(success, line, "Invalid arrival data.");
            } catch (IllegalArgumentException ex) {
                aSUStore.cleanTempList();
                vaStore.cleanTempList();
                sVStore.cleanTempList();
                throw new InvalidDataInLegacySystemFileException(line, ex.getMessage());
            }

            line++;
        }

        vaStore.saveLegacySystemTemporaryData();
        sVStore.saveLegacySystemTemporaryData();
        aSUStore.saveLegacySystemTemporaryData();

    }

    /**
     * Method to throw a new InvalidDataInLegacySystemFileException if something is invalid in the data of the file.
     *
     * @param valid    Holds the boolean value to know if the operation was valid.
     * @param fileLine The line of the file where the error was found.
     * @param message  The message to be thrown.
     */
    private void throwExceptionIfNotSuccess(boolean valid, int fileLine, String message) {
        if (!valid) {
            throw new InvalidDataInLegacySystemFileException(fileLine, message);
        }
    }

}
