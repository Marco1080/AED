package org.example.gestorinvenntariocifp.modelos;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "marcaje", schema = "inventario")
public class Marcaje {
    @Id
    @Column(name = "IdMarcaje", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false) // Cambiado a EAGER
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "IdProducto", nullable = false)
    private Producto idProducto;

    @ManyToOne(fetch = FetchType.EAGER, optional = false) // Cambiado a EAGER
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "IdAula", nullable = false)
    private Aula idAula;

    @Column(name = "Tipo", nullable = false)
    private Integer tipo;

    @Column(name = "TimeStamp", nullable = false)
    private Instant timeStamp;

    // Método para obtener el tipo en formato descriptivo
    public String getTipoDescripcion() {
        switch (tipo) {
            case 1:
                return "Entrada";
            case 2:
                return "Salida";
            case 3:
                return "Mantenimiento";
            default:
                return "Desconocido";
        }
    }

    // Método para obtener la fecha en formato legible
    public String getFormattedTimeStamp() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").withZone(java.time.ZoneId.systemDefault()).format(timeStamp);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Producto getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Producto idProducto) {
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
                ", idProducto=" + idProducto.getDescripcion() +
                ", idAula=" + idAula.getDescripcion() +
                ", tipo=" + getTipoDescripcion() +
                ", timeStamp=" + getFormattedTimeStamp() +
                '}';
    }
}
