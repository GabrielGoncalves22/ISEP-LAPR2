package app.core.domain.store;

import app.core.domain.model.Date;
import app.core.domain.model.LegacySystemFile;
import app.core.domain.model.Time;
import app.core.domain.shared.exception.InvalidDataInLegacySystemFileException;
import app.core.domain.shared.exception.InvalidDateException;
import app.core.domain.shared.exception.InvalidTimeException;
import app.core.dto.SNSUserDTO;
import app.core.dto.VaccineDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the Legacy System File Store.
 *
 * @author Group 40
 */
public class LegacySystemFileStore {

    /**
     * The list containing data from a legacy system file.
     */
    private List<LegacySystemFile> lSFList = new ArrayList<>();

    /**
     * Holds the text representing the ascending order.
     */
    public static final String ORDER_ASC = "asc";

    /**
     * Holds the text representing the descending order.
     */
    public static final String ORDER_DESC = "desc";

    /**
     * Holds the actual order of the list.
     */
    public static String order = LegacySystemFileStore.ORDER_ASC;

    /**
     * Method to store data from a file which comes in a format of a list inside a list. It's required the SNS User DTO
     * list and vaccine DTO list too, so it is possible to link the data with the required information.
     *
     * @param fileData  The data read in a file.
     * @param sUListDto The list of SNS Users DTO.
     * @param listVDto  The list of vaccines DTO.
     */
    public void storeData(List<List<String>> fileData, List<SNSUserDTO> sUListDto, List<VaccineDTO> listVDto) {

        this.lSFList = new ArrayList<>();
        int line = 2;

        for (List<String> data : fileData) {
            // The SNS User
            String snsUserNumber = data.get(0);
            SNSUserDTO sUDto = this.findSUBySUNumber(snsUserNumber, sUListDto);

            // According to the client clarification, we cannot discard the file when an SNS User doesn't exist
            if (sUDto != null) {

                // The vaccine
                String vName = data.get(1);
                VaccineDTO vDto = findVaccineByName(vName, listVDto);
                if (vDto == null) {
                    throw new InvalidDataInLegacySystemFileException(line, "The vaccine doesn't exists!");
                }

                // The dose
                String dose = data.get(2);

                // The lot number
                String lotNumber = data.get(3);

                // The scheduled
                String[] scheduledData = data.get(4).split(" ");
                // The arrival date
                Date scheduledDate = null;
                try {
                    scheduledDate = this.transformDate(scheduledData[0]);
                } catch (ArrayIndexOutOfBoundsException | InvalidDateException ex) {
                    throw new InvalidDataInLegacySystemFileException(line, "The scheduled date is invalid!");
                }

                // The arrival time
                Time scheduledTime = null;
                try {
                    scheduledTime = this.transformTime(scheduledData[1]);
                } catch (ArrayIndexOutOfBoundsException | InvalidTimeException ex) {
                    throw new InvalidDataInLegacySystemFileException(line, "The scheduled time is invalid!");
                }

                // The arrival
                String[] arrivalData = data.get(5).split(" ");
                // The arrival date
                Date arrivalDate = null;
                try {
                    arrivalDate = this.transformDate(arrivalData[0]);
                } catch (ArrayIndexOutOfBoundsException | InvalidDateException ex) {
                    throw new InvalidDataInLegacySystemFileException(line, "The arrival date is invalid!");
                }

                // The arrival time
                Time arrivalTime = null;
                try {
                    arrivalTime = this.transformTime(arrivalData[1]);
                } catch (ArrayIndexOutOfBoundsException | InvalidTimeException ex) {
                    throw new InvalidDataInLegacySystemFileException(line, "The arrival time is invalid!");
                }

                // The nurse administration
                String[] nurseAdministrationData = data.get(6).split(" ");

                // The nurse administration date
                Date nurseAdministrationDate = null;
                try {
                    nurseAdministrationDate = this.transformDate(nurseAdministrationData[0]);
                } catch (ArrayIndexOutOfBoundsException | InvalidDateException ex) {
                    throw new InvalidDataInLegacySystemFileException(line, "The arrival date is invalid!");
                }

                // The nurse administration time
                Time nurseAdministrationTime = null;
                try {
                    nurseAdministrationTime = this.transformTime(nurseAdministrationData[1]);
                } catch (ArrayIndexOutOfBoundsException | InvalidTimeException ex) {
                    throw new InvalidDataInLegacySystemFileException(line, "The arrival time is invalid!");
                }

                // The leaving
                String[] leavingDT = data.get(7).split(" ");
                // The leaving date
                Date leavingDate = null;
                try {
                    leavingDate = this.transformDate(leavingDT[0]);
                } catch (ArrayIndexOutOfBoundsException | InvalidDateException ex) {
                    throw new InvalidDataInLegacySystemFileException(line, "The leaving date is invalid!");
                }

                // The leaving time
                Time leavingTime = null;
                try {
                    leavingTime = this.transformTime(leavingDT[1]);
                } catch (ArrayIndexOutOfBoundsException | InvalidTimeException ex) {
                    throw new InvalidDataInLegacySystemFileException(line, "The leaving time is invalid!");
                }

                LegacySystemFile lsF = this.createLegacySystemFile(sUDto, vDto, dose, lotNumber, scheduledDate,
                        scheduledTime, arrivalDate, arrivalTime, nurseAdministrationDate, nurseAdministrationTime,
                        leavingDate, leavingTime);

                this.saveLegacySystemFile(lsF);
            }

            line++;
        }

    }

