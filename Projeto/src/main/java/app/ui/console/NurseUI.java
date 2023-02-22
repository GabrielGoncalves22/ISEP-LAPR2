package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class NurseUI implements Runnable {

    public NurseUI() {
    }

    public void run() {
        SelectVaccinationCenterUI selectVaccinationCenterUI = new SelectVaccinationCenterUI();
        selectVaccinationCenterUI.run();
        int vcId = selectVaccinationCenterUI.getSelectedVcId();

        if (vcId != -1) {
            List<MenuItem> options = new ArrayList<>();
            options.add(new MenuItem("Consult the users in the waiting room", new ListArrivalSNSUserUI(vcId)));

            int option;
            do {
                option = Utils.showAndSelectIndex(options, "\n*** Nurse Menu ***");

                if ((option >= 0) && (option < options.size())) {
                    options.get(option).run();
                }
            } while (option != -1);
        }
    }
}
