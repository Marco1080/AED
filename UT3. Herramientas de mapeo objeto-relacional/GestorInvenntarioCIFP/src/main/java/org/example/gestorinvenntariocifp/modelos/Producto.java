package org.example.gestorinvenntariocifp.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "productos", schema = "inventario")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProducto", nullable = false)
    private Integer id;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(name = "EAN13", nullable = false)
    private int ean;

    @Column(name = "keyRFID", nullable = false, length = 10)
    private String keyRFID;

    @Column(name = "IdCategoria", nullable = true)
    private Integer idCategoria;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEan13() {
        return ean;
    }

    public void setEan13(int ean) {
        this.ean = ean;
    }

    public String getKeyRFID() {
        return keyRFID;
    }

    public void setKeyRFID(String keyRFID) {
        this.keyRFID = keyRFID;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", ean=" + ean +
                ", keyRFID='" + keyRFID + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
