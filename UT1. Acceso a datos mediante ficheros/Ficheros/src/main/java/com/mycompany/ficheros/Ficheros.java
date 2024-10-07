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
            int id = Integer.parseInt(datos[0].trim());
            int edad = Integer.parseInt(datos[3].trim());
            Persona persona = new Persona(nombre, apellidos, telefono, id, edad);
            listaPersonas.add(persona);
            
        }
        
        br.close();
        fr.close();
        
        //2. Escritura Secuencial en un Fichero Binario
        
        FileOutputStream fos = new FileOutputStream("./ficheroData.dat");
        DataOutputStream dos = new DataOutputStream(fos);
        for (Persona persona : listaPersonas) {
            
            dos.writeChars(persona.getNombre());
            dos.writeChars(persona.getApellidos());
            dos.writeChars(persona.getTelefono());
            dos.writeInt(persona.getId());
            dos.writeInt(persona.getEdad());
            
        }
        
        dos.close();
        fos.close();

        //3. Ordenación de Registros y Escritura en un Nuevo Fichero
        FileInputStream fis = new FileInputStream("./ficheroData.dat");
        DataInputStream dis = new DataInputStream(fis);
        /*
        String i;
        while (true) {
            int id = dis.readInt();
            String nombre = dis.readChar();
            String telefono = dis.readChar();
            
        }
        /*
        fis.close();
        dis.close();
        */
    }
    /*
    public static String getStringFromBytes(DataInput disInput, int numChars) throws IOException {
        String output = "";
        for (int i = 0; i < numChars; i++) {
            output = output + disInput.readChar();
        }       
        return output;
    }
    */
}
