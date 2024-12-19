package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.openSession();
            ProductoCRUD productoCRUD = new ProductoCRUD(session);

            productoCRUD.eliminarPorId("1");

            // Listar productos con el nombre "The Witcher 3"

            Query<Producto> query = session.createQuery("FROM Producto WHERE nombreProducto = :nombre", Producto.class);
            query.setParameter("nombre", "The Witcher 3");
            List<Producto> productos = query.getResultList();

            for (Producto producto : productos) {
                System.out.println(producto);
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
