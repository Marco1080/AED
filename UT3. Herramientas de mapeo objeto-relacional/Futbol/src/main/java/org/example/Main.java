package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml"); // Cargar el archivo cfg.xml

        configuration.addAnnotatedClass(Division.class);
        configuration.addAnnotatedClass(Match.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory()) {

            Session session = sessionFactory.openSession();

            session.beginTransaction();

            Division division = new Division();
            division.setDivision("Primera");
            division.setName("Primera División");
            division.setCountry("España");
            session.save(division);


            session.getTransaction().commit();

            System.out.println("¡División creada y guardada en la base de datos!");

            List<Division> divisions = session.createQuery("from Division", Division.class).getResultList();

            for (Division div : divisions) {
                System.out.println("División: " + div.getDivision() + ", Nombre: " + div.getName() + ", País: " + div.getCountry());
            }

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
