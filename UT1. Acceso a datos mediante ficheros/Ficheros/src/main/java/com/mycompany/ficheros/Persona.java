/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ficheros;

/**
 *
 * @author 2damb
 */
public class Persona {
    String nombre, apellidos, telefono, id, edad;

    public Persona(String nombre, String apellidos, String telefono, String id, String edad) {
        this.nombre = ajustarFormato(nombre);
        this.apellidos = ajustarFormato(apellidos);
        this.telefono = ajustarFormato(telefono);
        this.id = ajustarFormato(id);
        this.edad = ajustarFormato(edad);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }
    
    @Override
    public String toString() {
        return "NOMBRE:" + getNombre() 
                + " APELLIDOS:" + getApellidos() 
                + " TELEFONO:" + getTelefono()
                + " ID:" + getId()
                + " EDAD:" + getEdad();
    }
    
    public String ajustarFormato(String texto) {
        
        if (texto.length() > 20) {
            System.out.println(texto.length());
        }
        else if ( texto.length() < 20 ){
            texto = String.format("%-20s", texto);
            return texto;
        }
        return texto;
        
    }
}
