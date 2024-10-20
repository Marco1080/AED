/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tiendavideojuegos;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author tonyi
 */
public class TiendaVideoJuegos {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Utilities utilities = new Utilities();
        utilities.generarCopiaBinaria("./metacritic_games.csv");
    }
}
