package org.example.gestorinvenntariocifp.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProducto", nullable = false)
    private Integer id;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(name = "EAN13", nullable = false)
    private int ean13;

    @Column(name = "keyRFID", nullable = false, length = 10)
    private String keyRFID;

    @ManyToOne
    @JoinColumn(name = "IdCategoria")
    private Categoria categoria;

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
        return ean13;
    }

    public void setEan13(int ean13) {
        this.ean13 = ean13;
    }

    public String getKeyRFID() {
        return keyRFID;
    }

    public void setKeyRFID(String keyRFID) {
        this.keyRFID = keyRFID;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        if (this.categoria == null) {
            this.categoria = new Categoria();
        }
        this.categoria.setId(idCategoria);
    }
}
