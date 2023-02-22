package app.core.domain.model;

import app.core.domain.store.SNSUserStore;
import app.core.domain.store.VaccinationCenterStore;
import app.core.domain.store.VaccineAdministrationStore;
import app.core.domain.store.VaccineStore;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.TimerTask;

/**
 * Represents the class related to the task of export full vaccinated to CSV file.
 */
public class ExportFullVaccinatedCSVTask extends TimerTask {

    /**
     * ExportFullVaccinatedCSVTask class run method
     */
    @Override
    public void run() {
        try {
            List<SNSUser> listSU = new SNSUserStore().getSNSUserList();
            List<Vaccine> listV = new VaccineStore().getListV();
            List<VaccinationCenter> listVC = new VaccinationCenterStore().getVaccinationCenterList();

            Date currentDate = Date.currentDate();
            List<FullVaccinated> fullVaccinatedList = new VaccineAdministrationStore().getFullVaccinatedPerCenter(listSU, listV, listVC, currentDate);

            new WriteFileFullVaccinated().writeToFileCSV(fullVaccinatedList);
        } catch (FileNotFoundException e) {
        }
    }
}
