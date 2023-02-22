package app.core.domain.shared;

import app.core.domain.model.Date;
import app.core.domain.model.LegacySystemFile;
import app.core.domain.model.Time;

/**
 * This class helps to handle the sort criteria required methods and values.
 *
 * @author Group 40
 */
public class SortDataCriteria {

    /**
     * Holds the arrival criteria identifier.
     */
    public static final String ARRIVAL_CRITERIA = "arrival";

    /**
     * Holds the leaving criteria identifier.
     */
    public static final String LEAVING_CRITERIA = "leaving";

    /**
     * Holds the actual sort criteria.
     */
    private String sortCriteria;

    /**
     * Builds an instance of SortDataCriteria receiving the sort criteria for this instance.
     *
     * @param sortCriteria The sort criteria.
     */
    public SortDataCriteria(String sortCriteria) {
        this.validateSortCriteria(sortCriteria);
        this.sortCriteria = sortCriteria;
    }

    /**
     * Method to validate the given sort criteria.
     *
     * @param sortCriteria The sort criteria.
     */
    private void validateSortCriteria(String sortCriteria) {
        if (!sortCriteria.equals(ARRIVAL_CRITERIA) && !sortCriteria.equals(LEAVING_CRITERIA)) {
            throw new IllegalArgumentException("Sort criteria is invalid!");
        }
    }

    /**
     * Method to get the date from the LegacySystemFile object from the required data attribute.
     *
     * @param lSF The LegacySystemFile object.
     * @return The date object for the needed attribute data present in the LegacySystemFile object.
     */
    public Date getDateValue(LegacySystemFile lSF) {

        Date date = null;

        switch (sortCriteria) {
            case ARRIVAL_CRITERIA:
                date = lSF.getArrivalDate();
                break;
            case LEAVING_CRITERIA:
                date = lSF.getLeavingDate();
                break;
        }

        return date;

    }

    /**
     * Method to get the time from the LegacySystemFile object from the required data attribute.
     *
     * @param lSF The LegacySystemFile object.
     * @return The time object for the needed attribute data present in the LegacySystemFile object.
     */
    public Time getTimeValue(LegacySystemFile lSF) {

        Time time = null;

        switch (sortCriteria) {
            case ARRIVAL_CRITERIA:
                time = lSF.getArrivalTime();
                break;
            case LEAVING_CRITERIA:
                time = lSF.getLeavingTime();
                break;
        }

        return time;

    }

}
