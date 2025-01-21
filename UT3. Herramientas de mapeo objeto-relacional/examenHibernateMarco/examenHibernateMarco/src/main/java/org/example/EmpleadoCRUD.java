package org.example;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmpleadoCRUD implements DAO<Empleado, Integer>{

    @Override
    public Integer insertar(Empleado empleado) {
        try(Session session = com.example.HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.persist(empleado);
            session.beginTransaction().commit();
        }catch (Exception e){return 0;}
        return 1;
    }

    @Override
    public Integer actualizar(Empleado empleado) {
        try(Session session = com.example.HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.merge(empleado);
            session.beginTransaction().commit();
        }catch (Exception e){return 0;}
        return 1;
    }

    @Override
    public Integer borrar(Empleado empleado) {
        try(Session session = com.example.HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            session.remove(empleado);
            session.beginTransaction().commit();
        }catch (Exception e){return 0;}
        return 1;
    }

    @Override
    public Empleado buscar(Integer id) {
        Empleado empleado;
        try (Session session = com.example.HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            empleado = session.get(Empleado.class, id);
            session.beginTransaction().commit();
        }
        return empleado;
    }

    public void empleadosExperiencia(int experiencia1, int experiencia2) {
        try (Session session = com.example.HibernateUtil.getSessionFactory().openSession()) {
            Query<Empleado> query = session.createQuery("FROM Empleado WHERE experiencia between :x and :y", Empleado.class);
            query.setParameter("x", experiencia1);
            query.setParameter("y", experiencia2);
            List<Empleado> empleados = query.getResultList();
            System.out.println("Empleados con experiencia entre " + experiencia1 + " y " + experiencia2);
            for(Empleado empleadosPorExperiencia : empleados){
                System.out.println(empleadosPorExperiencia);
            }
        }
    }

    public void empleadosDescripcion(String dato) {
        try (Session session = com.example.HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query<Empleado> query = session.createQuery("FROM Empleado inner join Localizacion where Localizacion.descripcion = :x", Empleado.class);
            query.setParameter("x", dato);
            List<Empleado> empleados = query.getResultList();
            System.out.println("Empleados con " + dato + " en la descripcion");
            for(Empleado empleadosPorExperiencia : empleados){
                System.out.println(empleadosPorExperiencia);
            }
            session.beginTransaction().commit();
        }
    }

    public void empleadosHerrramientas(int anio) {
        try(Session session = com.example.HibernateUtil.getSessionFactory().openSession()){
            Query<Herramienta> query = session.createQuery("FROM Herramienta inner join Empleado where year(Empleado.fechaNacimiento) > :x", Herramienta.class);
            query.setParameter("x", anio);
            List<Herramienta> herramientas = query.getResultList();
            System.out.println("Herramientas despues del año " + anio );
            for(Herramienta herramienta : herramientas){
                System.out.println(herramienta);
            }
        }
    }

    public void empleadosPremium(double sueldoHora) {
        try(Session session = com.example.HibernateUtil.getSessionFactory().openSession()){
            Query<Localizacion> query = session.createQuery("FROM Localizacion inner join Empleado where Empleado.precio > :x ", Localizacion.class);
            query.setParameter("x", sueldoHora);
            List<Localizacion> localizaciones = query.getResultList();
            System.out.println("Localizaciones donde el sueldo de los trabajadores es mayor a " + sueldoHora);
            for(Localizacion localizacion : localizaciones){
                System.out.println(localizacion);
            }
        }
    }
}
