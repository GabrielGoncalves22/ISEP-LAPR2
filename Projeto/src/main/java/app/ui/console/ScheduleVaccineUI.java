package app.ui.console;

import app.core.controller.ScheduleVaccineController;
import app.core.domain.model.*;
import app.core.dto.ScheduleVaccineDTO;
import app.ui.console.utils.Utils;

import java.util.List;

public class ScheduleVaccineUI implements Runnable {
    @Override
    public void run() {
        ScheduleVaccineController controller = new ScheduleVaccineController();

        String snsUserNumber = "";
        boolean state = false;

        while (!state) {
            snsUserNumber = Utils.readLineFromConsole("Insert SNS User Number: ");
            if (snsUserNumber.matches("[0-9]+") && snsUserNumber.length() == 9) {
                state = true;
            }
        }

        SNSUser su = controller.createSNSUser(snsUserNumber);

        if (su != null) {
            List<VaccinationCenter> vcl = controller.getVaccinationCenterList();

            if (!vcl.isEmpty()) {
                VaccinationCenter vc = (VaccinationCenter) Utils.showAndSelectOne(vcl, "Select Vaccination center");

                if (vc != null) {

                    try {
                        String stringDate = Utils.readLineFromConsole("Insert intended date (dd/mm/yyyy):");

                        while (stringDate.equals("") || !Date.validateDateMeaning(stringDate) || !Date.validateGreaterThanEqual(stringDate)) {
                            System.out.println("Insert date is invalid!!");
                            stringDate = Utils.readLineFromConsole("Insert intended date (dd/mm/yyyy):");
                        }

                        Date date = Date.convertStringToDate(stringDate);

                        System.out.println("Insert intended time:");
                        int hours = Utils.readIntegerFromConsole("Insert hours: ");
                        int minutes = Utils.readIntegerFromConsole("Insert minutes: ");
                        Time time = new Time(hours, minutes);

                        if (controller.verifySlotAvailability(vc, date, time)) {

                            List<VaccineType> vtl = controller.getVaccineTypeList();

                            if (!vtl.isEmpty()) {
                                VaccineType vt = (VaccineType) Utils.showAndSelectOne(vtl, "Select Vaccination type");

                                if (vt != null) {
                                    controller.createScheduleVaccine(new ScheduleVaccineDTO(su, vc, date, time, vt));

                                    printData(snsUserNumber, date, time);
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

    public void printData(String snsUserNumber, Date date, Time time) {
        System.out.println("");
        System.out.println("Confirm the data:");
        System.out.println("SNS user number: " + snsUserNumber);
        System.out.println("Date: " + date.toString());
        System.out.println("Time: " + time);

    }

    public void askForConfirmation(ScheduleVaccineController controller, SNSUser su, Date date, Time time, VaccinationCenter vc) {
        if (Utils.confirm("Is the data correct? (Y/N)")) {
            controller.saveScheduleVacine();
            String message = "In " + date.toString() + " at " + time.toStringHHMMSS() + " at vacination center located in " + vc.getAddress() + ".";

            new FileSMSMessage().sendSMSMessage(su.getPhoneNumber(), message);
            System.out.println("Operation complete");
        } else {
            System.out.println("Operation incomplete, please try again");
        }
    }

}


