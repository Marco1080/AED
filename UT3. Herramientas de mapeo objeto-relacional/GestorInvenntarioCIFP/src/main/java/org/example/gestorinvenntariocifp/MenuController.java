package org.example.gestorinvenntariocifp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class MenuController {

    @FXML
    private Button btnMarcajesProducto;

    @FXML
    private Button btnAulas;

    @FXML
    private Button btnMarcajesProductoAula;

    @FXML
    private Button btnProductosSinCategoria;

    @FXML
    private Button btnSalir;

    @FXML
    private MenuItem menuExportarPDF;

    @FXML
    private MenuItem menuGuardar;

    @FXML
    private MenuItem menuSalir;

    @FXML
    private MenuItem menuVersionActual;

    @FXML
    private MenuItem menuAcercaDe;

    @FXML
    private CheckMenuItem menuModoNocturno;

    @FXML
    private AnchorPane vistaPrincipal;

    @FXML
    public void initialize() {
        btnMarcajesProducto.setOnAction(event -> mostrarMarcajesProducto());
        btnMarcajesProductoAula.setOnAction(event -> mostrarMarcajesProductoAula());
        btnProductosSinCategoria.setOnAction(event -> mostrarCategorias());
        btnSalir.setOnAction(event -> volverInicio());
        btnAulas.setOnAction(event -> mostrarAulas());

        menuExportarPDF.setOnAction(event -> exportarAPDF());
        menuGuardar.setOnAction(event -> guardarCambios());
        menuSalir.setOnAction(event -> salirAplicacion());
        menuVersionActual.setOnAction(event -> mostrarVersionActual());
        menuAcercaDe.setOnAction(event -> mostrarAcercaDe());
        menuModoNocturno.setOnAction(event -> alternarModoNocturno());
    }

    private void mostrarMarcajesProducto() {
        cargarVista("marcajes-view.fxml");
    }

    private void mostrarMarcajesProductoAula() {
        cargarVista("productos-view.fxml");
    }

    private void mostrarCategorias() {
        cargarVista("categoria-view.fxml");
    }

    private void volverInicio() {
        cargarVista("inicio-view.fxml");
    }

    private void mostrarAulas() {
        cargarVista("aulas-view.fxml");
    }

    private void cargarVista(String vista) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(vista));
            AnchorPane nuevaVista = loader.load();

            vistaPrincipal.getChildren().setAll(nuevaVista);
            AnchorPane.setTopAnchor(nuevaVista, 0.0);
            AnchorPane.setBottomAnchor(nuevaVista, 0.0);
            AnchorPane.setLeftAnchor(nuevaVista, 0.0);
            AnchorPane.setRightAnchor(nuevaVista, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void exportarAPDF() {
    }

    private void guardarCambios() {
    }

    private void salirAplicacion() {
        System.exit(0);
    }

    private void mostrarVersionActual() {
    }

    private void mostrarAcercaDe() {
    }

    private void alternarModoNocturno() {
        if (menuModoNocturno.isSelected()) {
            vistaPrincipal.setStyle("-fx-background-color: #2b2b2b;");
            vistaPrincipal.lookupAll(".vbox").forEach(node -> node.setStyle("-fx-background-color: #3c3f41; -fx-text-fill: white;"));
            vistaPrincipal.lookupAll(".table-view").forEach(node -> node.setStyle("-fx-background-color: #3c3f41; -fx-border-color: #555555;"));
            vistaPrincipal.lookupAll(".button").forEach(node -> node.setStyle("-fx-background-color: #555555; -fx-text-fill: white;"));
        } else {
            vistaPrincipal.setStyle("-fx-background-color: #ffffff;");
            vistaPrincipal.lookupAll(".vbox").forEach(node -> node.setStyle("-fx-background-color: #ffffff; -fx-text-fill: black;"));
            vistaPrincipal.lookupAll(".table-view").forEach(node -> node.setStyle("-fx-background-color: #ffffff; -fx-border-color: #ced4da;"));
            vistaPrincipal.lookupAll(".button").forEach(node -> node.setStyle("-fx-background-color: #007bff; -fx-text-fill: white;"));
        }
    }

}
