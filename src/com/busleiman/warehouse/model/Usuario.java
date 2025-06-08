package com.example.library.model;
public class Usuario {
    private String nombre;
    private String contrasena;
    private String rol;

    public Usuario(String nombre, String contrasena, String rol) {
        this.nombre = nombre;
        this.contrasena = contrasena;
        this.rol = rol;
    }

    public String getRol() {
        return rol;
    }
}
