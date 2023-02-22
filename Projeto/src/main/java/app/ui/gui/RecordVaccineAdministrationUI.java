package app.ui.gui;

import app.core.controller.RecordVaccineAdministrationController;
import app.core.domain.model.*;
import app.core.dto.*;
import app.core.mapper.VaccineMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Represents the record vaccine administration UI(User interface).
 *
 * @author Group 40
 */
public class RecordVaccineAdministrationUI implements Initializable {
    private NurseMenuUI nurseMenuUI;
    private RecordVaccineAdministrationController controller;
    private VaccineAdministrationDTO vaDTO = new VaccineAdministrationDTO();

    @FXML
    private ComboBox<String> usersCBox;

    @FXML
    private ComboBox<VaccineDTO> vaccinesCBox;

    @FXML
    private ComboBox<DoseDTO> dosesCBox;

    @FXML
    private TextArea infoUserTxtArea;

    @FXML
    private TextField dosageTxt;

    @FXML
    private TextField lotNumberTxt;

    @FXML
    private Label warningLotNumber;

    @FXML
    private Button saveBtn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setDefaultConfiguration();
    }

    @FXML
    void saveVaccineAdministration(ActionEvent event) {
        try {
            vaDTO.setAdministrationDate(Date.currentDate());
            vaDTO.setAdministrationTime(Time.currentTime());
            vaDTO.setVaccinationCenter(controller.getVaccinationCenter());
            vaDTO.setLotNumber(lotNumberTxt.getText().trim());
            setLeavingDateTime();

            if (controller.createVaccineAdministration(vaDTO)) {
                Alert alert = AlertUI.createAlert(Alert.AlertType.CONFIRMATION, "Application", "Action confirmation", "Do you really want save the administration vaccine?");

                if (alert.showAndWait().get() == ButtonType.OK) {
                    controller.saveVaccineAdministration();
                    controller.sendMessage(vaDTO.getSnsUserDTO().getPhoneNumber(), "Your recovery period ends at " + Time.currentTime().addMinutes(controller.getRecoveryPeriod()) + ".", Time.minutesToMilliseconds(controller.getRecoveryPeriod()));

                    setDefaultConfiguration();
                    fillUsersChoiceBox();
                } else {
                    event.consume();
                }

            } else {
                AlertUI.createAlert(Alert.AlertType.ERROR, "Application", "Error action", "Unable to create vaccine administration!").showAndWait();
            }

        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
            AlertUI.createAlert(Alert.AlertType.ERROR, "Application", "Error action", "Unable to create vaccine administration!").showAndWait();
        }
    }

    @FXML
    void selectSNSUser(ActionEvent event) {
        String selectUser = usersCBox.getValue();
        if (selectUser != null) {
            SNSUserDTO su = controller.searchSNSUser(selectUser);
            vaDTO.setSnsUserDTO(su);

            String name = su.getName();
            int age = Date.currentAge(su.getBirthdate());
            String adverseReactions = "No Adverse Reactions registered in the system.";

            infoUserTxtArea.setText("The user info:\nNome: " + name + "\nAge: " + age + "\nAdverse Reactions: " + adverseReactions);

            setDefaultConfiguration();
            vaccinesCBox.setDisable(false);
            fillVaccineChoiceBox();
        }
    }

    @FXML
    void selectVaccine(ActionEvent event) {
        VaccineDTO selectVaccine = vaccinesCBox.getValue();
        if (selectVaccine != null) {
            vaDTO.setVaccineDTO(selectVaccine);

            fillDoseChoiceBox(selectVaccine);
            dosesCBox.setDisable(false);
        }
    }

    @FXML
    void selectDose(ActionEvent event) {
        DoseDTO selectDose = dosesCBox.getValue();
        if (selectDose != null) {
            vaDTO.setDoseDTO(selectDose);

            dosageTxt.setText(selectDose.getDosage() + "ml");
            lotNumberTxt.setDisable(false);
        }
    }

    @FXML
    void writingLotNumber(KeyEvent event) {
        String lotNumber = lotNumberTxt.getText();
        if (VaccineAdministration.correctFormatLotNumber(lotNumber)) {
            warningLotNumber.setVisible(false);
            saveBtn.setDisable(false);
        } else {
            warningLotNumber.setVisible(true);
            saveBtn.setDisable(true);
        }
    }

    public void associateParentUI(NurseMenuUI nurseMenuUI) {
        this.nurseMenuUI = nurseMenuUI;
        this.controller = new RecordVaccineAdministrationController(nurseMenuUI.getSelectVaccinationCenterUI().getSelectedVcId());
        fillUsersChoiceBox();
    }

    private void fillUsersChoiceBox() {
        List<ArrivalSNSUserDTO> listVCDto = controller.getListArrivalSNSUser();
        usersCBox.getItems().clear();

        for (ArrivalSNSUserDTO list : listVCDto) {
            usersCBox.getItems().add(list.getSnsUser().getSnsUserNumber());
        }

        infoUserTxtArea.clear();
    }

    private void fillVaccineChoiceBox() {
        List<VaccineDTO> listVDto = controller.getListSuggestedVaccineAdminister();
        vaccinesCBox.getItems().clear();
        vaccinesCBox.getItems().addAll(listVDto);
    }

    private void fillDoseChoiceBox(VaccineDTO vDto) {
        List<DoseDTO> listDDto = controller.getListSuggestedDoseAdminister(VaccineMapper.toModel(vDto));
        dosesCBox.getItems().clear();
        dosesCBox.getItems().addAll(listDDto);
    }

    private void setDefaultConfiguration() {
        vaccinesCBox.setDisable(true);
        vaccinesCBox.getItems().clear();
        dosesCBox.setDisable(true);
        dosesCBox.getItems().clear();
        dosageTxt.clear();
        dosageTxt.setDisable(true);
        lotNumberTxt.clear();
        lotNumberTxt.setDisable(true);
        warningLotNumber.setVisible(false);
        saveBtn.setDisable(true);
    }

    private void setLeavingDateTime() {
        int recoveryPeriod = controller.getRecoveryPeriod();

        Time tempTime = new Time(vaDTO.getAdministrationTime());

        vaDTO.setLeavingDate(vaDTO.getAdministrationDate());
        vaDTO.setLeavingTime(tempTime.addMinutes(recoveryPeriod));
    }
}
