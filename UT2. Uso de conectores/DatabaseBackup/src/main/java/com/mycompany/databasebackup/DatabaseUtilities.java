/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databasebackup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2damb
 */
public class DatabaseUtilities {

    public static ArrayList<String> listarTablas(ResultSet data) {
        ArrayList<String> listaTablas = new ArrayList();
        try {
            while (data.next()) {
                listaTablas.add(data.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            return listaTablas;
        }
    }

    public static void mapearTabla(ResultSetMetaData data) {
        try {
            int numeroColumnas = data.getColumnCount();
            System.out.println("");
            for (int i = 1; i <= numeroColumnas; i++) {
                //System.out.print(data.getTableName(i));
                System.out.print(data.getColumnName(i));
                System.out.print(" " + data.getColumnTypeName(i) + "(" + data.getColumnDisplaySize(i) + ")" + " ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void generarBackUp(Connection connection, ResultSetMetaData data) {
        try {
            String nuevaTabla = data.getTableName(1) + "New";
            String sqlCreateTable = "CREATE TABLE " + nuevaTabla + " (";
            
            int numeroColumnas = data.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                sqlCreateTable += data.getColumnName(i) 
                        + " " + data.getColumnTypeName(i) 
                        + "("+ data.getColumnDisplaySize(i) +")";
                if( (i <= numeroColumnas -1)) {sqlCreateTable += ",";}
            }
            sqlCreateTable += " );";
            System.out.println(sqlCreateTable);
            //PreparedStatement ps = connection.prepareStatement(sqlCreateTable);

        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            int numeroColumnas = data.getColumnCount();
            System.out.println("");
            for (int i = 1; i <= numeroColumnas; i++) {
                System.out.print("Nombre de la tabla: " + data.getTableName(i));
                //System.out.print(data.getColumnName(i));
                System.out.print(" " + data.getColumnTypeName(i) + "(" + data.getColumnDisplaySize(i) + ")" + " ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