    /**
     * Method to transform the data from legacy system into the current data format.
     *
     * @param dateString The String containing the date in format mm/dd/yyyy
     * @return A Date object.
     */
    public Date transformDate(String dateString) {
        Date date = null;

        String[] d = dateString.split("/");

        try {
            date = new Date(Integer.parseInt(d[2]), Integer.parseInt(d[0]), Integer.parseInt(d[1]));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new InvalidDateException(dateString);
        }

        return date;
    }

    /**
     * Method to transform a String into a Time object.
     *
     * @param timeString The String containing the time.
     * @return The Time object created.
     */
    public Time transformTime(String timeString) {
        Time time = null;

        String[] t = timeString.split(":");
        try {
            time = new Time(Integer.parseInt(t[0]), Integer.parseInt(t[1]));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            throw new InvalidTimeException(timeString);
        }

        return time;
    }

    /**
     * Method to return all the LegacySystemFile objects presents in the list.
     *
     * @return The LegacySystemFile list.
     */
    public List<LegacySystemFile> getlSFList() {
        return lSFList;
    }

    /**
     * Method to find the SNS User by its SNS user number, in a DTO list of SNS Users.
     *
     * @param snsUserNumber The SNS user number.
     * @param sUListDto     The DTO list with all the SNS Users.
     * @return The SNSUserDTO with the given SNS User number. If the SNS User with the required specifications doesn't
     * exist, its returned null instead.
     */
    private SNSUserDTO findSUBySUNumber(String snsUserNumber, List<SNSUserDTO> sUListDto) {

        SNSUserDTO sUDto = null;
        int i = 0;
        boolean found = false;

        while (i < sUListDto.size() && !found) {
            sUDto = sUListDto.get(i);
            if (sUDto.getSnsUserNumber().equals(snsUserNumber)) {
                found = true;
            }
            i++;
        }

        return sUDto;

    }

    /**
     * Method to find the vaccine by its name, in a DTO list of vaccines.
     *
     * @param vName    The vaccine name.
     * @param listVDto The DTO list with all the vaccines.
     * @return The VaccineDTO with the given name. If the vaccine with the required specifications doesn't exist, its
     * returned null instead.
     */
    private VaccineDTO findVaccineByName(String vName, List<VaccineDTO> listVDto) {

        VaccineDTO vDto = null;
        int i = 0;
        boolean found = false;

        while (i < listVDto.size() && !found) {
            vDto = listVDto.get(i);
            if (vDto.getName().equals(vName)) {
                found = true;
            }
            i++;
        }

        return vDto;

    }

    /**
     * This method creates a new instance of an LegacySystemFile.
     *
     * @param sUDto                   The SNS User DTO.
     * @param vDto                    The vaccine DTO.
     * @param dose                    The dose.
     * @param lotNumber               The lot number.
     * @param scheduledDate           The scheduled date.
     * @param scheduledTime           The scheduled time.
     * @param arrivalDate             The SNS User arrival date.
     * @param arrivalTime             The SNS User arrival time.
     * @param nurseAdministrationDate The date when the nurse administered the vaccine to the SNS User.
     * @param nurseAdministrationTime The time when the nurse administered the vaccine to the SNS User.
     * @param leavingDate             The leaving date when the SNS User leave the center.
     * @param leavingTime             The leaving time when the SNS User leave the center.
     * @return A new instance of an LegacySystemFile.
     */
    public LegacySystemFile createLegacySystemFile(SNSUserDTO sUDto, VaccineDTO vDto, String dose, String lotNumber,
                                                   Date scheduledDate, Time scheduledTime, Date arrivalDate,
                                                   Time arrivalTime, Date nurseAdministrationDate,
                                                   Time nurseAdministrationTime, Date leavingDate,
                                                   Time leavingTime) {
        return new LegacySystemFile(sUDto, vDto, dose, lotNumber, scheduledDate, scheduledTime, arrivalDate,
                arrivalTime, nurseAdministrationDate, nurseAdministrationTime, leavingDate, leavingTime);
    }

    /**
     * Method to save a LegacySystemFile in the list.
     *
     * @param lSF The LegacySystemFile data.
     * @return true if the operation is successfully made, or false otherwise.
     */
    public boolean saveLegacySystemFile(LegacySystemFile lSF) {
        return this.lSFList.add(lSF);
    }

    /**
     * Method to change the order of the list present in this store.
     *
     * @param order The needed order.
     */
    public void changeSortOrder(String order) {
        this.validateOrder(order);
        if (!order.equalsIgnoreCase(LegacySystemFileStore.order)) {
            setOrder(order);
            Collections.reverse(this.lSFList);
        }
    }

    /**
     * Method to validate the specified order.
     *
     * @param order The specified order.
     */
    private void validateOrder(String order) {
        if (!order.equalsIgnoreCase(ORDER_ASC) && !order.equalsIgnoreCase(ORDER_DESC)) {
            throw new IllegalArgumentException("Invalid order");
        }
    }

    /**
     * Method to change the value of the variable that holds the actual order of the array.
     *
     * @param order The new order.
     */
    public static void setOrder(String order) {
        LegacySystemFileStore.order = order.toLowerCase();
    }

}
