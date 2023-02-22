package app.core.domain.model;

import java.util.List;

/**
 * It represents the analysis of the performance of a vaccination center.
 *
 * @author Group 40
 */
public class AnalyzePerformance {
    private final VaccinationCenter vc;

    /**
     * Instantiates a new AnalyzePerformance.
     *
     * @param vc the vaccination center
     */
    public AnalyzePerformance(VaccinationCenter vc) {
        this.vc = vc;
    }

    /**
     * Function to create an array with the difference of entrances and exits of the vaccination center in certain intervals of time.
     *
     * @param listAU the arrival SNS user type list.
     * @param listVA the vacine administration list.
     * @param m      the interval minute.
     * @return the difference of entrances and exits of the vaccination center in certain intervals of time.
     */
    public int[] createDifferenceList(List<ArrivalSNSUser> listAU, List<VaccineAdministration> listVA, int m) {
        Time openingHours = this.vc.getOpeningHours();
        Time closingHours = this.vc.getClosingHours();

        int totalTime = Time.calculateTimeDifferenceMinute(openingHours, closingHours);
        int[] differenceList = new int[totalTime / m];

        Time tempTime = new Time(openingHours);
        Time startTime = openingHours;
        Time endTime = tempTime.addMinutes(m);

        int contInt;
        int contOut;
        int index = 0;

        do {
            contInt = countEntries(listAU, startTime, endTime);
            contOut = countDepartures(listVA, startTime, endTime);

            differenceList[index] = contInt - contOut;

            tempTime = new Time(endTime);
            startTime = endTime;
            endTime = tempTime.addMinutes(m);

            index++;

        } while (closingHours.isBigger(startTime));

        return differenceList;
    }

    /**
     * Function to construct a string representing the time interval of the maximum sum contiguous sublist.
     *
     * @param intendedDate the intended date.
     * @param m            the interval minute.
     * @return a string representing the time interval of the maximum sum contiguous sublist.
     */
    public String timeInterval(int startIndex, int endIndex, Date intendedDate, int m) {
        Time openingHours = this.vc.getOpeningHours();

        Time tempTime = new Time(openingHours);
        Time startTime = openingHours.addMinutes(m * startIndex);
        Time endTime = tempTime.addMinutes(m * endIndex + m);

        String startString = intendedDate.toDayMonthYearString() + " " + startTime;
        String endString = intendedDate.toDayMonthYearString() + " " + endTime;
        return "[" + startString + ", " + endString + "]";
    }

    /**
     * Function to calculate the number of people who entered a time interval.
     *
     * @param listAU    the arrival SNS user type list.
     * @param startTime the start time.
     * @param endTime   the end time.
     * @return the number of people who entered a time interval.
     */
    private int countEntries(List<ArrivalSNSUser> listAU, Time startTime, Time endTime) {
        int contInt = 0;

        for (ArrivalSNSUser list : listAU) {
            if (list.getArrivalTime().isBiggerOrEquals(startTime) && endTime.isBigger(list.getArrivalTime())) {
                contInt++;
            }
        }

        return contInt;
    }

    /**
     * Function to calculate the number of people who left in a period of time.
     *
     * @param listVA    the arrival SNS user type list.
     * @param startTime the start time
     * @param endTime   the end time
     * @return the number of people who left in a period of time.
     */
    private int countDepartures(List<VaccineAdministration> listVA, Time startTime, Time endTime) {
        int contOut = 0;

        for (VaccineAdministration list : listVA) {
            if (list.getLeavingTime().isBiggerOrEquals(startTime) && endTime.isBigger(list.getLeavingTime())) {
                contOut++;
            }
        }

        return contOut;
    }
}
