package com.mycompany.databasebackup;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public static void generarBackUp(Connection connection, ResultSetMetaData data) {
        try {
            String nuevaTabla = data.getTableName(1) + "new";
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + nuevaTabla + " (";
            int numeroColumnas = data.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                sqlCreateTable += data.getColumnName(i) + " " + data.getColumnTypeName(i);
                if (!data.getColumnTypeName(i).equals("INT UNSIGNED")) {
                    sqlCreateTable += "(" + data.getColumnDisplaySize(i) + ")";
                }
                if (i < numeroColumnas) {
                    sqlCreateTable += ",";
                }
            }
            sqlCreateTable += ");";
            PreparedStatement psCreate = connection.prepareStatement(sqlCreateTable);
            psCreate.executeUpdate();
            guardarCopiaRegistros(connection, data.getTableName(1), nuevaTabla);
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void guardarCopiaRegistros(Connection connection, String tabla, String nuevaTabla) {
        String sqlSelect = "SELECT * FROM " + tabla;
        try {
            PreparedStatement psSelect = connection.prepareStatement(sqlSelect);
            ResultSet registros = psSelect.executeQuery();
            int numeroColumnas = registros.getMetaData().getColumnCount();
            while (registros.next()) {
                StringBuilder sqlInsert = new StringBuilder("INSERT INTO " + nuevaTabla + " VALUES(");
                for (int i = 1; i <= numeroColumnas; i++) {
                    sqlInsert.append("?");
                    if (i < numeroColumnas) {
                        sqlInsert.append(",");
                    }
                }
                sqlInsert.append(");");
                PreparedStatement psInsert = connection.prepareStatement(sqlInsert.toString());
                for (int i = 1; i <= numeroColumnas; i++) {
                    psInsert.setObject(i, registros.getObject(i));
                }
                psInsert.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseUtilities.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
