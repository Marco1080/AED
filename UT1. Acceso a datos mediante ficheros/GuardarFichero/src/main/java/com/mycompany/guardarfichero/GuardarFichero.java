/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.guardarfichero;

import java.io.File;
import java.io.IOException;

/**
 *
 * @author tonyi
 */
public class GuardarFichero {

    public static void main(String[] args) throws IOException {
        
        File input = new File("./input.txt");
        File delete = new File("./delete.txt");
        
        guardarDiferencias.generarFicherosPrueba();
        guardarDiferencias.diferencia(input, delete);
    }
}
