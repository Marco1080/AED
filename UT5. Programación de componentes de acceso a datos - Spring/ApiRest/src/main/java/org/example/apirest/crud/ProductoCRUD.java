package org.example.apirest.crud;

import org.example.apirest.model.Producto;
import org.example.apirest.dao.ProductoRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoCRUD {

    private final ProductoRepository productoRepository;

    public ProductoCRUD(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> obtenerPorId(Long id) {
        return productoRepository.findById(id);
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }

    public List<Producto> obtenerProductosPorPrecioMinimo(double precio) {
        return productoRepository.findByPrecioGreaterThanEqual(precio);
    }

    public List<Producto> obtenerProductosOrdenadosPorNombre() {
        return productoRepository.findAll(Sort.by(Sort.Direction.ASC, "nombre"));
    }

    public List<Producto> obtenerProductosPorNombreContiene(String nombre) {
        return productoRepository.findByNombreContaining(nombre);
    }
}