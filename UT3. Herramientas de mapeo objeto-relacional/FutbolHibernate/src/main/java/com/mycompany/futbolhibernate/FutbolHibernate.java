package com.mycompany.futbolhibernate;

import com.mycompany.futbolhibernate.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FutbolHibernate {

    public static void main(String[] args) {

        Configuration configuration = new Configuration();
        configuration.configure("/hibernate.cfg.xml");

        configuration.addAnnotatedClass(Usuario.class);
        configuration.addAnnotatedClass(Division.class);
        configuration.addAnnotatedClass(Match.class);
        /*
        try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession()) {

            //Transaction transaction = session.beginTransaction();

            //Usuario usuario = new Usuario("Juan Mastonte", "mastonte@dolor.com");

            //session.persist(usuario);

            transaction.commit();

            System.out.println("Usuario guardado con éxito: " + usuario);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Scanner scan = new Scanner(System.in);
        boolean another = true;
        do {
            System.out.println("--->Menu<---"
                    + "\n1 - Generar base de datos."
                    + "\n2 - Crear una nueva division."
                    + "\n3 - Crear un nuevo partido."
                    + "\n4 - Listar equipos."
                    + "\n5 - Listar divisiones."
                    + "\n6 - Editar equipo."
                    + "\n7 - Editar division."
                    + "\n8 - Eliminar equipo."
                    + "\n9 - Eliminar division."
                    + "\n9 - Generar datos de prueba."
                    + "\n0 - Salir.\n");
            int option = scan.nextInt();
            switch (option) {
                case 1:
                    break;
                case 2:
                    System.out.println("Nombre:");
                    String name = scan.nextLine();
                    System.out.println("Pais:");
                    String country = scan.nextLine();
                    Division nuevaDivision = new Division(name, country);

                    break;
                case 3:
                    /*
                    System.out.println("Division:");
                    String division = scan.nextLine();

                    System.out.println("Fecha:");
                    String fecha = scan.nextLine();
                    Date fecha = new Date("2000-01-12");
                    
                    System.out.println("Equipo de casa:");
                    String homeTeam = scan.nextLine();

                    System.out.println("Equipo visitante:");
                    String awayTeam = scan.nextLine();

                    System.out.println("Ingrese FTHG:");
                    double fthg = scan.nextDouble();

                    System.out.println("Ingrese FTAG:");
                    double ftag = scan.nextDouble();

                    System.out.println("Ingrese FTR:");
                    double ftr = scan.nextDouble();

                    System.out.println("Temporada:");
                    int season = scan.nextInt();
                    
                    Match nuevoMatch = new Match(division, null, homeTeam, awayTeam, fthg, ftag, ftr, season);
                    session.persist(nuevoMatch);
                    session.persist(nuevoMatch);
                    transaction.commit();
                     */
                    try (SessionFactory sessionFactory = configuration.buildSessionFactory(); Session session = sessionFactory.openSession()) {
                        Transaction transaction = session.beginTransaction();

                        Usuario usuario = new Usuario("Tony", "tony@gmail.com");
                        session.persist(usuario);
                        //Division division, Date matchDate, String homeTeam, String awayTeam, double fthg, double ftag, Double ftr, int season) {

                        Match nuevoMatch = new Match(null, null, "ESPAÑA", "Si", option, option, Double.NaN, option);
                        session.persist(nuevoMatch);
                        transaction.commit();
                        System.out.println("Has guardado el nuevo usuario");
                    }
                    System.out.println("Se ha guardado el nuevo match.");
                    break;
                /*
                    this.division = division;
                    this.matchDate = matchDate;
                    this.homeTeam = homeTeam;
                    this.awayTeam = awayTeam;
                    this.fthg = fthg;
                    this.ftag = ftag;
                    this.ftr = ftr;
                    this.season = season;
                 */

                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 0:
                    another = false;
                    break;
                default:
                    break;
            }
        } while (another);
        //session.close();
        System.out.println("Has salido del menu.");

    }
}
