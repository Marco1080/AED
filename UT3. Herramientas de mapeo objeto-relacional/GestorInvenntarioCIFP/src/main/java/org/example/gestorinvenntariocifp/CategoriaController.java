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

    private ObservableList<Categoria> categoriasObservableList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        configurarColumnas();
        cargarCategorias();
        btnEliminarCategoria.setOnAction(event -> eliminarCategoriaSeleccionada());
        btnActualizarCategoria.setOnAction(event -> actualizarCategoriaSeleccionada());
        btnVolverMenu.setOnAction(event -> volverAlMenu());
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
                    "SELECT c.IdCategoria, c.Nombre, COUNT(p.IdProducto) " +
                            "FROM categoria c LEFT JOIN productos p ON c.IdCategoria = p.IdCategoria " +
                            "GROUP BY c.IdCategoria, c.Nombre"
            ).list();

            for (Object[] categoria : categorias) {
                Categoria nuevaCategoria = new Categoria(
                        (Integer) categoria[0],
                        (String) categoria[1],
                        ((Number) categoria[2]).intValue()
                );
                categoriasObservableList.add(nuevaCategoria);
            }
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudieron cargar las categorías.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }

        tablaCategorias.setItems(categoriasObservableList);
    }

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
                            "UPDATE productos SET IdCategoria = NULL WHERE IdCategoria = :idCategoria"
                    )
                    .setParameter("idCategoria", categoriaSeleccionada.getId())
                    .executeUpdate();
            Categoria categoria = session.get(Categoria.class, categoriaSeleccionada.getId());
            if (categoria != null) {
                session.delete(categoria);
                session.getTransaction().commit();
                mostrarAlerta("Éxito", "Categoría eliminada exitosamente. Los productos ahora no tienen categoría.", Alert.AlertType.INFORMATION);
            } else {
                mostrarAlerta("Error", "No se encontró la categoría seleccionada en la base de datos.", Alert.AlertType.ERROR);
            }
            cargarCategorias();
        } catch (Exception e) {
            mostrarAlerta("Error", "No se pudo eliminar la categoría debido a un error inesperado.", Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }

    private void actualizarCategoriaSeleccionada() {
        Categoria categoriaSeleccionada = tablaCategorias.getSelectionModel().getSelectedItem();
        if (categoriaSeleccionada == null) {
            mostrarAlerta("Advertencia", "Debe seleccionar una categoría para actualizar.", Alert.AlertType.WARNING);
            return;
        }
        mostrarAlerta("Pendiente", "Funcionalidad de actualizar categoría aún no implementada.", Alert.AlertType.INFORMATION);
    }

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

    private void mostrarAlerta(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setContentText(contenido);
        alerta.setHeaderText(null);
        alerta.showAndWait();
    }
}
