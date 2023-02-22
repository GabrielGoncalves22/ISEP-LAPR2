package app.ui.gui;

import app.core.controller.ImportManageCenterDataController;
import app.core.domain.shared.SortDataCriteria;
import app.core.domain.model.LegacySystemFile;
import app.core.domain.shared.exception.InvalidDataInLegacySystemFileException;
import app.core.domain.store.LegacySystemFileStore;
import app.ui.gui.table.LegacySystemFileTable;
import app.ui.gui.utils.Pair;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

/**
 * This class represent the controller of the UI to import manage center data from a legacy system.
 *
 * @author Group 40
 */
public class ImportManageCenterDataUI implements Initializable {

    /**
     * Button to choose a file.
     */
    @FXML
    private Button btnChooseFile;

    /**
     * Combo box that holds the order criteria options.
     */
    @FXML
    private ComboBox<Pair<String, String>> cbOrderCriteria;

    /**
     * Combo box that holds the order options.
     */
    @FXML
    private ComboBox<Pair<String, String>> cbOrder;

    /**
     * The table that holds the information using LegacySystemFileTable class.
     */
    @FXML
    private TableView<LegacySystemFileTable> tblDataInformation;

    /**
     * Column that holds the SNS User number.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnSNSUserNumber;

    /**
     * Column that holds the SNS User name.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnName;

    /**
     * Column that holds the vaccine name.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnVaccineName;

    /**
     * Column that holds the vaccine short description.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnVaccineShortDescription;

    /**
     * Column that holds the dose.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnDose;

    /**
     * Column that holds the lot number.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnLotNumber;

    /**
     * Column that holds the scheduled date and time.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnScheduledDT;

    /**
     * Column that holds the arrival date and time.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnArrivalDT;

    /**
     * Column that holds the nurse vaccine administration date and time.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnNurseAdministrationDT;

    /**
     * Column that holds the leaving date and time.
     */
    @FXML
    private TableColumn<LegacySystemFileTable, String> columnLeavingDT;

    /**
     * Show the path chosen to the file.
     */
    @FXML
    private TextField txtFilePath;

    /**
     * Label that holds the errors found before import the data.
     */
    @FXML
    private Label lblErrorReport;

    /**
     * Label that holds the errors found in the import data time.
     */
    @FXML
    private Label lblImportDataFail;

    /**
     * Button to import the data.
     */
    @FXML
    private Button btnImportData;

    /**
     * Knows if the data was already loaded to memory.
     */
    private boolean dataLoaded = false;

    /**
     * Knows if the data criteria option was already chosen.
     */
    private boolean dataCriteriaChosen = false;

    /**
     * Holds a list of data to be shown in the table.
     */
    ObservableList<LegacySystemFileTable> legacySystemFileObservableList = FXCollections.observableArrayList();

    /**
     * Holds the pair with key and value to join a combo box, and represents the ascending order option.
     */
    private final Pair<String, String> ascPair = new Pair<>(LegacySystemFileStore.ORDER_ASC, "Ascending order");

    /**
     * Holds the pair with key and value to join a combo box, and represents the descending order option.
     */
    private final Pair<String, String> descPair = new Pair<>(LegacySystemFileStore.ORDER_DESC, "Descending order");

    /**
     * The controller responsible for this UI
     */
    private ImportManageCenterDataController controller;

    /**
     * Method that initialize this UI. This method is called (in first place) every time this UI is access.
     * @param url The url.
     * @param resourceBundle The resource bundle.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillOrderCriteriaOptions();
        fillOrderOptions();
        cbOrderCriteria.setDisable(true);
        cbOrder.setDisable(true);
    }

    /**
     * Method to handle the event of choosing a file.
     * @param event The event.
     */
    @FXML
    void chooseFile(ActionEvent event) {
        tblDataInformation.getItems().clear();
        dataLoaded = false;
        dataCriteriaChosen = false;
        FileChooser fc = new FileChooser();
        File f = fc.showOpenDialog(null);

        if (f != null) {
            String filePath = f.getAbsolutePath();
            txtFilePath.setText(filePath);

            String message = this.controller.validateFile(filePath);
            if (message.equals("")) {
                tblDataInformation.setPlaceholder(new Label("There is no data to be displayed!"));
                try {
                    List<LegacySystemFile> lSF = this.controller.readFileData(filePath);
                    this.updateTableView(lSF);
                    cbOrderCriteria.setDisable(false);
                    dataLoaded = true;
                } catch (RuntimeException ex) {
                    lblErrorReport.setText(ex.getMessage());
                }
            } else {
                lblErrorReport.setText(message);
            }

        } else {
            cbOrderCriteria.setDisable(true);
            cbOrder.setDisable(true);
        }
    }

