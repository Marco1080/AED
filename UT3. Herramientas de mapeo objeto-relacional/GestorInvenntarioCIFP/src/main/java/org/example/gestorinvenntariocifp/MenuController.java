package org.example.gestorinvenntariocifp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

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
        // Mensaje de depuración para verificar la inicialización
        if (vistaPrincipal == null) {
            System.out.println("Error: vistaPrincipal no está inicializada. Verifica el fx:id en el archivo FXML.");
        } else {
            System.out.println("vistaPrincipal inicializada correctamente.");
        }

        // Asignar acciones a los botones
        btnMarcajesProducto.setOnAction(event -> mostrarMarcajesProducto());
        btnMarcajesProductoAula.setOnAction(event -> mostrarMarcajesProductoAula());
        btnProductosSinCategoria.setOnAction(event -> mostrarProductosSinCategoria());
        btnSalir.setOnAction(event -> volverInicio());
        btnAulas.setOnAction(event -> mostrarAulas());

        // Asignar acciones a los elementos del menú
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

    private void mostrarProductosSinCategoria() {
        cargarVista("productos-sin-categoria.fxml");
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
            vistaPrincipal.getChildren().setAll(nuevaVista); // Reemplazar el contenido de vistaPrincipal
        } catch (IOException e) {
            System.err.println("Error al cargar la vista: " + vista);
            e.printStackTrace();
        }
    }

    // Métodos para las opciones del menú
    private void exportarAPDF() {
        System.out.println("Exportar a PDF seleccionado.");
        // Implementar lógica de exportación a PDF
    }

    private void guardarCambios() {
        System.out.println("Guardar seleccionado.");
        // Implementar lógica para guardar cambios
    }

    private void salirAplicacion() {
        System.out.println("Salir seleccionado.");
        System.exit(0); // Salir de la aplicación
    }

    private void mostrarVersionActual() {
        System.out.println("Versión actual: 1.0.0");
        // Implementar ventana o mensaje emergente con la versión actual
    }

    private void mostrarAcercaDe() {
        System.out.println("Soporte.");
        // Implementar ventana o mensaje emergente con información "Acerca de"
    }

    private void alternarModoNocturno() {
        if (menuModoNocturno.isSelected()) {
            // Activar modo nocturno
            vistaPrincipal.setStyle("-fx-background-color: #2b2b2b;");
            aplicarEstiloRecursivamente(vistaPrincipal, "-fx-text-fill: white;");
        } else {
            // Desactivar modo nocturno (modo diurno)
            vistaPrincipal.setStyle("-fx-background-color: #ffffff;");
            aplicarEstiloRecursivamente(vistaPrincipal, "-fx-text-fill: black;");
        }
    }

    private void aplicarEstiloRecursivamente(AnchorPane nodo, String estiloTexto) {
        for (var child : nodo.getChildren()) {
            if (child instanceof Label) {
                ((Label) child).setStyle(estiloTexto);
            } else if (child instanceof Button) {
                ((Button) child).setStyle(estiloTexto);
            } else if (child instanceof MenuBar) {
                ((MenuBar) child).setStyle(estiloTexto);
            } else if (child instanceof VBox) {
                aplicarEstiloRecursivamente((AnchorPane) child, estiloTexto);
            }
        }
    }

}
