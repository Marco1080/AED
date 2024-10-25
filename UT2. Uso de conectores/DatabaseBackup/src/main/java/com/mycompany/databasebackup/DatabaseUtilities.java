/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databasebackup;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2damb
 */
public class DatabaseUtilities {

    public static void listarTablas(ResultSet data) {
        try {
            while (data.next()) {
                String nombreTabla = data.getString(1);
                System.out.println("\t" + nombreTabla);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void mapearTabla(ResultSetMetaData data) {
        try {
            int numeroColumnas = data.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                System.out.println(data.getColumnName(i));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
