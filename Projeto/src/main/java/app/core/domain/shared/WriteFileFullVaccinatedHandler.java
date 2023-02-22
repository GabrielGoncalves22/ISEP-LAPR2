package app.core.domain.shared;

import app.core.domain.model.FullVaccinated;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * Interface that implements a method  for export full vaccinated to CSV file.
 *
 * @author Group 40
 */
public interface WriteFileFullVaccinatedHandler {

    /**
     * Method for export full vaccinated to CSV file.
     *
     * @param fullVaccinatedList the list of total vaccinated
     * @return true if the file is created successfully. Otherwise false.
     * @throws FileNotFoundException file not found exception
     */
    boolean writeToFileCSV(List<FullVaccinated> fullVaccinatedList) throws FileNotFoundException;
}
