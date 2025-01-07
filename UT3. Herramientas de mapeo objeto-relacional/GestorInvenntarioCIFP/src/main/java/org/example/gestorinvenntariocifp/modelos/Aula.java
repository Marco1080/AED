package org.example.gestorinvenntariocifp.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "aula", schema = "inventario")
public class Aula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAula", nullable = false)
    private Integer id;

    @Column(name = "Numeracion", nullable = false, length = 5)
    private String numeracion;

    @Column(name = "Descripcion", nullable = false)
    private String descripcion;

    @Column(name = "IP", nullable = false, length = 15)
    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(String numeracion) {
        this.numeracion = numeracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @Override
    public String toString() {
        return "Aula{" +
                "id=" + id +
                ", numeracion='" + numeracion + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", ip='" + ip + '\'' +
                '}';
    }
}
