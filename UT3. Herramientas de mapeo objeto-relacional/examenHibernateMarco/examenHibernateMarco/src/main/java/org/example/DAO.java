package org.example;

public interface DAO <T, U>{
    public U insertar(T entidad);
    public U actualizar(T entidad);
    public U borrar(T entidad);
    public T buscar(U id);
}
