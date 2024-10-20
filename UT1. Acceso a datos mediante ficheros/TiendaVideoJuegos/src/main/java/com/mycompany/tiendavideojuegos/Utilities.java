package com.mycompany.tiendavideojuegos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class Utilities {

    public ArrayList<Juego> generarCopiaBinaria(String ruta) throws IOException {
        File file = new File(ruta);
        ArrayList<Juego> listaJuegos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            br.readLine();
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
        return listaJuegos;
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

    public void generarCopiaAleatoriaOrdenada(ArrayList<Juego> listaJuegos) {
        try (RandomAccessFile raf = new RandomAccessFile(".\\juegosOrdenado.dat", "rw")) {
            Collections.sort(listaJuegos);
            for (Juego juego : listaJuegos) {
                raf.writeUTF(juego.getNombre());
                raf.writeUTF(juego.getPlataforma());
                raf.writeUTF(juego.getDesarrollador());
                raf.writeUTF(juego.getGenero());
                raf.writeUTF(juego.getNumeroJugadores());
                raf.writeUTF(juego.getClasificacionEdad());
                raf.writeUTF(juego.getFechaLanzamiento());
                raf.writeInt(juego.getCriticasPositivas());
                raf.writeInt(juego.getCriticasNeutrales());
                raf.writeInt(juego.getCriticasNegativas());
                raf.writeInt(juego.getResenasPositivas());
                raf.writeInt(juego.getResenasNeutrales());
                raf.writeInt(juego.getResenasNegativas());
                raf.writeDouble(juego.getMetaScore());
                raf.writeDouble(juego.getUserScore());
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void generarEstructuraXML(ArrayList<Juego> listaJuegos) {
        try {
            Map<String, List<Juego>> juegosPorPlataforma = new HashMap<>();

            for (Juego juego : listaJuegos) {
                juegosPorPlataforma.computeIfAbsent(juego.getPlataforma(), k -> new ArrayList<>()).add(juego);
            }

            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element raiz = document.createElement("Juegos");
            document.appendChild(raiz);

            for (Map.Entry<String, List<Juego>> entrada : juegosPorPlataforma.entrySet()) {
                String plataforma = entrada.getKey();
                Element plataformaElemento = document.createElement("Plataforma");
                plataformaElemento.setAttribute("nombre", plataforma);
                raiz.appendChild(plataformaElemento);

                for (Juego juego : entrada.getValue()) {
                    Element juegoElemento = document.createElement("Juego");

                    crearElemento(document, juegoElemento, "Nombre", juego.getNombre());
                    crearElemento(document, juegoElemento, "Desarrollador", juego.getDesarrollador());
                    crearElemento(document, juegoElemento, "Género", juego.getGenero());
                    crearElemento(document, juegoElemento, "NúmeroJugadores", juego.getNumeroJugadores());
                    crearElemento(document, juegoElemento, "ClasificacionEdad", juego.getClasificacionEdad());
                    crearElemento(document, juegoElemento, "FechaLanzamiento", juego.getFechaLanzamiento());
                    crearElemento(document, juegoElemento, "CriticasPositivas", String.valueOf(juego.getCriticasPositivas()));
                    crearElemento(document, juegoElemento, "CriticasNeutrales", String.valueOf(juego.getCriticasNeutrales()));
                    crearElemento(document, juegoElemento, "CriticasNegativas", String.valueOf(juego.getCriticasNegativas()));
                    crearElemento(document, juegoElemento, "ResenasPositivas", String.valueOf(juego.getResenasPositivas()));
                    crearElemento(document, juegoElemento, "ResenasNeutrales", String.valueOf(juego.getResenasNeutrales()));
                    crearElemento(document, juegoElemento, "ResenasNegativas", String.valueOf(juego.getResenasNegativas()));
                    crearElemento(document, juegoElemento, "MetaScore", String.valueOf(juego.getMetaScore()));
                    crearElemento(document, juegoElemento, "UserScore", String.valueOf(juego.getUserScore()));

                    plataformaElemento.appendChild(juegoElemento);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("juegos.xml"));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);

            System.out.println("Archivo XML generado exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearElemento(Document document, Element parent, String nombre, String valor) {
        Element elemento = document.createElement(nombre);
        elemento.appendChild(document.createTextNode(valor));
        parent.appendChild(elemento);
    }

    public void generarEstructuraCarpetasDesdeXML(String rutaXML) {
        try {
            File archivoXML = new File(rutaXML);
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(archivoXML);
            document.getDocumentElement().normalize();

            File directorioPlataformas = new File("Plataformas");
            if (!directorioPlataformas.exists()) {
                directorioPlataformas.mkdir();
            }

            NodeList listaPlataformas = document.getElementsByTagName("Plataforma");

            for (int i = 0; i < listaPlataformas.getLength(); i++) {
                Element plataformaElemento = (Element) listaPlataformas.item(i);
                String nombrePlataforma = plataformaElemento.getAttribute("nombre").trim();

                File carpetaPlataforma = new File(directorioPlataformas, nombrePlataforma);
                if (!carpetaPlataforma.exists()) {
                    carpetaPlataforma.mkdir();
                }

                File archivoTxt = new File(carpetaPlataforma, nombrePlataforma + ".txt");
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTxt))) {
                    NodeList listaJuegos = plataformaElemento.getElementsByTagName("Juego");

                    for (int j = 0; j < listaJuegos.getLength(); j++) {
                        Element juegoElemento = (Element) listaJuegos.item(j);
                        String nombreJuego = juegoElemento.getElementsByTagName("Nombre").item(0).getTextContent().trim();
                        String desarrolladorJuego = juegoElemento.getElementsByTagName("Desarrollador").item(0).getTextContent().trim();
                        writer.write(nombreJuego + " - " + desarrolladorJuego);
                        writer.newLine();
                    }
                }
            }

            System.out.println("Estructura de carpetas y archivos generada exitosamente.");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void generarSistemaCarpetasPorGenero(String rutaDatos) {
        ArrayList<Juego> listaJuegos = leerJuegosDesdeDatos(rutaDatos);
        Map<String, ArrayList<Juego>> juegosPorGenero = new HashMap<>();

        for (Juego juego : listaJuegos) {
            if (juego.getMetaScore() >= 8) {
                String genero = juego.getGenero().trim();
                juegosPorGenero.putIfAbsent(genero, new ArrayList<>());
                juegosPorGenero.get(genero).add(juego);
            }
        }

        for (Map.Entry<String, ArrayList<Juego>> entry : juegosPorGenero.entrySet()) {
            String genero = entry.getKey().replaceAll("[\\\\/:*?\"<>|]", "_").trim();
            File carpetaGenero = new File("JuegosPorGenero/" + genero);
            if (!carpetaGenero.exists()) {
                carpetaGenero.mkdirs();
            }

            File archivoTxt = new File(carpetaGenero, genero + ".txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(archivoTxt))) {
                for (Juego juego : entry.getValue()) {
                    writer.write(juego.toString());
                    writer.newLine();
                }
            } catch (IOException e) {
                System.out.println("Error al escribir en el archivo: " + e.getMessage());
            }
        }

        System.out.println("Sistema de carpetas y archivos generado exitosamente.");
    }

    private ArrayList<Juego> leerJuegosDesdeDatos(String rutaDatos) {
        ArrayList<Juego> listaJuegos = new ArrayList<>();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(rutaDatos))) {
            while (true) {
                String nombre = dis.readUTF();
                String plataforma = dis.readUTF();
                String desarrollador = dis.readUTF();
                String genero = dis.readUTF();
                String numeroJugadores = dis.readUTF();
                String clasificacionEdad = dis.readUTF();
                String fechaLanzamiento = dis.readUTF();
                int criticasPositivas = dis.readInt();
                int criticasNeutrales = dis.readInt();
                int criticasNegativas = dis.readInt();
                int resenasPositivas = dis.readInt();
                int resenasNeutrales = dis.readInt();
                int resenasNegativas = dis.readInt();
                double metaScore = dis.readDouble();
                double userScore = dis.readDouble();

                Juego juego = new Juego(nombre, plataforma, desarrollador, genero, numeroJugadores, clasificacionEdad,
                        fechaLanzamiento, criticasPositivas, criticasNeutrales, criticasNegativas,
                        resenasPositivas, resenasNeutrales, resenasNegativas, metaScore, userScore);
                listaJuegos.add(juego);
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            System.out.println("Error al leer los datos: " + e.getMessage());
        }

        return listaJuegos;
    }

    public void mostrarJuegosMasDispares(String rutaDatos) {
        ArrayList<Juego> listaJuegos = leerJuegosDesdeDatos(rutaDatos);

        if (listaJuegos.isEmpty()) {
            System.out.println("No hay juegos disponibles.");
            return;
        }

        Juego juegoMayorDiferenciaCritico = null;
        Juego juegoMayorDiferenciaUsuario = null;
        double mayorDiferenciaCritico = Double.MIN_VALUE;
        double mayorDiferenciaUsuario = Double.MIN_VALUE;

        for (Juego juego : listaJuegos) {
            double diferenciaCritico = juego.getMetaScore() - juego.getUserScore();
            double diferenciaUsuario = juego.getUserScore() - juego.getMetaScore();

            if (diferenciaCritico > mayorDiferenciaCritico) {
                mayorDiferenciaCritico = diferenciaCritico;
                juegoMayorDiferenciaCritico = juego;
            }

            if (diferenciaUsuario > mayorDiferenciaUsuario) {
                mayorDiferenciaUsuario = diferenciaUsuario;
                juegoMayorDiferenciaUsuario = juego;
            }
        }

        if (juegoMayorDiferenciaCritico != null) {
            System.out.println("Juego con mayor diferencia de críticos sobre usuarios:");
            System.out.println(juegoMayorDiferenciaCritico);
            System.out.println("Diferencia: " + mayorDiferenciaCritico);
        }

        if (juegoMayorDiferenciaUsuario != null) {
            System.out.println("Juego con mayor diferencia de usuarios sobre críticos:");
            System.out.println(juegoMayorDiferenciaUsuario);
            System.out.println("Diferencia: " + mayorDiferenciaUsuario);
        }
    }

    public void buscarJuegosMayores18() {
        ArrayList<Juego> juegosMayores18 = new ArrayList<>();
        String ruta = ".\\juegos.dat";

        try (DataInputStream dis = new DataInputStream(new FileInputStream(ruta))) {
            while (true) {
                String nombre = dis.readUTF();
                String plataforma = dis.readUTF();
                String desarrollador = dis.readUTF();
                String genero = dis.readUTF();
                String numeroJugadores = dis.readUTF();
                String clasificacionEdad = dis.readUTF();
                String fechaLanzamiento = dis.readUTF();
                int criticasPositivas = dis.readInt();
                int criticasNeutrales = dis.readInt();
                int criticasNegativas = dis.readInt();
                int resenasPositivas = dis.readInt();
                int resenasNeutrales = dis.readInt();
                int resenasNegativas = dis.readInt();
                double metaScore = dis.readDouble();
                double userScore = dis.readDouble();

                Juego juego = new Juego(nombre, plataforma, desarrollador, genero,
                        numeroJugadores, clasificacionEdad, fechaLanzamiento,
                        criticasPositivas, criticasNeutrales, criticasNegativas,
                        resenasPositivas, resenasNeutrales, resenasNegativas,
                        metaScore, userScore);

                System.out.println("Leyendo juego: " + juego.getNombre() + ", Clasificación: " + juego.getClasificacionEdad());

                if ("M".equals(juego.getClasificacionEdad().trim())) {
                    juegosMayores18.add(juego);
                }
            }
        } catch (EOFException e) {
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }

        if (juegosMayores18.isEmpty()) {
            System.out.println("No se encontraron juegos para mayores de 18 años.");
            return;
        }

        System.out.println("Juegos para mayores de 18 años:");
        for (int i = 0; i < juegosMayores18.size(); i++) {
            Juego juego = juegosMayores18.get(i);
            System.out.println((i + 1) + ". " + juego.getNombre() + " - " + juego.getDesarrollador());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("Selecciona el número del juego para ver más detalles: ");
        int seleccion = scanner.nextInt() - 1;

        if (seleccion >= 0 && seleccion < juegosMayores18.size()) {
            Juego juegoSeleccionado = juegosMayores18.get(seleccion);
            mostrarDetallesJuego(juegoSeleccionado);
        } else {
            System.out.println("Selección no válida.");
        }
    }

    private void mostrarDetallesJuego(Juego juego) {
        System.out.println("\nDetalles del juego:");
        System.out.println("\tNombre: " + juego.getNombre());
        System.out.println("\tPlataforma: " + juego.getPlataforma());
        System.out.println("\tDesarrollador: " + juego.getDesarrollador());
        System.out.println("\tGénero: " + juego.getGenero());
        System.out.println("\tNúmero de Jugadores: " + juego.getNumeroJugadores());
        System.out.println("\tClasificación: " + juego.getClasificacionEdad());
        System.out.println("\tEdad recomendada para jugar: " + juego.getClasificacionEdad());
        System.out.println("\tFecha de Lanzamiento: " + juego.getFechaLanzamiento());
        System.out.println("\tMetaScore: " + juego.getMetaScore());
        System.out.println("\tUser Score: " + juego.getUserScore());
    }

}
