package org.example.gestorinvenntariocifp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
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
import java.time.format.DateTimeParseException;
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

    private ObservableList<Marcaje> marcajesObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Configurar columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colProducto.setCellValueFactory(cellData -> javafx.beans.binding.Bindings.createObjectBinding(() ->
                cellData.getValue().getIdProducto().getDescripcion()));
        colAula.setCellValueFactory(cellData -> javafx.beans.binding.Bindings.createObjectBinding(() ->
                cellData.getValue().getIdAula().getDescripcion()));
        colTipo.setCellValueFactory(cellData -> javafx.beans.binding.Bindings.createObjectBinding(() ->
                cellData.getValue().getTipoDescripcion()));
        colTimestamp.setCellValueFactory(cellData -> javafx.beans.binding.Bindings.createObjectBinding(() ->
                cellData.getValue().getFormattedTimeStamp()));

        // Hacer las columnas editables
        tablaMarcajes.setEditable(true);
        colTipo.setCellFactory(TextFieldTableCell.forTableColumn());
        colTipo.setOnEditCommit(event -> {
            Marcaje marcaje = event.getRowValue();
            try {
                int nuevoTipo = Integer.parseInt(event.getNewValue());
                marcaje.setTipo(nuevoTipo);
                actualizarMarcajeEnBD(marcaje);
            } catch (NumberFormatException e) {
                mostrarAlerta("Error", "El valor ingresado no es un número válido para el tipo.", Alert.AlertType.ERROR);
            }
        });

        // Cargar los datos
        cargarDatosMarcajes();

        // Configurar eventos de botones
        btnBuscar.setOnAction(event -> aplicarFiltros());
        btnNuevoMarcaje.setOnAction(event -> abrirVistaNuevoMarcaje());
        btnAtras.setOnAction(event -> volverAlMenu());
    }

    private void cargarDatosMarcajes() {
        marcajesObservableList.clear();

        // Configurar Hibernate
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            // Consulta para obtener todos los marcajes
            List<Marcaje> marcajes = session.createQuery("from Marcaje", Marcaje.class).list();
            marcajesObservableList.addAll(marcajes);
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar los marcajes.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        // Establecer los datos en la tabla
        tablaMarcajes.setItems(marcajesObservableList);
    }

    private void actualizarMarcajeEnBD(Marcaje marcaje) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.update(marcaje);
            session.getTransaction().commit();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo actualizar el marcaje.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void aplicarFiltros() {
        String filtroDescripcion = txtBuscarDescripcion.getText().toLowerCase().trim();
        LocalDate desde = dateDesde.getValue();
        LocalDate hasta = dateHasta.getValue();

        // Si no hay filtros, recargar todos los datos
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
                        // Validar el formato de la fecha completa y extraer solo la parte de fecha
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
                    } catch (DateTimeParseException e) {
                        System.err.println("Formato de fecha inválido: " + fechaStr);
                        return false;
                    }
                })
                .collect(Collectors.toList());

        tablaMarcajes.setItems(FXCollections.observableArrayList(filtrados));
    }

    private void abrirVistaNuevoMarcaje() {
        try {
            Stage stage = (Stage) btnNuevoMarcaje.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("nuevo-marcaje-view.fxml"));
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
