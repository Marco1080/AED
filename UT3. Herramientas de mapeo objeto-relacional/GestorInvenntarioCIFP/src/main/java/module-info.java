module org.example.gestorinvenntariocifp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jakarta.persistence;
    requires org.hibernate.orm.core;
    requires java.naming;

    opens org.example.gestorinvenntariocifp to javafx.fxml;

    opens org.example.gestorinvenntariocifp.modelos to org.hibernate.orm.core, javafx.base;

    exports org.example.gestorinvenntariocifp;
}
