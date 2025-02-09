package org.example.apirest.controller;

import org.example.apirest.crud.ProductoCRUD;
import org.example.apirest.model.Producto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoCRUD productoCRUD;

    public ProductoController(ProductoCRUD productoCRUD) {
        this.productoCRUD = productoCRUD;
    }

    @GetMapping
    public List<Producto> getProductos() {
        return productoCRUD.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> getProductosId(@PathVariable Long id) {
        return productoCRUD.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Producto guardarProducto(@RequestBody Producto producto) {
        return productoCRUD.guardar(producto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoCRUD.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/precio-minimo")
    public List<Producto> getProductosPorPrecioMinimo(@RequestParam double precio) {
        return productoCRUD.obtenerProductosPorPrecioMinimo(precio);
    }

    @GetMapping("/ordenados/nombre")
    public List<Producto> getProductosOrdenadosPorNombre() {
        return productoCRUD.obtenerProductosOrdenadosPorNombre();
    }

    @GetMapping("/buscar")
    public List<Producto> getProductosPorNombreContiene(@RequestParam String nombre) {
        return productoCRUD.obtenerProductosPorNombreContiene(nombre);
    }
}
