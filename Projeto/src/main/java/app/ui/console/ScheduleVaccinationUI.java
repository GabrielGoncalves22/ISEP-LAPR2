package app.ui.console;

import app.core.controller.ScheduleVaccinationController;
import app.core.domain.model.*;
import app.core.dto.ScheduleVaccineDTO;
import app.ui.console.utils.Utils;

import java.util.List;

/**
 * This class is the user interface the receptionist will interact to schedule a vaccine.
 *
 * @author Group 40
 */
public class ScheduleVaccinationUI implements Runnable {
    @Override
    public void run() {
        /**
         * This variable represents the controller.
         */
        ScheduleVaccinationController controller = new ScheduleVaccinationController();

        /**
         * This variable holds the SNS user number.
         */
        String snsUserNumber = "";
        boolean state = false;

        while (!state) {
            snsUserNumber = Utils.readLineFromConsole("Insert SNS User Number: ");
            if (snsUserNumber.matches("[0-9]+") && snsUserNumber.length() == 9) {
                state = true;
            }
        }

        /**
         * This variable holds a sns user.
         */
        SNSUser su = controller.createSNSUser(snsUserNumber);

        if (su != null) {
            /**
             * This variable holds the list of the vaccination center.
             */
            List<VaccinationCenter> vcl = controller.getVaccinationCenterList();

            if (!vcl.isEmpty()) {
                /**
                 * This variable holds a vacination center were the sns user is trying to shedule the vaccine.
                 */
                VaccinationCenter vc = (VaccinationCenter) Utils.showAndSelectOne(vcl, "Select Vaccination center");

                if (vc != null) {

                    try {

                        /**
                         * This variable holds a date in string format.
                         */
                        String stringDate = Utils.readLineFromConsole("Insert intended date (dd/mm/yyyy):");

                        while (stringDate.equals("") || !Date.validateDateMeaning(stringDate) || !Date.validateGreaterThanEqual(stringDate)) {
                            System.out.println("Insert date is invalid!!");
                            stringDate = Utils.readLineFromConsole("Insert intended date (dd/mm/yyyy):");
                        }

                        /**
                         * This variable holds a date in Date format.
                         */
                        Date date = Date.convertStringToDate(stringDate);

                        System.out.println("Insert intended time:");

                        /**
                         * This variable holds a number representing the hours.
                         */
                        int hours = Utils.readIntegerFromConsole("Insert hours: ");
                        /**
                         * This variable holds a number representing the minutes.
                         */
                        int minutes = Utils.readIntegerFromConsole("Insert minutes: ");
                        /**
                         * This variable holds a time in Time format.
                         */
                        Time time = new Time(hours, minutes);

                        if (controller.verifySlotAvailability(vc, date, time)) {

                            /**
                             * This variable holds the list of the vaccine types.
                             */
                            List<VaccineType> vtl = controller.getVaccineTypeList();

                            if (!vtl.isEmpty()) {
                                /**
                                 * This variable holds type of vaccine.
                                 */
                                VaccineType vt = (VaccineType) Utils.showAndSelectOne(vtl, "Select Vaccination type");

                                if (vt != null) {
                                    controller.createScheduleVaccination(new ScheduleVaccineDTO(su, vc, date, time, vt));

                                    printData(snsUserNumber, date, time, vt);
                                    askForConfirmation(controller, su, date, time, vc);
                                } else {
                                    System.out.println("The desired type of vaccine was not selected.");
                                }

                            } else {
                                System.out.println("There are no registered types of vaccine.");
                            }

                        } else {
                            System.out.println("Cannot schedule for this date and time.");
                        }

                    } catch (IllegalArgumentException exception) {
                        System.out.println(exception.getMessage());
                    }

                } else {
                    System.out.println("The intended vaccination center was not selected.");
                }
            } else {
                System.out.println("There are no registered vaccination centers.");
            }

        } else {
            System.out.println("The SNS user number indicated does not correspond to any registered user.");
        }
    }

    /**
     * This function prints the data inputed by the receptionist.
     *
     * @param snsUserNumber holds a sns user whose trying to schedule a vaccine.
     * @param date          holds the intended schedule vaccine date.
     * @param time          holds the intended schedule vaccine time.
     * @param vt            holds the type of vaccine.
     */
    public void printData(String snsUserNumber, Date date, Time time, VaccineType vt) {
        System.out.println("");
        System.out.println("Confirm the data:");
        System.out.println("SNSUser: " + snsUserNumber);
        System.out.println("Date: " + date.toString());
        System.out.println("Time: " + time);
        System.out.println("Vacine type: " + vt);
    }

    /**
     * This function asks for confirmation, if aproved it saves the schedule vaccine and creates SMS message in txt format.
     *
     * @param controller the schedule vaccine controller.
     * @param date       holds the intended schedule vaccine date.
     * @param time       holds the intended schedule vaccine time.
     * @param vc         holds a vacination center were the sns user is trying to shedule the vaccine.
     */
    public void askForConfirmation(ScheduleVaccinationController controller, SNSUser su, Date date, Time time, VaccinationCenter vc) {
        if (Utils.confirm("Is the data correct? (Y/N)")) {
            controller.saveScheduleVacination();
            String message = "In " + date.toString() + " at " + time.toStringHHMMSS() + " at vacination center located in " + vc.getAddress() + ".";

            new FileSMSMessage().sendSMSMessage(su.getPhoneNumber(), message);
            System.out.println("Operation complete");
        } else {
            System.out.println("Operation incomplete, please try again");
        }
    }

}
