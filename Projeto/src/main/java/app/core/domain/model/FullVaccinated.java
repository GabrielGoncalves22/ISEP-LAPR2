package app.core.domain.model;

/**
 * It represents a FullVaccinated through current date, name center vaccination and number of vaccinated.
 *
 * @author Group 40
 */
public class FullVaccinated {
    private Date currentDate;
    private String nameCenterVaccination;
    private int numberOfVaccinated;

    /**
     * Instantiates a new FullVaccinated.
     *
     * @param currentDate           the current date.
     * @param nameCenterVaccination the name of the vaccination center.
     * @param numberOfVaccinated    the number of vaccinated.
     */
    public FullVaccinated(Date currentDate, String nameCenterVaccination, int numberOfVaccinated) {
        this.currentDate = currentDate;
        this.nameCenterVaccination = nameCenterVaccination;
        this.numberOfVaccinated = numberOfVaccinated;
    }

    /**
     * Function to return the current date.
     *
     * @return the current date.
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * Function to return the name of the vaccination center.
     *
     * @return the name of the vaccination center.
     */
    public String getNameCenterVaccination() {
        return nameCenterVaccination;
    }

    /**
     * Function to return the number of vaccinated.
     *
     * @return the number of vaccinated.
     */
    public int getNumberOfVaccinated() {
        return numberOfVaccinated;
    }
}
