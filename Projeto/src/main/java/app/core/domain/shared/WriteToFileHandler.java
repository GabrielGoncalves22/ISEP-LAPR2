package app.core.domain.shared;

import app.core.domain.model.Date;

import java.io.FileNotFoundException;
import java.util.List;

public interface WriteToFileHandler {

    boolean writeToFile(Date date1, String fileName, List<Integer> numberOfVaccinated) throws FileNotFoundException;
}
