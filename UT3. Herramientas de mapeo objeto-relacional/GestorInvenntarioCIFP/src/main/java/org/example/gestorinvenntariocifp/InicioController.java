package org.example.gestorinvenntariocifp;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        // Acción al hacer clic en el botón de inicio de sesión
        btnLogin.setOnAction(event -> validarLogin());
    }

    private void validarLogin() {
        // Obtiene los valores de los campos
        String usuario = txtUsuario.getText();
        String password = txtPassword.getText();

        // Validar si los campos están vacíos
        if (usuario.isEmpty() || password.isEmpty()) {
            lblError.setText("Por favor, complete todos los campos.");
            lblError.setVisible(true);
            return;
        }

        // Validar credenciales (simulación)
        if (usuario.equals("admin") && password.equals("admin123")) {
            lblError.setText("Inicio de sesión exitoso.");
            lblError.setStyle("-fx-text-fill: green;"); // Mensaje de éxito en verde
            lblError.setVisible(true);
        } else {
            lblError.setText("Usuario o contraseña incorrectos.");
            lblError.setStyle("-fx-text-fill: red;"); // Mensaje de error en rojo
            lblError.setVisible(true);
        }
    }
}
