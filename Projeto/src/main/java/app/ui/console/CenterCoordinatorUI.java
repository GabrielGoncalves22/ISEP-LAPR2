package app.ui.console;

import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CenterCoordinatorUI implements Runnable {

    public CenterCoordinatorUI() {
    }

    public void run() {
        List<MenuItem> options = new ArrayList<>();

        int option;
        do {
            option = Utils.showAndSelectIndex(options, "\n*** Center Coordinator Menu ***");

            if ((option >= 0) && (option < options.size())) {
                options.get(option).run();
            }
        } while (option != -1);
    }
}
