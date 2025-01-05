package org.example.gestorinvenntariocifp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.example.gestorinvenntariocifp.modelos.Aula;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class AulasController {

    @FXML
    private TableView<Aula> tablaAulas;

    @FXML
    private TableColumn<Aula, Integer> colId;

    @FXML
    private TableColumn<Aula, String> colNumeracion;

    @FXML
    private TableColumn<Aula, String> colDescripcion;

    @FXML
    private TableColumn<Aula, String> colIp;

    @FXML
    private Button btnRecargar;

    @FXML
    private Button btnAtras;

    @FXML
    public void initialize() {
        System.out.println("Inicializando controlador de aulas...");

        // Configurar columnas
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colNumeracion.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("numeracion"));
        colDescripcion.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("descripcion"));
        colIp.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("ip"));

        // Cargar datos al inicio
        cargarAulas();

        // Acción del botón Recargar
        btnRecargar.setOnAction(event -> cargarAulas());

        // Acción del botón Atrás
        btnAtras.setOnAction(event -> volverAlMenu());
    }

    private void cargarAulas() {
        ObservableList<Aula> aulasObservableList = FXCollections.observableArrayList();

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            // Consulta para obtener todas las aulas
            List<Aula> aulas = session.createQuery("from Aula", Aula.class).list();
            aulasObservableList.addAll(aulas);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Establecer los datos en la tabla
        tablaAulas.setItems(aulasObservableList);
    }

    private void volverAlMenu() {
        try {
            Stage stage = (Stage) btnAtras.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene menuScene = new Scene(fxmlLoader.load());
            stage.setScene(menuScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
