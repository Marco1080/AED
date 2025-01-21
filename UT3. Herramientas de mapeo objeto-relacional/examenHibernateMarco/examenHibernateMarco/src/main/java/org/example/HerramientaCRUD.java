package org.example;

import org.hibernate.Session;

public class HerramientaCRUD implements DAO<Herramienta, Integer>{

    @Override
    public Integer insertar(Herramienta herramienta) {
        try(Session session = com.example.HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(herramienta);
            session.getTransaction().commit();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public Integer actualizar(Herramienta herramienta) {
        try(Session session = com.example.HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.merge(herramienta);
            session.getTransaction().commit();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public Integer borrar(Herramienta herramienta) {
        try(Session session = com.example.HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.remove(herramienta);
            session.getTransaction().commit();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public Herramienta buscar(Integer id) {
        Herramienta herramienta;
        try(Session session = com.example.HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            herramienta = session.get(Herramienta.class, id);
            session.getTransaction().commit();
        }
        return herramienta;
    }
}
