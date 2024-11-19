package com.example;

/*
import org.hibernate.SessionFactory;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

 */

import static com.example.HibernateUtil.getSessionFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private void persist() {

        org.hibernate.Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        var employee1 = new com.example.model.Employee("employee1", 23);
        var employee2 = new com.example.model.Employee("employee2", 25);

        session.persist(employee1);
        session.persist(employee2);

        session.getTransaction().commit();

        session.close();



    }



    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");
        org.hibernate.Session session = getSessionFactory().openSession();

        session.beginTransaction();

        var employee1 = new com.example.model.Employee("marco", 50);
        var employee2 = new com.example.model.Employee("employee333", 25);

        session.persist(employee1);
        session.persist(employee2);

        session.getTransaction().commit();

        session.close();

    }
}