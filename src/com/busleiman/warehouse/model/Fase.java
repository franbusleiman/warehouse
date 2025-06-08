package com.busleiman.warehouse.model;

public class Fase {
    private int id;
    private int ordenId;
    private String tipo;
    private String estado;
    private Integer asignadaA;
    private java.time.LocalDateTime fechaInicio;
    private java.time.LocalDateTime fechaFin;
    private int faseAnterior;
    private int faseSiguiente;

    public Fase(int id, int ordenId, String tipo, String estado, Integer asignadaA,
                java.time.LocalDateTime fechaInicio, java.time.LocalDateTime fechaFin) {
        this.id = id;
        this.ordenId = ordenId;
        this.tipo = tipo;
        this.estado = estado;
        this.asignadaA = asignadaA;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getOrdenId() { return ordenId; }
    public void setOrdenId(int ordenId) { this.ordenId = ordenId; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Integer getAsignadaA() { return asignadaA; }
    public void setAsignadaA(Integer asignadaA) { this.asignadaA = asignadaA; }
    public java.time.LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(java.time.LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    public java.time.LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(java.time.LocalDateTime fechaFin) { this.fechaFin = fechaFin; }
    public int getFaseAnterior() { return faseAnterior; }
    public void setFaseAnterior(int faseAnterior) { this.faseAnterior = faseAnterior; }
    public int getFaseSiguiente() { return faseSiguiente; }
    public void setFaseSiguiente(int faseSiguiente) { this.faseSiguiente = faseSiguiente; }
}