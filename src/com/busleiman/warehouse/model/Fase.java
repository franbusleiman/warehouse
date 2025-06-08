package com.example.library.model;

import java.time.LocalDateTime;

public class Fase {
    private int id;
    private String tipo;
    private String estado;
    private int ordenId;
    private Integer asignadaA;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Fase(int id, String tipo, String estado, int ordenId, Integer asignadaA,
                LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        this.id = id;
        this.tipo = tipo;
        this.estado = estado;
        this.ordenId = ordenId;
        this.asignadaA = asignadaA;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() { return id; }
    public String getTipo() { return tipo; }
    public String getEstado() { return estado; }
    public int getOrdenId() { return ordenId; }
    public Integer getAsignadaA() { return asignadaA; }
    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public LocalDateTime getFechaFin() { return fechaFin; }

    public void setEstado(String estado) { this.estado = estado; }
    public void setAsignadaA(Integer asignadaA) { this.asignadaA = asignadaA; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }

    @Override
    public String toString() {
        return tipo + " - Orden #" + ordenId + " [" + estado + "]";
    }
}

