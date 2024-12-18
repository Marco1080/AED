module org.example.proyectoinventario {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.proyectoinventario to javafx.fxml;
    exports org.example.proyectoinventario;
}