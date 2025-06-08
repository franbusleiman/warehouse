package com.busleiman.warehouse.dao;

import com.busleiman.warehouse.model.Orden;
import com.busleiman.warehouse.model.Producto;
import com.busleiman.warehouse.model.ProductoOrden;

import java.util.ArrayList;
import java.util.List;

public class OrdenDAO {
    private List<Orden> ordenes = new ArrayList<>();
    private int nextId = 1;
    private ProductoOrdenDAO productoOrdenDAO = new ProductoOrdenDAO();
    private ProductoDAO productoDAO = new ProductoDAO();

    public Orden crearOrden(String descripcion, List<ProductoOrden> productos) {
        for (ProductoOrden po : productos) {
            Producto p = productoDAO.getById(po.getProductoId());
            if (p == null || p.getStock() < po.getCantidad()) {
                throw new RuntimeException("Stock insuficiente para el producto con ID: " + po.getProductoId());
            }
        }

        Orden o = new Orden(nextId++, descripcion, new java.sql.Timestamp(System.currentTimeMillis()));
        ordenes.add(o);

        for (ProductoOrden po : productos) {
            productoOrdenDAO.asociarProducto(o.getId(), po.getProductoId(), po.getCantidad());
            productoDAO.descontarStock(po.getProductoId(), po.getCantidad());
        }
        return o;
    }

    public Orden getById(int id) {
        return ordenes.stream().filter(o -> o.getId() == id).findFirst().orElse(null);
    }

    public List<Orden> getAll() {
        return ordenes;
    }

    public ProductoOrdenDAO getProductoOrdenDAO() {
        return productoOrdenDAO;
    }

    public ProductoDAO getProductoDAO() {
        return productoDAO;
    }
}
