package app.ui.console;

import app.core.controller.SelectVaccinationCenterController;
import app.core.dto.VaccinationCenterDTO;
import app.ui.console.utils.Utils;

import java.util.List;

public class SelectVaccinationCenterUI {
    private int vcId;

    SelectVaccinationCenterController controller = new SelectVaccinationCenterController();

    public void run() {
        List<VaccinationCenterDTO> listVCDto = controller.getVaccinationCenterList();

        this.vcId = -1;
        if (listVCDto.isEmpty()) {
            System.out.println("There are no registered vaccination centers, so it is not possible to advance.");
        } else {
            boolean vaccinationCenterExists = false;

            //while (!vaccinationCenterExists) {
            VaccinationCenterDTO vc = (VaccinationCenterDTO) Utils.showAndSelectOne(listVCDto, "Select vaccination center: ");
            if (vc != null) {
                this.vcId = controller.getVaccinationCenterId(vc);
                vaccinationCenterExists = controller.existsVaccinationCenter(this.vcId);
            }
            //}
        }
    }

    public int getSelectedVcId() {
        return vcId;
    }
}
