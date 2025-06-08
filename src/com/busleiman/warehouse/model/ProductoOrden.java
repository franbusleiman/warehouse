package com.busleiman.warehouse.model;

public class ProductoOrden {
    private int id;
    private int ordenId;
    private int productoId;
    private int cantidad;

    public ProductoOrden(int id, int ordenId, int productoId, int cantidad) {
        this.id = id;
        this.ordenId = ordenId;
        this.productoId = productoId;
        this.cantidad = cantidad;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrdenId() { return ordenId; }
    public void setOrdenId(int ordenId) { this.ordenId = ordenId; }
    public int getProductoId() { return productoId; }
    public void setProductoId(int productoId) { this.productoId = productoId; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
}
