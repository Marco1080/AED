package org.example;

import org.hibernate.Session;

public class MatchDAO implements DAO<Match,String>{
    private final Session session;

    public MatchDAO(Session session) {
        this.session = session;
    }

    @Override
    public String insertar(Match entidad) {
        session.beginTransaction();
        session.persist(entidad);
        session.getTransaction().commit();
        return entidad.getMatch();
    }

    @Override
    public String actualizar(Match entidad) {
        session.beginTransaction();
        session.merge(entidad);
        session.getTransaction().commit();
        return entidad.getMatch();
    }

    @Override
    public String eliminar(Match entidad) {
        session.beginTransaction();
        session.remove(entidad);
        session.getTransaction().commit();
        return entidad.getMatch();
    }

    @Override
    public Match buscar(String id) {
        session.beginTransaction();
        Match match = session.get(Match.class, id);
        session.getTransaction().commit();
        return match;
    }
}
