package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = new Configuration();
        configuration.configure("hibernate.cfg.xml");
        configuration.addAnnotatedClass(Division.class);
        configuration.addAnnotatedClass(Match.class);

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        try (Session session = sessionFactory.openSession()) {
            DivisionDAO divisionDAO = new DivisionDAO(session);

            Division division = divisionDAO.buscar("dvi1");
            if (division == null) {
                division = new Division("dvi1", "Premier League", "England");
                divisionDAO.insertar(division);
                System.out.println("División creada: " + division.getName());
            } else {
                System.out.println("División: " + division.getName());
            }
            MatchDAO matchDAO = new MatchDAO(session);

            Match match = new Match(
                    division,
                    LocalDate.of(2024, 12, 20), // Fecha del partido
                    "Manchester United","Manchester City",
                    2.0f, 3.0f,
                    "A", 2024
            );
            matchDAO.insertar(match);

            System.out.println("Partido creado: " + match.getHomeTeam() + " vs " + match.getAwayTeam());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sessionFactory.close();
        }
    }
}
