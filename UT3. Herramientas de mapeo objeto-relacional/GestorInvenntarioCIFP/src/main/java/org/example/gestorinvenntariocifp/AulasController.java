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
    private Button btnCrear;

    @FXML
    private Button btnEliminar;

    private ObservableList<Aula> aulasObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarAulas();

        btnRecargar.setOnAction(event -> cargarAulas());
        btnAtras.setOnAction(event -> volverAlMenu());
        btnCrear.setOnAction(event -> crearAula());
        btnEliminar.setOnAction(event -> eliminarAulaSeleccionada());
    }

    private void configurarColumnas() {
        colId.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        colNumeracion.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("numeracion"));
        colDescripcion.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("descripcion"));
        colIp.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("ip"));
    }

    private void cargarAulas() {
        aulasObservableList.clear();
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            List<Aula> aulas = session.createQuery("from Aula", Aula.class).list();
            aulasObservableList.addAll(aulas);
        } catch (Exception e) {
            e.printStackTrace();
        }

        tablaAulas.setItems(aulasObservableList);
    }

    private void volverAlMenu() {
        try {
            Stage stage = (Stage) btnAtras.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene menuScene = new Scene(fxmlLoader.load(), 800, 600); // Dimensiones consistentes
            stage.setScene(menuScene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void crearAula() {
        try {
            Stage stage = (Stage) tablaAulas.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevaAula-view.fxml"));
            Scene crearScene = new Scene(fxmlLoader.load(), 800, 600);
            stage.setScene(crearScene);
            stage.centerOnScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void eliminarAulaSeleccionada() {
        Aula aulaSeleccionada = tablaAulas.getSelectionModel().getSelectedItem();
        if (aulaSeleccionada == null) {
            return;
        }

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Aula aula = session.get(Aula.class, aulaSeleccionada.getId());
            if (aula != null) {
                session.delete(aula);
                session.getTransaction().commit();
                tablaAulas.getItems().remove(aulaSeleccionada);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
