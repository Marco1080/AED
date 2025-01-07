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
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {
            try (Session session = sessionFactory.openSession()) {
                System.out.println("Conexión exitosa con la base de datos.");

                session.beginTransaction();

                List<Aula> aulas = session.createQuery("from Aula", Aula.class).list();
                System.out.println("Aulas:");
                aulas.forEach(aula -> System.out.printf("ID: %d, Numeración: %s, Descripción: %s, IP: %s%n",
                        aula.getId(), aula.getNumeracion(), aula.getDescripcion(), aula.getIp()));

                List<Producto> productos = session.createQuery("from Producto", Producto.class).list();
                System.out.println("\nProductos:");
                productos.forEach(producto -> System.out.printf("ID: %d, Descripción: %s, EAN13: %d, RFID: %s%n",
                        producto.getId(), producto.getDescripcion(), producto.getEan13(), producto.getKeyRFID()));

                List<Marcaje> marcajes = session.createQuery("from Marcaje", Marcaje.class).list();
                System.out.println("\nMarcajes:");
                marcajes.forEach(marcaje -> System.out.printf("ID: %d, Producto ID: %d, Aula ID: %d, Tipo: %s, Timestamp: %s%n",
                        marcaje.getId(), marcaje.getIdProducto().getId(), marcaje.getIdAula().getId(), marcaje.getTipo(), marcaje.getTimeStamp()));

                session.getTransaction().commit();
            }
        } catch (Exception e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
