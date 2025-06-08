package com.busleiman.warehouse.controller;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

import com.busleiman.warehouse.dao.FaseDAO;
import com.busleiman.warehouse.model.*;

public class FaseController {
    private FaseDAO faseDAO;

    public FaseController(FaseDAO faseDAO) {
        this.faseDAO = faseDAO;
    }

    public List<Fase> obtenerFasesDisponibles() {
        return faseDAO.getFasesDisponibles();
    }

    public void tomarFase(int faseId, int usuarioId) {
        faseDAO.tomarFase(faseId, usuarioId);
    }

    public void completarFase(int faseId) {
        faseDAO.completarFase(faseId);
    }

    public List<Fase> obtenerFasesPorOrden(int ordenId) {
        return faseDAO.getByOrdenId(ordenId);
    }

    public List<Fase> obtenerTodasLasFases() {
        return faseDAO.getAll();
    }
}
