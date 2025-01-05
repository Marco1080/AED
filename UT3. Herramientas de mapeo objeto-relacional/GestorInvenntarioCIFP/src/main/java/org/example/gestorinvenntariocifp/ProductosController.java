package org.example.gestorinvenntariocifp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import org.example.gestorinvenntariocifp.modelos.Producto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductosController {

    @FXML
    private AnchorPane vistaPrincipal;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, Integer> colId;

    @FXML
    private TableColumn<Producto, String> colDescripcion;

    @FXML
    private TableColumn<Producto, Long> colEan13;

    @FXML
    private TableColumn<Producto, String> colRfid;

    @FXML
    private TextField txtBuscar;

    @FXML
    private Button btnBuscar;

    @FXML
    private Button btnAnadirProducto;

    @FXML
    private Button btnAtras;

    private ObservableList<Producto> productosObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        if (vistaPrincipal == null) {
            System.out.println("Error: vistaPrincipal no está inicializada. Verifica el fx:id en productos-view.fxml.");
        } else {
            System.out.println("vistaPrincipal inicializada correctamente.");
        }

        // Configurar columnas
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEan13.setCellValueFactory(new PropertyValueFactory<>("ean13"));
        colRfid.setCellValueFactory(new PropertyValueFactory<>("keyRFID"));

        // Cargar datos
        cargarProductos();

        // Acción para buscar productos
        btnBuscar.setOnAction(event -> buscarProductos());

        // Acción para añadir producto
        btnAnadirProducto.setOnAction(event -> abrirVistaNuevoProducto());

        // Acción para regresar al menú principal
        btnAtras.setOnAction(event -> volverAlMenu());
    }

    private void cargarProductos() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            // Consulta para obtener todos los productos
            List<Producto> productos = session.createQuery("from Producto", Producto.class).list();
            productosObservableList.setAll(productos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Establecer los datos en la tabla
        tablaProductos.setItems(productosObservableList);
    }

    private void buscarProductos() {
        String filtro = txtBuscar.getText().trim().toLowerCase();
        if (filtro.isEmpty()) {
            tablaProductos.setItems(productosObservableList);
        } else {
            List<Producto> filtrados = productosObservableList.stream()
                    .filter(producto -> producto.getDescripcion() != null &&
                            producto.getDescripcion().toLowerCase().contains(filtro))
                    .collect(Collectors.toList());
            tablaProductos.setItems(FXCollections.observableArrayList(filtrados));
        }
    }

    private void abrirVistaNuevoProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nuevoProducto-view.fxml"));
            AnchorPane nuevaVista = loader.load();
            vistaPrincipal.getChildren().setAll(nuevaVista);
        } catch (IOException e) {
            System.err.println("Error al cargar la vista nuevoProducto-view.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void volverAlMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            AnchorPane menuVista = loader.load();
            vistaPrincipal.getChildren().setAll(menuVista);
        } catch (IOException e) {
            System.err.println("Error al cargar la vista del menú: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
