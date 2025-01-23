package org.example.gestorinvenntariocifp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.example.gestorinvenntariocifp.modelos.Categoria;
import org.example.gestorinvenntariocifp.modelos.Producto;
import org.example.gestorinvenntariocifp.modelos.Aula;
import org.example.gestorinvenntariocifp.modelos.Marcaje;

import java.io.IOException;
import java.time.Instant;

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
    private CheckBox chkDatosPrueba;

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

        if (chkDatosPrueba.isSelected()) {
            crearDatosDePrueba();
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

    private void crearDatosDePrueba() {
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            Categoria categoria1 = new Categoria();
            categoria1.setNombre("Electrónica");
            session.save(categoria1);

            Categoria categoria2 = new Categoria();
            categoria2.setNombre("Mobiliario");
            session.save(categoria2);

            Producto producto1 = new Producto();
            producto1.setDescripcion("Laptop HP");
            producto1.setEan13(13213);
            producto1.setKeyRFID("RF123456");
            producto1.setIdCategoria(categoria1.getId());
            session.save(producto1);

            Producto producto2 = new Producto();
            producto2.setDescripcion("Mesa de oficina");
            producto2.setEan13(9876);
            producto2.setKeyRFID("RF987654");
            producto2.setIdCategoria(categoria2.getId());
            session.save(producto2);

            Aula aula1 = new Aula();
            aula1.setNumeracion("A101");
            aula1.setDescripcion("Aula Principal");
            aula1.setIp("192.168.1.1");
            session.save(aula1);

            Aula aula2 = new Aula();
            aula2.setNumeracion("B202");
            aula2.setDescripcion("Laboratorio de Física");
            aula2.setIp("192.168.1.2");
            session.save(aula2);

            Marcaje marcaje1 = new Marcaje();
            marcaje1.setIdProducto(producto1);
            marcaje1.setIdAula(aula1);
            marcaje1.setTipo(1);
            marcaje1.setTimeStamp(Instant.now());
            session.save(marcaje1);

            Marcaje marcaje2 = new Marcaje();
            marcaje2.setIdProducto(producto2);
            marcaje2.setIdAula(aula2);
            marcaje2.setTipo(2);
            marcaje2.setTimeStamp(Instant.now());
            session.save(marcaje2);

            session.getTransaction().commit();
        } catch (Exception e) {
            lblError.setText("Error al crear datos de prueba.");
            lblError.setVisible(true);
            e.printStackTrace();
        }
    }
}
