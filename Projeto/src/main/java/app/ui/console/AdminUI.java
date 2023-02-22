package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio pam@isep.ipp.pt
 */

public class AdminUI implements Runnable {

    public AdminUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();
        options.add(new MenuItem("Register a vaccination center", new RegisterVaccinationCenterUI()));
        options.add(new MenuItem("Register an employee", new RegisterEmployeeUI()));
        options.add(new MenuItem("List of employees", new ListEmployeeUI()));
        options.add(new MenuItem("Specify a new vaccine type", new SpecifyNewVaccineTypeUI()));
        options.add(new MenuItem("Specify a new vaccine and its administration process", new SpecifyNewVaccineUI()));
        options.add(new MenuItem("Load file with SNS Users data", new LoadFileUI()));

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n*** Admin Menu ***");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
