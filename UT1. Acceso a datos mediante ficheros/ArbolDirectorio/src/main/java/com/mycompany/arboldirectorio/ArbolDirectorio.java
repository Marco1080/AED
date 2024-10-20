package com.mycompany.arboldirectorio;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.File;
import java.nio.file.*;

public class ArbolDirectorio {

    public void generarArbolDesdeXML(String rutaXML) {
        try {
            File archivoXML = new File(rutaXML);

            if (!archivoXML.exists()) {
                System.out.println("Se ha creado uno por defecto en la raíz.");
                crearArchivoXMLPorDefecto(archivoXML);
            }

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document documento = builder.parse(archivoXML);
            documento.getDocumentElement().normalize();

            Path directorioBase = Paths.get(".").toAbsolutePath().normalize().resolve("Programaciones");
            if (!Files.exists(directorioBase)) {
                Files.createDirectories(directorioBase);
            }

            NodeList listaProgramas = documento.getDocumentElement().getChildNodes();
            for (int i = 0; i < listaProgramas.getLength(); i++) {
                Node programa = listaProgramas.item(i);

                if (programa.getNodeType() == Node.ELEMENT_NODE) {
                    String nombrePrograma = programa.getNodeName();
                    Path rutaPrograma = directorioBase.resolve(nombrePrograma);
                    Files.createDirectories(rutaPrograma);

                    NodeList niveles = programa.getChildNodes();
                    for (int j = 0; j < niveles.getLength(); j++) {
                        Node nivel = niveles.item(j);

                        if (nivel.getNodeType() == Node.ELEMENT_NODE) {
                            String nombreNivel = nivel.getNodeName();
                            Path rutaNivel = rutaPrograma.resolve(nombreNivel);
                            Files.createDirectories(rutaNivel);
                        }
                    }
                }
            }

            System.out.println("Árbol de directorios generado correctamente en la raíz del proyecto.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void crearArchivoXMLPorDefecto(File archivoXML) {
        try {
            String contenidoXML = """
            <?xml version="1.0" encoding="UTF-8"?>
            <Programaciones>
                <SMR>
                    <primero/>
                    <segundo/>
                </SMR>
                <DAM>
                    <primero/>
                    <segundo/>
                </DAM>
                <DAW>
                    <primero/>
                    <segundo/>
                </DAW>
                <ASIR>
                    <primero/>
                    <segundo/>
                </ASIR>
            </Programaciones>
            """;

            Files.write(archivoXML.toPath(), contenidoXML.getBytes());
            System.out.println("Archivo XML por defecto creado en: " + archivoXML.getAbsolutePath());

        } catch (Exception e) {
            System.err.println("Error al crear el archivo XML por defecto.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArbolDirectorio arbol = new ArbolDirectorio();
        arbol.generarArbolDesdeXML("arbolDirectorios.xml");
    }
}
