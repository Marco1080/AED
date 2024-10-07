/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.guardarfichero;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;

/**
 *
 * @author tonyi
 */
public class guardarDiferencias {
    
    public static void generarFicherosPrueba() throws IOException {
        File input = new File("./input.txt");
        File delete = new File("./delete.txt");
        BufferedWriter bwInput = new BufferedWriter(new FileWriter(input));
        BufferedWriter bwDelete = new BufferedWriter(new FileWriter(delete));
        bwInput.append("Toyota\nHonda\nFord\nBMW\nAudi\nMercedes");
        bwInput.close();
        bwDelete.append("Toyota\nFord\nMercedes\n");
        bwDelete.close();
    }
    
    public static void diferencia(File input, File delete) throws IOException {
        
        BufferedReader brInput = new BufferedReader(new FileReader(input));
        String inputLine, deleteLine;
        
        
        File output = new File("./output.txt");
        BufferedWriter bw = new BufferedWriter(new FileWriter(output));
        
        while ( (inputLine = brInput.readLine()) != null) {
            boolean isDiferent = true;
            BufferedReader brDelete = new BufferedReader(new FileReader(delete));
            
            while( (deleteLine = brDelete.readLine()) != null) {
                
                if ( inputLine.equals(deleteLine) ){
                    System.out.println(inputLine + " = " + deleteLine);
                    isDiferent = false;
                    break;
                }
            
            }
            if ( isDiferent ) {
                bw.append(inputLine + "\n");
            }
        }
        bw.close();
    }
}
