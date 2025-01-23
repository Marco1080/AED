package org.example.gestorinvenntariocifp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.gestorinvenntariocifp.modelos.Aula;
import org.example.gestorinvenntariocifp.modelos.Categoria;
import org.example.gestorinvenntariocifp.modelos.Producto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NuevoProductoController {

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtEan13;

    @FXML
    private TextField txtRfid;

    @FXML
    private ComboBox<String> comboAulas;

    @FXML
    private ComboBox<String> comboCategoria;

    @FXML
    private RadioButton radioNuevo;

    @FXML
    private RadioButton radioUsado;

    @FXML
    private DatePicker dateAdquisicion;

    @FXML
    private Spinner<Integer> spinnerCantidad;

    @FXML
    private Slider sliderPrecio;

    @FXML
    private Label labelPrecio;

    @FXML
    private TextArea txtNotas;

    @FXML
    private Button btnGuardar;

    @FXML
    private Button btnCancelar;

    private ObservableList<String> numeracionesAulasObservableList = FXCollections.observableArrayList();
    private ObservableList<String> categoriasObservableList = FXCollections.observableArrayList();
    private Map<String, Integer> categoriasMap = new HashMap<>(); // Mapa para almacenar categorías con sus IDs

    @FXML
    public void initialize() {
        cargarAulas();
        cargarCategorias();
        configurarEstadoRadioButtons();
        configurarSpinnerCantidad();
        configurarSliderPrecio();

        btnGuardar.setOnAction(event -> guardarProducto());
        btnCancelar.setOnAction(event -> volverAProductos());
    }

    private void cargarAulas() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            List<Aula> aulas = session.createQuery("from Aula", Aula.class).list();
            for (Aula aula : aulas) {
                numeracionesAulasObservableList.add(aula.getNumeracion());
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar las aulas.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        comboAulas.setItems(numeracionesAulasObservableList);
    }

    private void cargarCategorias() {
        categoriasObservableList.clear();
        categoriasMap.clear();

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            List<Object[]> categorias = session.createNativeQuery(
                    "SELECT c.IdCategoria, c.Nombre FROM categoria c"
            ).list();
            for (Object[] categoria : categorias) {
                String nombreCategoria = (String) categoria[1];
                int idCategoria = ((Number) categoria[0]).intValue();
                categoriasObservableList.add(nombreCategoria);
                categoriasMap.put(nombreCategoria, idCategoria); // Guardar el ID asociado al nombre
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar las categorías.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        comboCategoria.setItems(categoriasObservableList);
    }

    private void configurarEstadoRadioButtons() {
        ToggleGroup toggleGroupEstado = new ToggleGroup();
        radioNuevo.setToggleGroup(toggleGroupEstado);
        radioUsado.setToggleGroup(toggleGroupEstado);
    }

    private void configurarSpinnerCantidad() {
        spinnerCantidad.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 1000, 10));
    }

    private void configurarSliderPrecio() {
        sliderPrecio.valueProperty().addListener((observable, oldValue, newValue) -> {
            labelPrecio.setText(String.format("%.2f €", newValue.doubleValue()));
        });
    }

    private void guardarProducto() {
        String descripcion = txtDescripcion.getText().trim();
        String ean13Text = txtEan13.getText().trim();
        String rfid = txtRfid.getText().trim();
        String numeracionSeleccionada = comboAulas.getValue();
        String categoriaSeleccionada = comboCategoria.getValue();
        Integer cantidadStock = spinnerCantidad.getValue();
        Double precioUnitario = sliderPrecio.getValue();

        if (descripcion.isEmpty() || ean13Text.isEmpty() || rfid.isEmpty() || numeracionSeleccionada == null || cantidadStock == null || precioUnitario == null) {
            mostrarAlerta("Campos Obligatorios", "Todos los campos son obligatorios, excepto la categoría.", Alert.AlertType.WARNING);
            return;
        }

        try {
            long ean13 = Long.parseLong(ean13Text);

            Producto nuevoProducto = new Producto();
            nuevoProducto.setDescripcion(descripcion);
            nuevoProducto.setEan13((int) ean13);
            nuevoProducto.setKeyRFID(rfid);

            if (categoriaSeleccionada != null) {
                Integer idCategoria = categoriasMap.get(categoriaSeleccionada);
                Categoria categoria = new Categoria();
                categoria.setId(idCategoria);
                nuevoProducto.setCategoria(categoria); // Asociar categoría al producto
            }

            guardarEnBaseDatos(nuevoProducto);

            mostrarAlerta("Éxito", "Producto guardado exitosamente.", Alert.AlertType.INFORMATION);
            volverAProductos();
        } catch (NumberFormatException e) {
            mostrarAlerta("Error de Validación", "El campo EAN13 debe ser un número válido.", Alert.AlertType.ERROR);
        }
    }

    private void guardarEnBaseDatos(Producto producto) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(producto);
            session.getTransaction().commit();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo guardar el producto en la base de datos.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void volverAProductos() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("productos-view.fxml"));
            AnchorPane productosVista = loader.load();

            Stage stage = (Stage) btnCancelar.getScene().getWindow();
            Scene scene = new Scene(productosVista);
            stage.setScene(scene);
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo volver a la vista de productos.", Alert.AlertType.ERROR);
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
