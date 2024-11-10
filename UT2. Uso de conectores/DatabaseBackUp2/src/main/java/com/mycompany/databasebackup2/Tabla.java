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
public class Tabla {

    String nombre;
    ArrayList<Campo> listaCampos;

    public Tabla(String nombre, ArrayList<Campo> listaCampos) {
        this.nombre = nombre;
        this.listaCampos = listaCampos;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        for (Campo campo : listaCampos) {
            result = 31 * result + (campo != null ? campo.hashCode() : 0);
        }
        return result;
    }

}
