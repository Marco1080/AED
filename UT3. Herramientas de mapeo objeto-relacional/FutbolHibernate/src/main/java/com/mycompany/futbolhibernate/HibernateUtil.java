/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.futbolhibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory factory;

    static {
        try {
            factory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Session getSession() {
        return factory.getCurrentSession();
    }
}
