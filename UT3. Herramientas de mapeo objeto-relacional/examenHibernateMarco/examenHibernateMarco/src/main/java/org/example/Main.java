package org.example;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Configuration config = new Configuration().configure();
        SessionFactory sessionFactory = config.buildSessionFactory();
        try (Session session = sessionFactory.openSession()) {
            //Por razon que desconozco me fallan algunos metodos del CRUD,
            //creo que los try cierran implicitamente las sesiones pero no dispongo de tiempo para hacer las modificaciones pertinentes,
            //aplico aqui la misma logica de mis CRUDs aunque no sea lo adecuado
            //asignar tanta logica al main

            //Crear a Otolio
            EmpleadoCRUD empleadoCRUD = new EmpleadoCRUD();
            Date fecha = new Date(1966, 4, 2);
            Empleado empleado = new Empleado("Otolio", fecha, 50.5, 40);
            empleadoCRUD.insertar(empleado);
            System.out.println("Empleado insertado");

            //Actualizar a Pepe Gotera
            try (Session actualizarEmpleado = sessionFactory.openSession()) {
                empleado.setNombreEmplado("Pepe Gotera");
                actualizarEmpleado.beginTransaction();
                actualizarEmpleado.merge(empleado);
                actualizarEmpleado.getTransaction().commit();
                System.out.println("Empleado actualizado");
            }
            /*
            Empleado empleadoAactualizar;
            empleado.setNombreEmplado("Pepe Gotera");
            empleadoCRUD.actualizar(empleado);
            System.out.println("Empleado actualizado");*/

            //Crear Amoladora
            HerramientaCRUD herramientaCRUD = new HerramientaCRUD();
            Herramienta herramienta = new Herramienta("Amoladora", "", "", "", "Dexter", 70.0);
            herramientaCRUD.insertar(herramienta);
            System.out.println("Herramienta creada");

            //Crear localizacion
            LocalizacionCRUD localizacionCRUD = new LocalizacionCRUD();
            Localizacion localizacion = new Localizacion("CIFP Cesar Manrique", "Se ha ido la luz no hay internet");
            localizacionCRUD.insertar(localizacion);
            System.out.println("Localizacion creada");

            //Borrar localizacion
            try (Session borrarLocalizacion = sessionFactory.openSession()) {
                borrarLocalizacion.beginTransaction();
                borrarLocalizacion.remove(herramienta);
                borrarLocalizacion.beginTransaction().commit();
            }
            System.out.println("Localizacion borrada");
            //localizacionCRUD.borrar(localizacionCRUD.buscar(5));

            //Borrar los chapuzas
            //Borro todos los empleados en lugar de dos, en caso de querer borrar alguno especifico
            //utilizar el metodo borrar de EmpleadoCRUD
            Query<Empleado> borrarEmpleadosQuery = session.createQuery("from Empleado", Empleado.class);
            List<Empleado> empleadosAborrar = borrarEmpleadosQuery.getResultList();
            try (Session sessionBorrarEmpleado = sessionFactory.openSession()) {
                for (Empleado borrarEmpleado : empleadosAborrar) {
                    System.out.println("Se ha borrado a " + borrarEmpleado.getNombreEmplado());
                    sessionBorrarEmpleado.refresh(borrarEmpleado);
                }
            }

            //HQLs
            empleadoCRUD.empleadosExperiencia(10, 15);
            empleadoCRUD.empleadosDescripcion("reforma");
            empleadoCRUD.empleadosHerrramientas(2000);
            empleadoCRUD.empleadosPremium(20.0);
        }
    }
}