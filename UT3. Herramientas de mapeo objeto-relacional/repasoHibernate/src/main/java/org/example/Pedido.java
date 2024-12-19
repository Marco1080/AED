package org.example;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Pedidos")
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPedido", nullable = false)
    private int idPedido;

    @Column(name = "entregado")
    private boolean entrado;

    @Column(name = "direccion", length = 255)
    private String direccion;

    @Column(name = "empresaTransporte", length = 255)
    private String empresaTransporte;

    //@Column(name = "fechaCreacion", nullable = false)
    //private LocalDateTime fechaCreacion;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Producto> productos = new ArrayList<Producto>();

    public Pedido() {
    }

    public Pedido(boolean entrado, String direccion, String empresaTransporte, List<Producto> productos) {
        this.entrado = entrado;
        this.direccion = direccion;
        this.empresaTransporte = empresaTransporte;
        this.productos = productos;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public boolean isEntrado() {
        return entrado;
    }

    public void setEntrado(boolean entrado) {
        this.entrado = entrado;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmpresaTransporte() {
        return empresaTransporte;
    }

    public void setEmpresaTransporte(String empresaTransporte) {
        this.empresaTransporte = empresaTransporte;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", entrado=" + entrado +
                ", direccion='" + direccion + '\'' +
                ", empresaTransporte='" + empresaTransporte + '\'' +
                ", productos=" + productos +
                '}';
    }
}
