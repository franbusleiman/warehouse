package com.busleiman.warehouse.controller;


import com.busleiman.warehouse.dao.ProductoDAO;
import com.busleiman.warehouse.model.Producto;

import java.util.List;

public class StockController {
    private ProductoDAO productoDAO;

    public StockController(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;
    }

    public List<Producto> listarProductos() {
        return productoDAO.getAll();
    }

    public void reponerStock(int productoId, int cantidad) {
        productoDAO.reponerStock(productoId, cantidad);
    }
}
