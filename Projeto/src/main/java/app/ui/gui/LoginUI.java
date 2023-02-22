package app.ui.gui;

import app.core.controller.AuthController;
import app.ui.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class LoginUI implements Initializable {
    private AuthController authController;
    private UserRoleDTO roleLogin;
    private String email;

    @FXML
    private TextField emailTxt;

    @FXML
    private PasswordField passwordPsw;

    @FXML
    private TextField passwordTxt;

    @FXML
    private Label warningLbl;

    @FXML
    private CheckBox showPasswordSelect;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authController = new AuthController();

        noFocus();
        warningLbl.setVisible(false);
        passwordTxt.setVisible(false);
    }

    public void initializeSelectVaccinationCenterUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/SelectVaccinationCenter.fxml"));

            Scene scene = new Scene(loader.load(), 600, 450);
            Stage window = MainApp.STAGE;
            window.setScene(scene);
            window.centerOnScreen();
            window.show();

            SelectVaccinationCenterUI selectVaccinationCenterUI = loader.getController();
            selectVaccinationCenterUI.associarParentUI(this);
        } catch (IOException ex) {
            AlertUI.createErrorAlert().showAndWait();
        }
    }

    @FXML
    void login(ActionEvent event) {
        boolean success = doLogin();

        if (success) {
            List<UserRoleDTO> roles = this.authController.getUserRoles();
            if ((roles == null) || (roles.isEmpty())) {
                warningLbl.setText("User has not any role assigned.");
                warningLbl.setVisible(true);
            } else {
                UserRoleDTO role = selectsRole(roles);
                if (!Objects.isNull(role)) {
                    this.roleLogin = role;
                    this.email = emailTxt.getText().trim();
                    initializeSelectVaccinationCenterUI();
                } else {
                    warningLbl.setText("User did not select a role.");
                    warningLbl.setVisible(true);
                }
            }
        }

        this.logout();
    }

    @FXML
    void showPassword(ActionEvent event) {
        if (showPasswordSelect.isSelected()) {
            passwordTxt.setText(passwordPsw.getText());
            passwordTxt.setVisible(true);
            passwordPsw.setVisible(false);

        } else {

            passwordPsw.setText(passwordTxt.getText());
            passwordPsw.setVisible(true);
            passwordTxt.setVisible(false);
        }
    }

    @FXML
    void passwordTxt(KeyEvent event) {
        passwordPsw.setText(passwordTxt.getText());
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

    private boolean doLogin() {
        String email = emailTxt.getText().trim();
        String password = passwordPsw.getText().trim();

        boolean success = authController.doLogin(email, password);
        if (!success) {
            warningLbl.setText("Invalid Email and/or Password.");
            warningLbl.setVisible(true);
        }

        return success;
    }

    private void logout() {
        authController.doLogout();
    }

    private UserRoleDTO selectsRole(List<UserRoleDTO> roles) {
        if (roles.size() == 1) {
            return roles.get(0);
        } else {
            return null;
        }
    }

    public UserRoleDTO getRoleLogin() {
        return roleLogin;
    }

    public String getEmail() {
        return email;
    }

    private void noFocus() {
        emailTxt.setFocusTraversable(false);
        passwordTxt.setFocusTraversable(false);
        passwordPsw.setFocusTraversable(false);
        showPasswordSelect.setFocusTraversable(false);
    }
}
