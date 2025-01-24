package org.example.gestorinvenntariocifp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.gestorinvenntariocifp.modelos.Categoria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class NuevaCategoriaController {

    @FXML
    private TextField txtNombre;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    @FXML
    private Label lblError;

    @FXML
    public void initialize() {
        btnGuardar.setOnAction(event -> guardarCategoria());
        btnCancelar.setOnAction(event -> volverACategorias());
    }

    private void guardarCategoria() {
        String nombre = txtNombre.getText().trim();

        if (nombre.isEmpty()) {
            mostrarError("El campo nombre es obligatorio.");
            return;
        }

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            List<Categoria> categoriasExistentes = session.createQuery("from Categoria", Categoria.class).list();
            for (Categoria categoria : categoriasExistentes) {
                if (categoria.getNombre().equalsIgnoreCase(nombre)) {
                    mostrarError("Ya existe una categoría con el mismo nombre.");
                    return;
                }
            }

            Categoria nuevaCategoria = new Categoria();
            nuevaCategoria.setNombre(nombre);

            session.beginTransaction();
            session.save(nuevaCategoria);
            session.getTransaction().commit();

            mostrarAlerta("Éxito", "Categoría creada exitosamente.");
            volverACategorias();
        } catch (Exception e) {
            mostrarError("Error al guardar la categoría en la base de datos.");
            e.printStackTrace();
        }
    }

    private void volverACategorias() {
        try {
            Stage stage = (Stage) btnGuardar.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("categoria-view.fxml"));
            Scene categoriaScene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(categoriaScene);
            stage.centerOnScreen();
        } catch (Exception e) {
            mostrarError("No se pudo volver a la vista de categorías.");
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
