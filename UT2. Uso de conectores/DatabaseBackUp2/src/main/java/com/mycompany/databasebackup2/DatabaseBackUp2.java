/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.databasebackup2;

/**
 *
 * @author 2damb
 */
public class DatabaseBackUp2 {

    public static void main(String[] args) {
        DataBaseUtilities db = new DataBaseUtilities();
        BaseDeDatos base = db.mapearBaseDeDatos("animales");

        db.crearCopiaBaseDeDatos(base);
        db.copiarRegistro(base);
    }
}
