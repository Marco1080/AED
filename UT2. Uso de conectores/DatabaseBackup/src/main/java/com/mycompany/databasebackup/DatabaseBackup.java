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

            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/animales", "root", "");
            DatabaseUtilities utilities = new DatabaseUtilities();
            String databaseName = "animales";
            String sqlTablas = "SHOW TABLES";
            PreparedStatement preparedStmtTablas = connection.prepareStatement(sqlTablas);
            ResultSet rsTablas = preparedStmtTablas.executeQuery();
            for (String tableName : (utilities.listarTablas(rsTablas))) {
                String sql1 = "SELECT * FROM " + tableName;
                PreparedStatement preparedStmt1 = connection.prepareStatement(sql1);
                ResultSet rs1 = preparedStmt1.executeQuery(sql1);
                //utilities.mapearTabla(rs1.getMetaData());
                utilities.generarBackUp(connection, rs1.getMetaData());
            }

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
