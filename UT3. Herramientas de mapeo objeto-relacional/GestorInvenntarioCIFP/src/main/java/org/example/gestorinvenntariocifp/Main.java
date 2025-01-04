package org.example.gestorinvenntariocifp;

import org.example.gestorinvenntariocifp.modelos.Aula;
import org.example.gestorinvenntariocifp.modelos.Producto;
import org.example.gestorinvenntariocifp.modelos.Marcaje;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Crear una configuración de Hibernate desde el archivo hibernate.cfg.xml
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        // Crear la fábrica de sesiones
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            // Abrir una sesión
            try (Session session = sessionFactory.openSession()) {
                System.out.println("¡Conexión exitosa con la base de datos!");

                // Iniciar transacción
                session.beginTransaction();

                // Consultar las aulas
                List<Aula> aulas = session.createQuery("from Aula", Aula.class).list();
                System.out.println("Aulas:");
                for (Aula aula : aulas) {
                    System.out.println("ID: " + aula.getId() +
                            ", Numeración: " + aula.getNumeracion() +
                            ", Descripción: " + aula.getDescripcion() +
                            ", IP: " + aula.getIp());
                }

                // Consultar los productos
                List<Producto> productos = session.createQuery("from Producto", Producto.class).list();
                System.out.println("\nProductos:");
                for (Producto producto : productos) {
                    System.out.println("ID: " + producto.getId() +
                            ", Descripción: " + producto.getDescripcion() +
                            ", EAN13: " + producto.getEan13() +
                            ", RFID: " + producto.getKeyRFID());
                }

                // Consultar los marcajes
                List<Marcaje> marcajes = session.createQuery("from Marcaje", Marcaje.class).list();
                System.out.println("\nMarcajes:");
                for (Marcaje marcaje : marcajes) {
                    System.out.println("ID: " + marcaje.getId() +
                            ", Producto ID: " + marcaje.getIdProducto().getId() +
                            ", Aula ID: " + marcaje.getIdAula().getId() +
                            ", Tipo: " + marcaje.getTipo() +
                            ", Timestamp: " + marcaje.getTimeStamp());
                }

                // Finalizar transacción
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
