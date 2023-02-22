package app.core.domain.model;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile {

    /**
     * This function writes to a csv file a date and a corresponding number of fully vaccinated people in that date
     *
     * @param date1              the first date of the interval
     * @param fileName           the name of the that is going to be created with the data
     * @param numberOfVaccinated the number of fully vaccinated people
     * @return boolean state that passes true if the writing and file creation is successfully and passes false if not
     */
    public boolean writeToFile(Date date1, String fileName, List<Integer> numberOfVaccinated) throws FileNotFoundException {
        try {
            FileWriter myWriter = new FileWriter("src/statistics/" + fileName + ".csv");
            for (Integer item : numberOfVaccinated) {
                myWriter.write(date1.toDayMonthYearString() + " ; " + item + "\n");
                date1.addOneDay();
            }

            myWriter.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
