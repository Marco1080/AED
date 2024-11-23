/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author tonyi
 */
public class AnimalDao implements GenericDao<Animal, Integer> {

    private Connection connection;

    public AnimalDao() {

        try {
            this.connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/animales", "root", "");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int add(Animal animal) {
        String sql = "INSERT INTO Animal (nombre, especie) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getNombre());
            stmt.setString(2, animal.getEspecie());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int update(Animal animal) {
        String sql = "UPDATE Animal SET nombre = ?, especie = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, animal.getNombre());
            stmt.setString(2, animal.getEspecie());
            stmt.setInt(3, animal.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Animal animal) {
        String sql = "DELETE FROM Animal WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, animal.getId());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public Animal getbById(Integer id) {
        String sql = "SELECT * FROM Animal WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            var resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String especie = resultSet.getString("especie");
                Animal animal = new Animal(id, nombre, especie);
                return animal;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
