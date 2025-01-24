package org.example.gestorinvenntariocifp.modelos;

import jakarta.persistence.*;
import javafx.beans.property.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCategoria")
    private int id;

    @Column(name = "Nombre", nullable = false)
    private String nombre;

    @Transient
    private int productosAsociados;

    public Categoria() {
    }

    public Categoria(int id, String nombre, int productosAsociados) {
        this.id = id;
        this.nombre = nombre;
        this.productosAsociados = productosAsociados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getProductosAsociados() {
        return productosAsociados;
    }

    public void setProductosAsociados(int productosAsociados) {
        this.productosAsociados = productosAsociados;
    }

    public IntegerProperty idProperty() {
        return new SimpleIntegerProperty(id);
    }

    public StringProperty nombreProperty() {
        return new SimpleStringProperty(nombre);
    }

    public IntegerProperty productosAsociadosProperty() {
        return new SimpleIntegerProperty(productosAsociados);
    }

}
