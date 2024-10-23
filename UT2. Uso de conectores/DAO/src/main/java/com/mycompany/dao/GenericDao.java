/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

/**
 *
 * @author tonyi
 */
public interface GenericDao<T, ID> {
    // Método para guardar un nuevo objeto en la base de datos 

    int add(T entity);

    // Método para actualizar un objeto existente en la base de datos 
    int update(T entity);

    // Método para eliminar un objeto de la base de datos 
    int delete(T entity);

    // Método para buscar un objeto por su ID 
    T getbById(ID id);

    // Método para obtener todos los objetos de una tabla/entidad 
    //Arraylist<T> getAll(); 
}
