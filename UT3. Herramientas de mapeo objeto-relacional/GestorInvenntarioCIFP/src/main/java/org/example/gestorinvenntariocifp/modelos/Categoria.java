package org.example.gestorinvenntariocifp.modelos;

import javafx.beans.property.*;

public class Categoria {
    private final IntegerProperty id;
    private final StringProperty nombre;
    private final IntegerProperty productosAsociados;

    public Categoria() {
        this.id = new SimpleIntegerProperty();
        this.nombre = new SimpleStringProperty();
        this.productosAsociados = new SimpleIntegerProperty(0);
    }

    public Categoria(int id, String nombre, int productosAsociados) {
        this.id = new SimpleIntegerProperty(id);
        this.nombre = new SimpleStringProperty(nombre);
        this.productosAsociados = new SimpleIntegerProperty(productosAsociados);
    }

    // Getters y setters para propiedades observables
    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public int getProductosAsociados() {
        return productosAsociados.get();
    }

    public void setProductosAsociados(int productosAsociados) {
        this.productosAsociados.set(productosAsociados);
    }

    public IntegerProperty productosAsociadosProperty() {
        return productosAsociados;
    }
}
