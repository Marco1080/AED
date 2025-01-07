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
    private Integer ean13;

    @Column(name = "keyRFID", nullable = false, length = 10)
    private String keyRFID;

    @Column(name = "IdCategoria", nullable = true) // Se permite que sea NULL
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

    public Integer getEan13() {
        return ean13;
    }

    public void setEan13(Integer ean13) {
        this.ean13 = ean13;
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
                ", ean13=" + ean13 +
                ", keyRFID='" + keyRFID + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
