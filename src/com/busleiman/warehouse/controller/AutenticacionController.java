package com.busleiman.warehouse.controller;

import com.busleiman.warehouse.model.Usuario;
import com.busleiman.warehouse.dao.*;

public class AutenticacionController {
    private UsuarioDAO usuarioDAO;

    public AutenticacionController(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }

    public Usuario autenticar(String usuario, String contraseña) {
        return usuarioDAO.autenticar(usuario, contraseña);
    }
}
