package app.ui.gui;

import app.ui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class NurseMenuUI implements Initializable {
    private SelectVaccinationCenterUI selectVaccinationCenterUI;

    @FXML
    private AnchorPane anchorPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    void recordVaccineAdministration(ActionEvent event) {
        recordVaccineAdministrationUI();
    }

    @FXML
    void logoutProgram(ActionEvent event) {
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

    @FXML
    void closeProgram(ActionEvent event) {
        Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Application", "Action confirmation.", "Do you really want to close the application?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            System.exit(0);
        } else {
            event.consume();
        }
    }

    public void connectParentUI(SelectVaccinationCenterUI selectVaccinationCenterUI) {
        this.selectVaccinationCenterUI = selectVaccinationCenterUI;
        recordVaccineAdministrationUI();
    }

    public SelectVaccinationCenterUI getSelectVaccinationCenterUI() {
        return selectVaccinationCenterUI;
    }

    public void recordVaccineAdministrationUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/RecordVaccineAdministration.fxml"));
            AnchorPane pane = loader.load();
            anchorPane.getChildren().setAll(pane);

            RecordVaccineAdministrationUI recordVaccineAdministrationUI = loader.getController();
            recordVaccineAdministrationUI.associateParentUI(this);
        } catch (Exception ex) {
            AlertUI.createErrorAlert().showAndWait();
        }
    }
}
