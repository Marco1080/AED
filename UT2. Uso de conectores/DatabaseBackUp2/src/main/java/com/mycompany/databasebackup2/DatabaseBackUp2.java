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
        db.mapearBaseDeDatos();

        for (Tabla tabla : db.listaTablas) {
            System.out.println("\nTabla " + tabla.nombre);
            System.out.println("Campos:");
            for(Campo c : tabla.listaCampos) {
                System.out.println("\t-" + c.nombre + " " + c.tipo + " " + c.longitud);
            }
        }
    }
}
