package com.mycompany.futbolhibernate;

import com.mycompany.futbolhibernate.Usuario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FutbolHibernate {

    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("/hibernate.cfg.xml");
        configuration.addAnnotatedClass(Usuario.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession()) {

            Transaction transaction = session.beginTransaction();

            Usuario usuario = new Usuario("Juan Mastonte", "mastonte@dolor.com");

            session.save(usuario);

            transaction.commit();

            System.out.println("Usuario guardado con éxito: " + usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
