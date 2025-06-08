package com.busleiman.warehouse.model;


public class Producto {
    private int id;
    private String nombre;
    private int stock;

    public Producto(int id, String nombre, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
}