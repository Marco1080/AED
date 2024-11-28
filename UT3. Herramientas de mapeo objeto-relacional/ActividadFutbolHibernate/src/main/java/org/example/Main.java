package org.example;

import org.hibernate.Session;
public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
    }
}
