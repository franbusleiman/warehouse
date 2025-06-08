package com.busleiman.warehouse.dao;

import com.busleiman.warehouse.model.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private List<Producto> productos = new ArrayList<>();
    private int nextId = 1;

    public ProductoDAO() {
        productos.add(new Producto(nextId++, "Cajas", 50));
        productos.add(new Producto(nextId++, "Sobres", 100));
    }

    public List<Producto> getAll() {
        return productos;
    }

    public Producto getById(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
    }

    public void descontarStock(int productoId, int cantidad) {
        Producto p = getById(productoId);
        if (p != null) {
            if (p.getStock() < cantidad) {
                throw new RuntimeException("Stock insuficiente para el producto: " + p.getNombre());
            }
            p.setStock(p.getStock() - cantidad);
        }
    }

    public void reponerStock(int productoId, int cantidad) {
        Producto p = getById(productoId);
        if (p != null) {
            p.setStock(p.getStock() + cantidad);
        }
    }
}
