package org.example.gestorinvenntariocifp.modelos;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "marcaje", schema = "inventario")
public class Marcaje {
    @Id
    @Column(name = "IdMarcaje", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "IdProducto", nullable = false)
    private org.example.gestorinvenntariocifp.modelos.Producto idProducto;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "IdAula", nullable = false)
    private Aula idAula;

    @Column(name = "Tipo", nullable = false)
    private Integer tipo;

    @Column(name = "TimeStamp", nullable = false)
    private Instant timeStamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public org.example.gestorinvenntariocifp.modelos.Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(org.example.gestorinvenntariocifp.modelos.Producto idProducto) {
        this.idProducto = idProducto;
    }

    public Aula getIdAula() {
        return idAula;
    }

    public void setIdAula(Aula idAula) {
        this.idAula = idAula;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Marcaje{" +
                "id=" + id +
                ", idProducto=" + idProducto +
                ", idAula=" + idAula +
                ", tipo=" + tipo +
                ", timeStamp=" + timeStamp +
                '}';
    }
}