package com.busleiman.warehouse.dao;

import com.busleiman.warehouse.model.ProductoOrden;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoOrdenDAO {
    private List<ProductoOrden> relaciones = new ArrayList<>();
    private int nextId = 1;

    public void asociarProducto(int ordenId, int productoId, int cantidad) {
        relaciones.add(new ProductoOrden(nextId++, ordenId, productoId, cantidad));
    }

    public List<ProductoOrden> getByOrdenId(int ordenId) {
        return relaciones.stream().filter(r -> r.getOrdenId() == ordenId).collect(Collectors.toList());
    }

    public List<ProductoOrden> getAll() {
        return relaciones;
    }
}
