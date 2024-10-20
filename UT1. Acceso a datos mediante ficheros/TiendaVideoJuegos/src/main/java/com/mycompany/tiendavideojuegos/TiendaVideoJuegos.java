/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.tiendavideojuegos;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author tonyi
 */
public class TiendaVideoJuegos {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Utilities utilities = new Utilities();
        ArrayList<Juego> listaJuegos = utilities.generarCopiaBinaria(".\\metacritic_games.csv");
        utilities.generarCopiaAleatoriaOrdenada(listaJuegos);
        utilities.generarEstructuraXML(listaJuegos);
        utilities.generarEstructuraCarpetasDesdeXML(".\\juegos.xml");
        utilities.generarSistemaCarpetasPorGenero(".\\juegos.dat");
        utilities.mostrarJuegosMasDispares(".\\juegos.dat");
        utilities.buscarJuegosMayores18();
    }
}
