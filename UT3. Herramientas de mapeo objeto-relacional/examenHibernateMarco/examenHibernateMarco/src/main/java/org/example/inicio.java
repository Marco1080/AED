package org.example;

import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class inicio {

    public static void main(String[] args) {
        System.out.println("inicio");
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        //Aula miaula = session.get(Aula.class, 1);

        //System.out.println(miaula);
        tx.commit();

        session.close();


    }
}
