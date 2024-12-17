package org.example;

public interface DAO <T, U>{
    public U insertar(T entidad);
    public U  actualizar(T entidad);
    public U eliminar(T entidad);
    public T buscar(U id);
}
