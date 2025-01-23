package org.example.gestorinvenntariocifp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.example.gestorinvenntariocifp.modelos.Marcaje;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

public class MarcajesController {

    @FXML
    private TableView<Marcaje> tablaMarcajes;

    @FXML
    private TableColumn<Marcaje, Integer> colId;

    @FXML
    private TableColumn<Marcaje, String> colProducto;

    @FXML
    private TableColumn<Marcaje, String> colAula;

    @FXML
    private TableColumn<Marcaje, String> colTipo;

    @FXML
    private TableColumn<Marcaje, String> colTimestamp;

    @FXML
    private TextField txtBuscarDescripcion;

    @FXML
    private DatePicker dateDesde;

    @FXML
    private DatePicker dateHasta;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnNuevoMarcaje;

    @FXML
    private Button btnAtras;

    @FXML
    private Button btnEliminar;

    private ObservableList<Marcaje> marcajesObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProducto.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createObjectBinding(() ->
                        cellData.getValue().getIdProducto().getDescripcion() // Solo el nombre del producto
                )
        );
        colAula.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createObjectBinding(() ->
                        cellData.getValue().getIdAula().getDescripcion()
                )
        );
        colTipo.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createObjectBinding(() ->
                        cellData.getValue().getTipoDescripcion()
                )
        );
        colTimestamp.setCellValueFactory(cellData ->
                javafx.beans.binding.Bindings.createObjectBinding(() ->
                        cellData.getValue().getFormattedTimeStamp()
                )
        );

        // Cargar datos iniciales
        cargarDatosMarcajes();

        // Configurar acciones de botones
        btnBuscar.setOnAction(event -> aplicarFiltros());
        btnNuevoMarcaje.setOnAction(event -> abrirVistaNuevoMarcaje());
        btnAtras.setOnAction(event -> volverAlMenu());
        btnEliminar.setOnAction(event -> eliminarMarcajeSeleccionado());
    }

    private void cargarDatosMarcajes() {
        marcajesObservableList.clear();
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            List<Marcaje> marcajes = session.createQuery("from Marcaje", Marcaje.class).list();
            marcajesObservableList.addAll(marcajes);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los marcajes.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        tablaMarcajes.setItems(marcajesObservableList);
    }

    private void eliminarMarcajeSeleccionado() {
        Marcaje marcajeSeleccionado = tablaMarcajes.getSelectionModel().getSelectedItem();
        if (marcajeSeleccionado == null) {
            mostrarAlerta("Advertencia", "Debe seleccionar un marcaje para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Marcaje marcaje = session.get(Marcaje.class, marcajeSeleccionado.getId());
            if (marcaje != null) {
                session.delete(marcaje);
                session.getTransaction().commit();
                marcajesObservableList.remove(marcajeSeleccionado);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo eliminar el marcaje.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void aplicarFiltros() {
        String filtroDescripcion = txtBuscarDescripcion.getText().toLowerCase().trim();
        LocalDate desde = dateDesde.getValue();
        LocalDate hasta = dateHasta.getValue();

        if (filtroDescripcion.isEmpty() && desde == null && hasta == null) {
            cargarDatosMarcajes();
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Marcaje> filtrados = marcajesObservableList.stream()
                .filter(marcaje -> filtroDescripcion.isEmpty() ||
                        marcaje.getIdProducto().getDescripcion().toLowerCase().contains(filtroDescripcion))
                .filter(marcaje -> {
                    String fechaStr = marcaje.getFormattedTimeStamp();
                    try {
                        LocalDateTime fechaHoraMarcaje = LocalDateTime.parse(fechaStr, formatter);
                        LocalDate fechaMarcaje = fechaHoraMarcaje.toLocalDate();

                        if (desde != null && hasta != null) {
                            return !fechaMarcaje.isBefore(desde) && !fechaMarcaje.isAfter(hasta);
                        } else if (desde != null) {
                            return !fechaMarcaje.isBefore(desde);
                        } else if (hasta != null) {
                            return !fechaMarcaje.isAfter(hasta);
                        }
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                })
                .collect(Collectors.toList());

        tablaMarcajes.setItems(FXCollections.observableArrayList(filtrados));
    }

    private void abrirVistaNuevoMarcaje() {
        try {
            Stage stage = (Stage) btnNuevoMarcaje.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevoMarcaje-view.fxml"));
            Scene nuevoMarcajeScene = new Scene(fxmlLoader.load());
            stage.setScene(nuevoMarcajeScene);
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la vista de nuevo marcaje.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void volverAlMenu() {
        try {
            Stage stage = (Stage) btnAtras.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene menuScene = new Scene(fxmlLoader.load());
            stage.setScene(menuScene);
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la vista del menú.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
