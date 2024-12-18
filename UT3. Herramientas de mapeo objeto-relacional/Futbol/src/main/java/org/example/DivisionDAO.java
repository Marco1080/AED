package org.example;

import org.hibernate.Session;

public class DivisionDAO implements DAO<Division, String> {

    private final Session session;

    public DivisionDAO(Session session) {
        this.session = session;
    }

    @Override
    public String insertar(Division division) {
        session.beginTransaction();
        session.persist(division);
        session.getTransaction().commit();
        return division.getDivision(); // Suponiendo que Division tiene un método getId() para su clave primaria
    }

    @Override
    public String actualizar(Division division) {
        session.beginTransaction();
        session.merge(division);
        session.getTransaction().commit();
        return division.getDivision();
    }

    @Override
    public String eliminar(Division division) {
        session.beginTransaction();
        session.remove(division);
        session.getTransaction().commit();
        return division.getDivision();
    }

    @Override
    public Division buscar(String id) {
        session.beginTransaction();
        Division division = session.get(Division.class, id);
        session.getTransaction().commit();
        return division;
    }

}
