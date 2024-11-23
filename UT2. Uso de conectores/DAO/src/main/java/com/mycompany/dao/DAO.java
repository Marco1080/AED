/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dao;

/**
 *
 * @author tonyi
 */
public class DAO {

    public static void main(String[] args) {
        
        AnimalDao animalDao = new AnimalDao();

        Animal gatito = new Animal(1,"Gatito", "Gato");
        int resultado = animalDao.add(gatito);
        System.out.println("Resultado de la inserción: " + resultado);
        
        if (resultado > 0) {
            System.out.println("Animal insertado exitosamente.");
        } else {
            System.out.println("Error al insertar el animal.");
        }

        System.out.println("Finalizando programa...");
    }
}
