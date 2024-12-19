package org.example;

import org.hibernate.*;

public class ProductoCRUD implements DAO<Producto,String>{
    private final Session session;

    public ProductoCRUD(Session session) {
        this.session = session;
    }

    @Override
    public String insertar(Producto entidad) {
        session.beginTransaction();
        session.persist(entidad);
        session.getTransaction().commit();
        return entidad.getNombreProducto();
    }

    @Override
    public String actualizar(Producto entidad) {
        session.beginTransaction();
        session.merge(entidad);
        session.getTransaction().commit();
        return entidad.getNombreProducto();
    }

    @Override
    public String eliminar(Producto entidad) {
        session.beginTransaction();
        session.remove(entidad);
        session.getTransaction().commit();
        return entidad.getNombreProducto();
    }

    public String eliminarPorId(String id) {
        session.beginTransaction();
        Producto producto = session.get(Producto.class, id);
        if (producto != null) {
            session.remove(producto);
        }
        session.getTransaction().commit();
        return id;
    }


    @Override
    public Producto buscar(String id) {
        session.beginTransaction();
        Producto producto = session.get(Producto.class, id);
        session.getTransaction().commit();
        return producto;
    }
}
