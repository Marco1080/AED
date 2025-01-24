package org.example.gestorinvenntariocifp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.gestorinvenntariocifp.modelos.Categoria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.io.IOException;
import java.util.List;

public class CategoriaController {

    @FXML
    private TableView<Categoria> tablaCategorias;

    @FXML
    private TableColumn<Categoria, Integer> colIdCategoria;

    @FXML
    private TableColumn<Categoria, String> colNombreCategoria;

    @FXML
    private TableColumn<Categoria, Integer> colProductosAsociados;

    @FXML
    private Button btnEliminarCategoria;

    @FXML
    private Button btnActualizarCategoria;

    @FXML
    private Button btnVolverMenu;

    @FXML
    private Button btnCrearCategoria;

    private ObservableList<Categoria> categoriasObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarCategorias();
    }

    private void configurarColumnas() {
        colIdCategoria.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        colNombreCategoria.setCellValueFactory(cellData -> cellData.getValue().nombreProperty());
        colProductosAsociados.setCellValueFactory(cellData -> cellData.getValue().productosAsociadosProperty().asObject());
    }

    private void cargarCategorias() {
        categoriasObservableList.clear();
        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            List<Object[]> categorias = session.createNativeQuery(
                    "SELECT c.IdCategoria, c.Nombre, COUNT(p.IdProducto) AS TotalProductos " +
                            "FROM categoria c LEFT JOIN producto p ON c.IdCategoria = p.IdCategoria " +
                            "GROUP BY c.IdCategoria, c.Nombre"
            ).list();

            for (Object[] categoria : categorias) {
                int id = (Integer) categoria[0];
                String nombre = (String) categoria[1];
                int productosAsociados = ((Number) categoria[2]).intValue();

                Categoria nuevaCategoria = new Categoria(id, nombre, productosAsociados);
                categoriasObservableList.add(nuevaCategoria);
            }

            tablaCategorias.setItems(categoriasObservableList);

        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar las categorías. Verifica la base de datos.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    @FXML
    private void eliminarCategoriaSeleccionada() {
        Categoria categoriaSeleccionada = tablaCategorias.getSelectionModel().getSelectedItem();
        if (categoriaSeleccionada == null) {
            mostrarAlerta("Advertencia", "Debe seleccionar una categoría para eliminar.", Alert.AlertType.WARNING);
            return;
        }

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            session.createNativeQuery(
                    "UPDATE producto SET IdCategoria = NULL WHERE IdCategoria = :idCategoria"
            ).setParameter("idCategoria", categoriaSeleccionada.getId()).executeUpdate();

            Categoria categoria = session.get(Categoria.class, categoriaSeleccionada.getId());
            if (categoria != null) {
                session.delete(categoria);
                session.getTransaction().commit();

                categoriasObservableList.remove(categoriaSeleccionada);
                mostrarAlerta("Éxito", "Categoría eliminada exitosamente.", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se encontró la categoría seleccionada en la base de datos.", Alert.AlertType.ERROR);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo eliminar la categoría debido a un error inesperado.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void actualizarCategoriaSeleccionada() {
        mostrarAlerta("Información", "Funcionalidad no implementada todavía.", Alert.AlertType.INFORMATION);
    }
    @FXML
    private void volverAlMenu() {
        try {
            Stage stage = (Stage) btnVolverMenu.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("menu-view.fxml"));
            Scene menuScene = new Scene(fxmlLoader.load());
            stage.setScene(menuScene);
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la vista del menú.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }
    @FXML
    private void irANuevaCategoria() {
        try {
            Stage stage = (Stage) btnCrearCategoria.getScene().getWindow();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("NuevaCategoria-view.fxml"));
            Scene nuevaCategoriaScene = new Scene(fxmlLoader.load());
            stage.setScene(nuevaCategoriaScene);
        } catch (IOException e) {
            mostrarAlerta("Error", "No se pudo cargar la vista para crear una nueva categoría.", Alert.AlertType.ERROR);
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
