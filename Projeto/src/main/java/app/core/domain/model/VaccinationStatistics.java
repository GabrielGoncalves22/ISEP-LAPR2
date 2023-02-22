package app.core.domain.model;

/**
 * Represents the vaccine statistics controller.
 *
 * @author Group 40
 */
public class VaccinationStatistics {

    /**
     * This variable holds the first date of an interval.
     */
    private Date date1;

    /**
     * This variable holds the second date of an interval.
     */
    private Date date2;

    /**
     * Builds a vaccination statistics instance by receiving two dates.
     *
     * @param date1 the first date of an interval.
     * @param date2 the second date of an interval.
     */
    public VaccinationStatistics(Date date1, Date date2) {
        this.date1 = date1;
        this.date2 = date2;
    }

    /**
     * This function verifys if two dates in a date interval are equal, if the first date is bigger than the second and if both dates happened before the current date.
     *
     * @return boolean state true if the interval is valid, return false if the interval is not.
     */
    public boolean verifyDateInterval() {
        if (this.date1.compareTo(this.date2) < 0 && !this.date1.isMajorOrEquals(Date.currentDate()) && !this.date2.isMajorOrEquals(Date.currentDate())) {
            return true;
        } else {
            return false;
        }
    }
}