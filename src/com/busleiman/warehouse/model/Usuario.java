package com.busleiman.warehouse.model;

public class Usuario {

    private int id;
    private String usuario;
    private String contraseña;
    private int rolId;

    public Usuario(int id, String usuario, String contraseña, int rolId) {
        this.id = id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rolId = rolId;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }
    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }
    public int getRolId() { return rolId; }
    public void setRolId(int rolId) { this.rolId = rolId; }
}