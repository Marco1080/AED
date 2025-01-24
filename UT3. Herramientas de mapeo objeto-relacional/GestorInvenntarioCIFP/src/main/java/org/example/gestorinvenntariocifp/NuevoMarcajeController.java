package org.example.gestorinvenntariocifp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestorinvenntariocifp.modelos.Aula;
import org.example.gestorinvenntariocifp.modelos.Marcaje;
import org.example.gestorinvenntariocifp.modelos.Producto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class NuevoMarcajeController {

    @FXML
    private ComboBox<String> productoComboBox;

    @FXML
    private ComboBox<String> aulaComboBox;

    @FXML
    private RadioButton entradaRadioButton;

    @FXML
    private RadioButton salidaRadioButton;

    @FXML
    private Label feedbackLabel;

    private ToggleGroup tipoMarcajeGroup;
    private SessionFactory sessionFactory;

    @FXML
    public void initialize() {
        sessionFactory = new Configuration()
                .configure()
                .addAnnotatedClass(Producto.class)
                .addAnnotatedClass(Aula.class)
                .addAnnotatedClass(Marcaje.class)
                .buildSessionFactory();

        ObservableList<String> productos = FXCollections.observableArrayList(getProductosFromDB());
        ObservableList<String> aulas = FXCollections.observableArrayList(getAulasFromDB());
        productoComboBox.setItems(productos);
        aulaComboBox.setItems(aulas);

        tipoMarcajeGroup = new ToggleGroup();
        entradaRadioButton.setToggleGroup(tipoMarcajeGroup);
        salidaRadioButton.setToggleGroup(tipoMarcajeGroup);
        entradaRadioButton.setSelected(true);
    }

    private List<String> getProductosFromDB() {
        try (Session session = sessionFactory.openSession()) {
            List<Producto> productos = session.createQuery("FROM Producto", Producto.class).list();
            return productos.stream().map(Producto::getDescripcion).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    private List<String> getAulasFromDB() {
        try (Session session = sessionFactory.openSession()) {
            List<Aula> aulas = session.createQuery("FROM Aula", Aula.class).list();
            return aulas.stream().map(Aula::getDescripcion).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @FXML
    public void handleCrearMarcaje() {
        String productoSeleccionado = productoComboBox.getValue();
        String aulaSeleccionada = aulaComboBox.getValue();
        RadioButton selectedTipo = (RadioButton) tipoMarcajeGroup.getSelectedToggle();

        if (productoSeleccionado == null || aulaSeleccionada == null || selectedTipo == null) {
            feedbackLabel.setText("Por favor, completa todos los campos.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
            return;
        }

        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            List<Producto> productos = session.createQuery("FROM Producto WHERE descripcion = :desc", Producto.class)
                    .setParameter("desc", productoSeleccionado)
                    .list();
            if (productos.isEmpty()) {
                feedbackLabel.setText("Producto no encontrado.");
                feedbackLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            if (productos.size() > 1) {
                feedbackLabel.setText("Más de un producto encontrado con esa descripción. Por favor, verifica.");
                feedbackLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            Producto producto = productos.get(0);

            List<Aula> aulas = session.createQuery("FROM Aula WHERE descripcion = :desc", Aula.class)
                    .setParameter("desc", aulaSeleccionada)
                    .list();
            if (aulas.isEmpty()) {
                feedbackLabel.setText("Aula no encontrada.");
                feedbackLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            if (aulas.size() > 1) {
                feedbackLabel.setText("Más de un aula encontrada con esa descripción. Por favor, verifica.");
                feedbackLabel.setStyle("-fx-text-fill: red;");
                return;
            }
            Aula aula = aulas.get(0);

            Marcaje marcaje = new Marcaje();
            marcaje.setIdProducto(producto);
            marcaje.setIdAula(aula);
            marcaje.setTipo(selectedTipo.getText().equals("Entrada") ? 1 : 2);
            marcaje.setTimeStamp(Instant.now());

            session.persist(marcaje);
            session.getTransaction().commit();

            feedbackLabel.setText("Marcaje guardado correctamente.");
            feedbackLabel.setStyle("-fx-text-fill: green;");
            redirectToMarcajes();
        } catch (Exception e) {
            e.printStackTrace();
            feedbackLabel.setText("Error al guardar el marcaje.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }


    @FXML
    public void handleCancelar() {
        redirectToMarcajes();
    }

    private void redirectToMarcajes() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/gestorinvenntariocifp/marcajes-view.fxml"));
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) feedbackLabel.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            feedbackLabel.setText("Error al redirigir a la vista de marcajes.");
            feedbackLabel.setStyle("-fx-text-fill: red;");
        }
    }

    public void close() {
        if (sessionFactory != null) sessionFactory.close();
    }
}