    /**
     * Method to upload the data into the table, so the user can see the data before import it.
     *
     * @param lSFList The list with LegacySystemFile objects to be imported.
     */
    private void updateTableView(List<LegacySystemFile> lSFList) {
        lblErrorReport.setText("");
        lblImportDataFail.setText("");
        tblDataInformation.getItems().clear();

        for (LegacySystemFile lSF : lSFList) {

            LegacySystemFileTable lSFTable = new LegacySystemFileTable(
                    lSF.getSnsUserDTO().getSnsUserNumber(),
                    lSF.getSnsUserDTO().getName(), lSF.getVaccineDTO().getName(),
                    lSF.getVaccineDTO().getVtDto().getDescription(), lSF.getDose(), lSF.getLotNumber(),
                    lSF.getScheduledDate().toDayMonthYearString() + " " + lSF.getScheduledTime().toStringHHMM(),
                    lSF.getArrivalDate().toDayMonthYearString() + " " + lSF.getArrivalTime().toStringHHMM(),
                    lSF.getNurseAdministrationDate().toDayMonthYearString() + " " + lSF.getNurseAdministrationTime().toStringHHMM(),
                    lSF.getLeavingDate().toDayMonthYearString() + " " + lSF.getLeavingTime().toStringHHMM());


            legacySystemFileObservableList.add(lSFTable);

            columnSNSUserNumber.setCellValueFactory(cellData -> cellData.getValue().snsUserNumberProperty());
            columnName.setCellValueFactory(cellData -> cellData.getValue().snsUserNameProperty());
            columnVaccineName.setCellValueFactory(cellData -> cellData.getValue().vaccineNameProperty());
            columnVaccineShortDescription.setCellValueFactory(cellData -> cellData.getValue().vaccineTypeShortDescriptionProperty());
            columnDose.setCellValueFactory(cellData -> cellData.getValue().doseProperty());
            columnLotNumber.setCellValueFactory(cellData -> cellData.getValue().lotNumberProperty());
            columnScheduledDT.setCellValueFactory(cellData -> cellData.getValue().scheduledDateTimeProperty());
            columnArrivalDT.setCellValueFactory(cellData -> cellData.getValue().arrivalDateTimeProperty());
            columnNurseAdministrationDT.setCellValueFactory(cellData -> cellData.getValue().nurseAdministrationDateTimeProperty());
            columnLeavingDT.setCellValueFactory(cellData -> cellData.getValue().leavingDateTimeProperty());

        }

        tblDataInformation.setItems(legacySystemFileObservableList);

        tblDataInformation.setPlaceholder(new Label("There is no data to be displayed!"));

    }

    /**
     * Method to change the order criteria of the data.
     * @param event The event.
     */
    @FXML
    void changeOrderCriteria(ActionEvent event) {
        lblImportDataFail.setText("");
        lblErrorReport.setText("");

        List<LegacySystemFile> lSFList = this.controller.sortDataByCriteria(cbOrderCriteria.getValue().getKey());
        if (lSFList != null) {
            cbOrder.setValue(this.ascPair);
            cbOrder.setDisable(false);
            updateTableView(lSFList);
            dataCriteriaChosen = true;
        } else {
            lblErrorReport.setText("Something went wrong! Please, try again later.");
        }

    }

    /**
     * Method to handle the event of change the order of the present data.
     * @param event The event.
     */
    @FXML
    void changeDataOrder(ActionEvent event) {
        try {
            List<LegacySystemFile> lSF = this.controller.changeSortOrder(cbOrder.getValue().getKey());
            updateTableView(lSF);
        } catch (IllegalArgumentException ex) {
            lblErrorReport.setText("An error occurred. Please try again.");
        }
    }

    /**
     * Method to handle the event of importing the data.
     * @param event The event.
     */
    @FXML
    void importData(ActionEvent event) {
        if (dataLoaded && dataCriteriaChosen) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to export the presented data?");
            alert.getButtonTypes().clear();
            alert.getButtonTypes().addAll(ButtonType.YES, ButtonType.NO);
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.YES) {
                try {
                    this.controller.importDataToTheSystem();
                    Alert success = new Alert(Alert.AlertType.INFORMATION, "The data was successfully imported!");
                    success.setHeaderText("Operation information");
                    success.showAndWait();
                    dataCriteriaChosen = false;
                    dataLoaded = false;
                    txtFilePath.setText("");
                    tblDataInformation.getItems().clear();
                } catch (InvalidDataInLegacySystemFileException ex) {
                    lblImportDataFail.setText(ex.getMessage());
                }
            }
        } else {
            lblImportDataFail.setText("It's required to load a file and choose the order criteria first!");
        }

    }

    /**
     * Method to fill a combo box with the possible order criteria items.
     */
    private void fillOrderCriteriaOptions() {
        cbOrderCriteria.getItems().clear();
        cbOrderCriteria.getItems().add(new Pair<>(SortDataCriteria.ARRIVAL_CRITERIA, "Date and time of arrival at the center"));
        cbOrderCriteria.getItems().add(new Pair<>(SortDataCriteria.LEAVING_CRITERIA, "Leaving date and time from the center"));
    }

    /**
     * Method to fill a combo box with the possible order options items.
     */
    private void fillOrderOptions() {
        cbOrder.getItems().clear();
        cbOrder.getItems().add(this.ascPair);
        cbOrder.getItems().add(this.descPair);
    }

    /**
     * Method used to connect this UI with the Center Coordinator main menu UI.
     *
     * @param centerCoordinatorMenuUI The Center Coordinator UI.
     */
    public void connectParentUI(CenterCoordinatorMenuUI centerCoordinatorMenuUI) {
        this.controller = new ImportManageCenterDataController(centerCoordinatorMenuUI.getSelectVaccinationCenterUI().getSelectedVcId());
    }

}