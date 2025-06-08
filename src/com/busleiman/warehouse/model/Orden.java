package com.busleiman.warehouse.model;

public class Orden {
    private int id;
    private String descripcion;
    private java.sql.Timestamp fechaCreacion;

    public Orden(int id, String descripcion, java.sql.Timestamp fechaCreacion) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaCreacion = fechaCreacion;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public java.sql.Timestamp getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(java.sql.Timestamp fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}