package org.example.apirest.dao;

import org.example.apirest.model.Producto;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {

    List<Producto> findAll(Sort sort);

    List<Producto> findByPrecioGreaterThanEqual(double precio);

    List<Producto> findByNombreContaining(String nombre);
}
