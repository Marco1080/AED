/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databasebackup2;

import java.util.ArrayList;

/**
 *
 * @author 2damb
 */
public class BaseDeDatos {

    String nombre;
    ArrayList<Tabla> listaTablas;

    public BaseDeDatos(String nombre, ArrayList<Tabla> listaTablas) {
        this.nombre = nombre;
        this.listaTablas = listaTablas;
    }

    @Override
    public int hashCode() {
        int result = nombre.hashCode();
        result = 31 * result + listaTablas.hashCode();
        return result;
    }

}
