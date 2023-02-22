package app.core.domain.model;

import app.core.domain.shared.WriteFileFullVaccinatedHandler;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Represents the class related to write the list of fully vaccinated in Csv
 */
public class WriteFileFullVaccinated implements WriteFileFullVaccinatedHandler {

    /**
     * Method to write the list of fully vaccinated in Csv
     *
     * @param fullVaccinatedList the list of fully vaccinated
     * @throws FileNotFoundException file not found exception
     */
    @Override
    public boolean writeToFileCSV(List<FullVaccinated> fullVaccinatedList) throws FileNotFoundException {
        try {
            FileWriter myWriter = new FileWriter("src/daily people vaccinated/DailyPeopleVaccinated.csv", true);
            for (FullVaccinated list : fullVaccinatedList) {
                myWriter.write(list.getCurrentDate().toDayMonthYearString() + ";" + list.getNameCenterVaccination()
                        + ";" + list.getNumberOfVaccinated() + "\n");
            }
            myWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
