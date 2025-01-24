package org.example.gestorinvenntariocifp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
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

    @FXML
    private Button btnEliminar;

    @FXML
    private CheckBox chkSinCategoria; // Checkbox para filtrar productos sin categoría

    private ObservableList<Producto> productosObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colEan13.setCellValueFactory(new PropertyValueFactory<>("ean13"));
        colRfid.setCellValueFactory(new PropertyValueFactory<>("keyRFID"));

        cargarProductos();
        btnBuscar.setOnAction(event -> buscarProductos());
        btnAnadirProducto.setOnAction(event -> abrirVistaNuevoProducto());
        btnAtras.setOnAction(event -> volverAlMenu());
        btnEliminar.setOnAction(event -> eliminarProductoSeleccionado());
        chkSinCategoria.setOnAction(event -> filtrarProductosSinCategoria());
    }

    private void cargarProductos() {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            List<Producto> productos = session.createQuery("from Producto", Producto.class).list();
            productosObservableList.setAll(productos);
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private void filtrarProductosSinCategoria() {
        if (chkSinCategoria.isSelected()) {
            List<Producto> sinCategoria = productosObservableList.stream()
                    .filter(producto -> producto.getCategoria() == null) // Filtrar productos con categoría null
                    .collect(Collectors.toList());
            tablaProductos.setItems(FXCollections.observableArrayList(sinCategoria));
        } else {
            tablaProductos.setItems(productosObservableList); // Restaurar todos los productos
        }
    }

    private void abrirVistaNuevoProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("nuevoProducto-view.fxml"));
            AnchorPane nuevaVista = loader.load();
            vistaPrincipal.getChildren().setAll(nuevaVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void volverAlMenu() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            AnchorPane menuVista = loader.load();
            vistaPrincipal.getChildren().setAll(menuVista);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void eliminarProductoSeleccionado() {
        Producto productoSeleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (productoSeleccionado == null) {
            return;
        }

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Producto producto = session.get(Producto.class, productoSeleccionado.getId());
            if (producto != null) {
                session.delete(producto);
                session.getTransaction().commit();
                productosObservableList.remove(productoSeleccionado);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
