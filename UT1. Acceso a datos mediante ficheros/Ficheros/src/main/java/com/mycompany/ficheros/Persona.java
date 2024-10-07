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
    String nombre, apellidos, telefono;
    int id, edad;

    public Persona(String nombre, String apellidos, String telefono, int id, int edad) {
        this.nombre = ajustarFormato(nombre, 10);
        this.apellidos = ajustarFormato(apellidos, 20);
        this.telefono = ajustarFormato(telefono, 20);
        this.id = id;
        this.edad = edad;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
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
    
    public String ajustarFormato(String texto, int longitud) {
        
        if (texto.length() > longitud) {
            return texto.substring(0, longitud);
        }
        else if ( texto.length() < longitud ){
            texto = String.format( "%-" + longitud + "s", texto );
            return texto;
        }
        return texto;
    }
}
