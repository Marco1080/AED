package org.example.gestorinvenntariocifp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class InicioController {

    @FXML
    private TextField txtUsuario;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblError;

    @FXML
    public void initialize() {
        btnLogin.setOnAction(event -> validarYMostrarMenu());
    }

    private void validarYMostrarMenu() {
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        if (usuario.isEmpty() || password.isEmpty()) {
            lblError.setText("Por favor, complete todos los campos.");
            lblError.setVisible(true);
            return;
        }

        try {
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene menuScene = new Scene(fxmlLoader.load());
            stage.setScene(menuScene);
        } catch (IOException e) {
            lblError.setText("Error al cargar el menú.");
            lblError.setVisible(true);
            e.printStackTrace();
        }
    }
}
