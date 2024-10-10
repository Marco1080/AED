/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dinosaurios;

/**
 *
 * @author tonyi
 */
public abstract class Dinosaurio {
    private String nombre;
    private int salud;
    private int voracidad;
    private double peso;
    private int tamaño;

    public Dinosaurio(String nombre, int salud, int voracidad) {
        this.nombre = nombre;
        this.salud = salud;
        this.voracidad = voracidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public void setVoracidad(int voracidad) {
        this.voracidad = voracidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getVoracidad() {
        return voracidad;
    }

    public abstract void comer();
    
}
        
