package app.ui.gui;

import app.core.controller.SelectVaccinationCenterController;
import app.core.domain.shared.Constants;
import app.core.dto.VaccinationCenterDTO;
import app.ui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class SelectVaccinationCenterUI implements Initializable {
    private LoginUI loginUI;
    private SelectVaccinationCenterController controller;
    private int vcId;

    @FXML
    private ComboBox<VaccinationCenterDTO> vaccinationCenterCBox;

    @FXML
    private Button button;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        controller = new SelectVaccinationCenterController();
        fillChoiceBox();
    }


    @FXML
    void selectCenter(ActionEvent event) {
        VaccinationCenterDTO vaccinationCenterDTO = vaccinationCenterCBox.getValue();
        if (vaccinationCenterDTO != null) {
            button.setDisable(false);
        }
    }

    @FXML
    void selectVaccinationCenter(ActionEvent event) {
        VaccinationCenterDTO vc = vaccinationCenterCBox.getValue();
        this.vcId = controller.getVaccinationCenterId(vc);
        initializeNextUI();
    }

    @FXML
    void closeProgram(ActionEvent event) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Application", "Action confirmation.", "Do you really want to close the application?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        } else {
            event.consume();
        }
    }

    public void initializeNextUI() {
        String role = this.loginUI.getRoleLogin().getDescription();
        FXMLLoader loader;
        Parent root = null;

        try {
            switch (role) {
                case Constants.ROLE_NURSE:
                    loader = new FXMLLoader(getClass().getResource("/NurseMenu.fxml"));
                    root = loader.load();

                    NurseMenuUI nurseMenuUI = loader.getController();
                    nurseMenuUI.connectParentUI(this);
                    break;
                case Constants.ROLE_CENTER_COORDINATOR:
                    loader = new FXMLLoader(getClass().getResource("/CenterCoordinatorMenu.fxml"));
                    root = loader.load();

                    CenterCoordinatorMenuUI centerCoordinatorMenuUI = loader.getController();
                    centerCoordinatorMenuUI.connectParentUI(this);
                    break;
            }

            Scene scene = new Scene(root, 1100, 900);
            Stage window = MainApp.STAGE;
            window.setScene(scene);
            window.centerOnScreen();
            window.show();
        } catch (IOException ex) {
            AlertUI.createErrorAlert().showAndWait();
        }
    }

    private void fillChoiceBox() {
        List<VaccinationCenterDTO> listVCDto = controller.getVaccinationCenterList();
        vaccinationCenterCBox.getItems().addAll(listVCDto);
    }

    public void associarParentUI(LoginUI loginUI) {
        this.loginUI = loginUI;

        if (this.loginUI.getRoleLogin().getDescription().equals(Constants.ROLE_CENTER_COORDINATOR)) {
            String emailLogin = this.loginUI.getEmail();
            int centerCoordinatorId = controller.getCenterCoordinatorId(emailLogin);
            if (centerCoordinatorId == -1) {
                logoutProgram();
                AlertUI.createAlert(Alert.AlertType.ERROR, "Application", "Error action", "The logged in user cannot access the system!").showAndWait();
            } else {
                this.vcId = controller.getVaccinationCenterIdCenterCoordinator(centerCoordinatorId);
                initializeNextUI();
            }
        }
    }

    public int getSelectedVcId() {
        return vcId;
    }

    public void logoutProgram() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login.fxml")));
            Scene scene = new Scene(root, 400, 550);

            Stage window = MainApp.STAGE;
            window.setScene(scene);
            window.centerOnScreen();
            window.show();
        } catch (IOException ex) {
            AlertUI.createErrorAlert().showAndWait();
        }
    }
}
