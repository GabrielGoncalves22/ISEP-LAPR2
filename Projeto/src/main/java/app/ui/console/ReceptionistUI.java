package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistUI implements Runnable {

    public ReceptionistUI() {
    }

    public void run() {
        SelectVaccinationCenterUI selectVaccinationCenterUI = new SelectVaccinationCenterUI();
        selectVaccinationCenterUI.run();
        int vcId = selectVaccinationCenterUI.getSelectedVcId();

        if (vcId != -1) {
            List<MenuItem> options = new ArrayList<>();
            options.add(new MenuItem("Register a SNS user", new RegisterSNSUserUI()));
            options.add(new MenuItem("Register the arrival of a SNS user", new RegisterArrivalSNSUserUI(vcId)));
            options.add(new MenuItem("Schedule a vaccine", new ScheduleVaccinationUI()));

            int option;
            do {
                option = Utils.showAndSelectIndex(options, "\n*** Receptionist Menu ***");

                if ((option >= 0) && (option < options.size())) {
                    options.get(option).run();
                }
            } while (option != -1);
        }
    }
}
