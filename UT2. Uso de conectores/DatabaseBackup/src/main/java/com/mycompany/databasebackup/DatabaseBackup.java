/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.databasebackup;

import com.mysql.jdbc.Util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2damb
 */
public class DatabaseBackup {

    public static void main(String[] args) throws ClassNotFoundException {
        Connection connection = null;
        try {
            /*
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/animales", "root", "");
            String sql = "INSERT INTO animal (nombre, especie) VALUES (?, ?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString(1, "ErGato");
            preparedStmt.setString(2, "Gato");
            preparedStmt.execute();
            connection.close();*/

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/animales", "root", "");
            String sql = "SELECT * FROM animal";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            ResultSet rs = preparedStmt.executeQuery(sql);
            String registro;
            /*
            while(rs.next()){
                System.out.println(rs.getString("nombre") + " " + rs.getString("nombre"));
            }*/

            DatabaseUtilities utilities = new DatabaseUtilities();
            utilities.mapearTabla(rs.getMetaData());
            String sqlTablas = "SHOW TABLES";
            ResultSet rsSql = preparedStmt.executeQuery(sqlTablas);
            String sqlData;

            PreparedStatement preparedStmtTablas = connection.prepareStatement(sqlTablas);
            ResultSet rsTablas = preparedStmtTablas.executeQuery();
            System.out.println("Tablas de la base de datos:");
            utilities.listarTablas(rsTablas);

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("---BASE DE DATOS CERRADA---");
                } catch (SQLException ex) {
                    Logger.getLogger(DatabaseBackup.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}