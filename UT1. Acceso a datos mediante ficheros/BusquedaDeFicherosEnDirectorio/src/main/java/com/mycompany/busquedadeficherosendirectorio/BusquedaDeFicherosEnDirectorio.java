/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.busquedadeficherosendirectorio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author tonyi
 */
public class BusquedaDeFicherosEnDirectorio {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Introduce la ruta absoluta del directorio: ");
        String path = scan.nextLine();
        Utilities utilities = new Utilities();
        File[] file = utilities.obtenerListaFicheros(path);
        utilities.leerFicheros(file);
        
        /*
        String linea;
        String ruta = scan.nextLine();
        File file = new File(ruta);
        File[] fileList = file.listFiles();
        for (File f : fileList) {
            System.out.println("Archivo: " + f.getName());
            File fileActual = new File(ruta + "\\" + f.getName());
            FileReader fr = new FileReader(fileActual);
            BufferedReader br = new BufferedReader(fr);
            System.out.println("CONTENIDO DEL FICHERO " + f.getName());
            while ((linea = br.readLine()) != null) {
                if(linea.trim().equals("hola")) {System.out.println("Se ha encontrado hola");}
                else {System.out.println(linea + " - No es un hola");}
            }
            
        }
        */
    }
}