package org.example;

import org.hibernate.Session;

public class LocalizacionCRUD implements DAO<Localizacion, Integer> {

    @Override
    public Integer insertar(Localizacion localizacion) {
        try (Session session = com.example.HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.persist(localizacion);
            session.getTransaction().commit();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public Integer actualizar(Localizacion localizacion) {
        try (Session session = com.example.HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(localizacion);
            session.getTransaction().commit();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public Integer borrar(Localizacion localizacion) {
        try (Session session = com.example.HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Localizacion borarAborrar = session.get(Localizacion.class, localizacion.getIdLocalizacion());
            session.remove(borarAborrar);
            session.getTransaction().commit();
        } catch (Exception e) {
            return 0;
        }
        return 1;
    }

    @Override
    public Localizacion buscar(Integer id) {
        Localizacion localizacion;
        try (Session session = com.example.HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            localizacion = session.get(Localizacion.class, id);
            session.getTransaction().commit();
        }
        return localizacion;
    }
}
