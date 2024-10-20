/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.busquedadeficherosendirectorio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author tonyi
 */
public class Utilities {

    public File[] obtenerListaFicheros(String path) throws IOException {
        File file = new File(path);
        File[] fileList = file.listFiles();

        return fileList;
    }

    public void leerFicheros(File[] fileList) throws FileNotFoundException, IOException {
        String linea;
        File file = new File("./coincidencias.txt");
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for (File f : fileList) {
                int lineaActual = 0;
                FileReader fr = null;
                BufferedReader br = null;
                try {
                    fr = new FileReader(f);
                    br = new BufferedReader(fr);
                    while ((linea = br.readLine()) != null) {
                        if (linea.trim().equals("profesor")) {

                            bw.write("Se ha encontrado profesor en el fichero "
                                    + f.getName()
                                    + " en la linea "
                                    + lineaActual);
                            bw.newLine();

                        } else if (linea.trim().equals("malo")) {
                            bw.write("Se ha encontrado malo en el fichero "
                                    + f.getName()
                                    + " en la linea "
                                    + lineaActual);
                            bw.newLine();
                        }
                        lineaActual++;
                    }
                } finally {
                    if (br != null) {
                        br.close();
                    }
                    if (fr != null) {
                        fr.close();
                    }
                }
            }
        } finally {
            if (bw != null) {
                bw.close();
            }
            if (fw != null) {
                fw.close();
            }
        }
    }

}
