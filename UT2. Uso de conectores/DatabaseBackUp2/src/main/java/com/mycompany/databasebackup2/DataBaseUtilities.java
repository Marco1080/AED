/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.databasebackup2;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tonyi
 */
public class DataBaseUtilities {

    public BaseDeDatos mapearBaseDeDatos() {
        ArrayList<Tabla> listaTablas = new ArrayList<Tabla>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/animales", "root", "");
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet rs = metaData.getTables("animales", null, "%", new String[]{"TABLE"});
            while (rs.next()) {
                ArrayList<Campo> listaCampos = new ArrayList<Campo>();
                ResultSet campos = metaData.getColumns("animales", null, rs.getString("TABLE_NAME"), "%");
                while (campos.next()) {
                    listaCampos.add(new Campo(campos.getString("COLUMN_NAME"), campos.getString("TYPE_NAME"), Integer.parseInt(campos.getString("COLUMN_SIZE"))));
                }
                listaTablas.add(new Tabla(rs.getString("TABLE_NAME"), listaCampos));

            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataBaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        BaseDeDatos baseDeDatos;
        try {
            baseDeDatos = new BaseDeDatos(connection.getCatalog(), listaTablas);
            return baseDeDatos;
        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void crearCopiaBaseDeDatos(BaseDeDatos db) {
        Connection connection = null;
        String dbName = db.nombre + "New";
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306", "root", "");
            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            System.out.println(dbName);
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute();
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306" + "/" + dbName, "root", "");
            for (Tabla base : db.listaTablas) {

                String sqlTabla = "CREATE TABLE IF NOT EXISTS " + base.nombre
                        + "( ";
                int i = 0;
                for (Campo c : base.listaCampos) {

                    sqlTabla += c.nombre
                            + " " + c.tipo;
                    if (!c.tipo.contains("INT")) {
                        sqlTabla += "(" + c.longitud + ")";
                    }
                    if (i < base.listaCampos.size() - 1) {
                        sqlTabla += ", ";
                        i++;
                    }

                }
                sqlTabla += ");";
                PreparedStatement psTable = connection.prepareStatement(sqlTabla);
                psTable.execute();
            }

        } catch (SQLException ex) {
            Logger.getLogger(DataBaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ex) {
                    Logger.getLogger(DataBaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}