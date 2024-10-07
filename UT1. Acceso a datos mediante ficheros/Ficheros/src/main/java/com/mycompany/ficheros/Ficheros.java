/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.ficheros;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author tonyi
 */
public class Ficheros {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        //1. Lectura de Datos desde un Fichero de Texto
        ArrayList<Persona> listaPersonas = new ArrayList<Persona>();
        File file = new File("./input");
        FileReader fr = new FileReader(file);
        String line;
        BufferedReader br = new BufferedReader(fr);
        
        
        while ( ( line = br.readLine()) != null) {
            
            String[] datos = line.split(",");
            String nombre = datos[0].trim();
            String apellidos = datos[1].trim();
            String telefono = datos[4];
            String id = datos[0];
            String edad = datos[3].trim();
            Persona persona = new Persona(nombre, apellidos, telefono, id, edad);
            listaPersonas.add(persona);
            
        }
        
        br.close();
        fr.close();
        
        //2. Escritura Secuencial en un Fichero Binario
        
        FileOutputStream fos = new FileOutputStream("./ficheroData.dat");
        DataOutputStream dos = new DataOutputStream(fos);
        for (Persona persona : listaPersonas) {
            
            dos.writeUTF(persona.getNombre());
            dos.writeUTF(persona.getApellidos());
            dos.writeUTF(persona.getTelefono());
            dos.writeUTF(persona.getId());
            dos.writeUTF(persona.getEdad());
            
        }
        
        dos.close();
        fos.close();

        //3. Ordenación de Registros y Escritura en un Nuevo Fichero
        FileInputStream fis = new FileInputStream("./ficheroData.dat");
        DataInputStream dis = new DataInputStream(fis);
        
        while( true ) {
            System.out.println(dis.readUTF().trim());
        }
    }
}
