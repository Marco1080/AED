package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEmpleado",length = 255, nullable = false)
    private int idEmpleado;

    @Column(name = "nombreEmplado", length = 255)
    private String nombreEmplado;

    @Column(name = "fechaNacimiento", nullable = true)
    private Date fechaNacimiento;

    @Column(name = "precio")
    private double precio;

    @Column(name = "experiencia")
    private int experiencia;

    @ManyToOne
    @JoinColumn(name = "idLocalizacion", nullable = true)
    private Localizacion localizacion;

    public Empleado() {
    }

    public Empleado(String nombreEmplado, Date fechaNacimiento, double precio, int experiencia) {
        this.nombreEmplado = nombreEmplado;
        this.fechaNacimiento = fechaNacimiento;
        this.precio = precio;
        this.experiencia = experiencia;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombreEmplado() {
        return nombreEmplado;
    }

    public void setNombreEmplado(String nombreEmplado) {
        this.nombreEmplado = nombreEmplado;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "idEmpleado=" + idEmpleado +
                ", nombreEmplado='" + nombreEmplado + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", precio=" + precio +
                ", experiencia=" + experiencia +
                '}';
    }
}
