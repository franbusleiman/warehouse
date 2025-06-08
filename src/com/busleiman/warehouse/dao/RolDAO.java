package com.busleiman.warehouse.dao;

import com.busleiman.warehouse.model.Rol;

import java.util.ArrayList;
import java.util.List;

public class RolDAO {
    private List<Rol> roles = new ArrayList<>();
    private int nextId = 1;

    public RolDAO() {
        roles.add(new Rol(nextId++, "OPERARIO"));
        roles.add(new Rol(nextId++, "SUPERVISOR"));
    }

    public Rol getById(int id) {
        return roles.stream().filter(r -> r.getId() == id).findFirst().orElse(null);
    }

    public List<Rol> getAll() {
        return roles;
    }
}