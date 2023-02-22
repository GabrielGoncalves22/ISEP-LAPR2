package app.ui.gui;

import app.ui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CenterCoordinatorMenuUI {
    private SelectVaccinationCenterUI selectVaccinationCenterUI;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    void importManageCenterData(ActionEvent event) {
        importManageCenterDataUI();
    }

    @FXML
    void analyzePerformance(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/AnalyzePerformance.fxml"));
            AnchorPane pane = loader.load();
            anchorPane.getChildren().setAll(pane);

            AnalyzePerformanceUI analyzePerformanceUI = loader.getController();
            analyzePerformanceUI.connectParentUI(this);

        } catch (Exception ex) {
            AlertUI.createErrorAlert().showAndWait();
        }
    }

    @FXML
    void vaccinationStatistics(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/VaccinationStatistics.fxml"));
            AnchorPane pane = loader.load();
            anchorPane.getChildren().setAll(pane);

            VaccinationStatisticsUI vaccinationStatisticsUI = loader.getController();
            vaccinationStatisticsUI.connectParentUI(this);

        } catch (Exception ex) {
            AlertUI.createErrorAlert().showAndWait();
        }
    }

    @FXML
    void logoutProgram(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/Login.fxml")));
            Stage window = MainApp.STAGE;
            Scene scene = new Scene(root, 400, 550);
            window.setScene(scene);
            window.show();
            window.centerOnScreen();

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
        importManageCenterDataUI();
    }

    public void importManageCenterDataUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ImportManageCenterData.fxml"));
            AnchorPane pane = loader.load();
            anchorPane.getChildren().setAll(pane);

            ImportManageCenterDataUI importManageCenterDataUI = loader.getController();
            importManageCenterDataUI.connectParentUI(this);

        } catch (Exception ex) {
            AlertUI.createErrorAlert().showAndWait();
        }
    }

    public SelectVaccinationCenterUI getSelectVaccinationCenterUI() {
        return selectVaccinationCenterUI;
    }
}
