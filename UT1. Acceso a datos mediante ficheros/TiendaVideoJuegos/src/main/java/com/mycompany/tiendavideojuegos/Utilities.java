package com.mycompany.tiendavideojuegos;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Utilities {

    public void generarCopiaBinaria(String ruta) throws IOException {
        File file = new File(ruta);
        ArrayList<Juego> listaJuegos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine(); // Descartar encabezado
            String linea;

            while ((linea = br.readLine()) != null) {
                Juego juego = generarObjeto(linea);
                if (juego != null) {
                    listaJuegos.add(juego);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Utilities.class.getName()).log(Level.SEVERE, "Error de lectura: " + ex.getMessage(), ex);
        }

        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(".\\juegos.dat"))) {
            for (Juego juego : listaJuegos) {
                dos.writeUTF(juego.getNombre());
                dos.writeUTF(juego.getPlataforma());
                dos.writeUTF(juego.getDesarrollador());
                dos.writeUTF(juego.getGenero());
                dos.writeUTF(juego.getNumeroJugadores());
                dos.writeUTF(juego.getClasificacionEdad());
                dos.writeUTF(juego.getFechaLanzamiento());
                dos.writeInt(juego.getCriticasPositivas());
                dos.writeInt(juego.getCriticasNeutrales());
                dos.writeInt(juego.getCriticasNegativas());
                dos.writeInt(juego.getResenasPositivas());
                dos.writeInt(juego.getResenasNeutrales());
                dos.writeInt(juego.getResenasNegativas());
                dos.writeDouble(juego.getMetaScore());
                dos.writeDouble(juego.getUserScore());
            }
        } catch (IOException e) {
        }
    }

    public Juego generarObjeto(String linea) {
        String[] datos = linea.split(";");
        if (datos.length < 15) {
            System.out.println("Datos incompletos: " + linea);
            return null;
        }

        String nombre = datos[0];
        String plataforma = datos[1];
        String desarrollador = datos[2];
        String genero = datos[3];
        String numeroJugadores = datos[4].isEmpty() ? "Desconocido" : datos[4];
        String clasificacionEdad = datos[5];
        String fechaLanzamiento = datos[6];

        int criticasPositivas = parseIntSafely(datos[7]);
        int criticasNeutrales = parseIntSafely(datos[8]);
        int criticasNegativas = parseIntSafely(datos[9]);
        int resenasPositivas = parseIntSafely(datos[10]);
        int resenasNeutrales = parseIntSafely(datos[11]);
        int resenasNegativas = parseIntSafely(datos[12]);
        double metaScore = parseDoubleSafely(datos[13]);
        double userScore = parseDoubleSafely(datos[14]);

        return new Juego(nombre, plataforma, desarrollador, genero, numeroJugadores, clasificacionEdad, fechaLanzamiento, criticasPositivas, criticasNeutrales, criticasNegativas, resenasPositivas, resenasNeutrales, resenasNegativas, metaScore, userScore);
    }

    private int parseIntSafely(String value) {
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            System.out.println("No se pudo parsear el entero: " + value);
            return 0;
        }
    }

    private double parseDoubleSafely(String value) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            System.out.println("No se pudo parsear el doble: " + value);
            return 0.0;
        }
    }
}
