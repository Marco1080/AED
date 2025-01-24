package org.example.gestorinvenntariocifp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.gestorinvenntariocifp.modelos.Aula;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class NuevaAulaController {

    @FXML
    private TextField txtNumeracion;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtIp;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblError;

    @FXML
    public void initialize() {
        btnGuardar.setOnAction(event -> guardarAula());
        btnCancelar.setOnAction(event -> volverAAulas());
    }

    private void guardarAula() {
        String numeracion = txtNumeracion.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        String ip = txtIp.getText().trim();

        if (numeracion.isEmpty() || descripcion.isEmpty() || ip.isEmpty()) {
            mostrarError("Todos los campos son obligatorios.");
            return;
        }

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {

            List<Aula> aulasExistentes = session.createQuery("from Aula", Aula.class).list();
            for (Aula aula : aulasExistentes) {
                if (aula.getDescripcion().equalsIgnoreCase(descripcion)) {
                    mostrarError("Ya existe un aula con la misma descripción.");
                    return;
                }
                if (aula.getNumeracion().equalsIgnoreCase(numeracion)) {
                    mostrarError("Ya existe un aula con la misma numeración.");
                    return;
                }
                if (aula.getIp().equalsIgnoreCase(ip)) {
                    mostrarError("Ya existe un aula con la misma IP.");
                    return;
                }
            }
            Aula nuevaAula = new Aula();
            nuevaAula.setNumeracion(numeracion);
            nuevaAula.setDescripcion(descripcion);
            nuevaAula.setIp(ip);

            session.beginTransaction();
            session.save(nuevaAula);
            session.getTransaction().commit();

            mostrarAlerta("Éxito", "Aula creada exitosamente.");
            volverAAulas();
        } catch (Exception e) {
            mostrarError("Error al guardar el aula en la base de datos.");
            e.printStackTrace();
        }
    }

    private void volverAAulas() {
        try {
            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("aulas-view.fxml"));
            Scene aulasScene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(aulasScene);
            stage.centerOnScreen();
        } catch (Exception e) {
            mostrarError("No se pudo volver a la vista de aulas.");
            e.printStackTrace();
        }
    }


    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setVisible(true);
    }

    private void mostrarAlerta(String titulo, String contenido) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
