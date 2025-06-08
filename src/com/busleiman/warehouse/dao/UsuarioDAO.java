package com.busleiman.warehouse.dao;


import java.util.*;

import com.busleiman.warehouse.model.*;

public class UsuarioDAO {
    private List<Usuario> usuarios = new ArrayList<>();
    private int nextId = 1;

    public UsuarioDAO() {
        usuarios.add(new Usuario(nextId++, "juan", "1234", 1));
        usuarios.add(new Usuario(nextId++, "ana", "5678", 2));
    }

    public Usuario autenticar(String usuario, String contraseña) {
        return usuarios.stream()
                .filter(u -> u.getUsuario().equals(usuario) && u.getContraseña().equals(contraseña))
                .findFirst().orElse(null);
    }

    public Usuario getById(int id) {
        return usuarios.stream().filter(u -> u.getId() == id).findFirst().orElse(null);
    }

    public List<Usuario> getAll() {
        return usuarios;
    }
}