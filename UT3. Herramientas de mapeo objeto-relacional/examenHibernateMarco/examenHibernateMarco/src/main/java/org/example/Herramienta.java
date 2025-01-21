package org.example;

import jakarta.persistence.*;
@Entity
@Table(name = "Herramientas")
public class Herramienta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHerramienta",length = 255, nullable = false)
    private int idHerramienta;

    @Column(name = "nombre", length = 255)
    private String nombre;

    @Column(name = "paleta", length = 255)
    private String paleta;

    @Column(name = "brocha", length = 255)
    private String brocha;

    @Column(name = "tornillo", length = 255)
    private String tornillo;

    @Column(name = "marca", length = 255)
    private String marca;

    @Column(name = "precio", length = 255)
    private double precio;

    public Herramienta() {}

    public Herramienta(String nombre, String paleta, String brocha, String tornillo, String marca, double precio) {
        this.nombre = nombre;
        this.paleta = paleta;
        this.brocha = brocha;
        this.tornillo = tornillo;
        this.marca = marca;
        this.precio = precio;
    }

    public int getIdHerramienta() {
        return idHerramienta;
    }

    public void setIdHerramienta(int idHerramienta) {
        this.idHerramienta = idHerramienta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaleta() {
        return paleta;
    }

    public void setPaleta(String paleta) {
        this.paleta = paleta;
    }

    public String getBrocha() {
        return brocha;
    }

    public void setBrocha(String brocha) {
        this.brocha = brocha;
    }

    public String getTornillo() {
        return tornillo;
    }

    public void setTornillo(String tornillo) {
        this.tornillo = tornillo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Herramienta{" +
                "idHerramienta=" + idHerramienta +
                ", nombre='" + nombre + '\'' +
                ", paleta='" + paleta + '\'' +
                ", brocha='" + brocha + '\'' +
                ", tornillo='" + tornillo + '\'' +
                ", marca='" + marca + '\'' +
                ", precio=" + precio +
                '}';
    }
}
