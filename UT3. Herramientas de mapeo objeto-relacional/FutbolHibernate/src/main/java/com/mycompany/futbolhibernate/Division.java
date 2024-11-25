package com.mycompany.futbolhibernate;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "divisions")
public class Division {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nombre;

    private String pais;

    @OneToMany(mappedBy = "division", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Match> partidos = new ArrayList<>();

    public Division() {}

    public Division(String nombre, String pais) {
        this.nombre = nombre;
        this.pais = pais;
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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Match> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Match> partidos) {
        this.partidos = partidos;
    }

    @Override
    public String toString() {
        return "Division{id=" + id + ", nombre='" + nombre + '\'' + ", pais='" + pais + '\'' + '}';
    }
}
