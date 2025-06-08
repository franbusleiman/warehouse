package com.busleiman.warehouse.dao;


import com.busleiman.warehouse.model.Fase;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FaseDAO {
    private List<Fase> fases = new ArrayList<>();
    private int nextId = 1;

    public void crearFasesParaOrden(int ordenId) {
        String[] tipos = {"PICKING", "CONTROL_CALIDAD", "EMBALAJE", "DESPACHO"};
        Integer prevId = null;
        for (int i = 0; i < tipos.length; i++) {
            int id = nextId++;
            Fase fase = new Fase(id, ordenId, tipos[i], i == 0 ? "DISPONIBLE" : "BLOQUEADA",
                    null, null, null);
            if (prevId != null) {
                fase.setFaseAnterior(prevId);
                Fase anterior = getById(prevId);
                anterior.setFaseSiguiente(id);
            }
            fases.add(fase);
            prevId = id;
        }
    }

    public List<Fase> getFasesDisponibles() {
        return fases.stream().filter(f -> "DISPONIBLE".equals(f.getEstado())).collect(Collectors.toList());
    }

    public List<Fase> getByOrdenId(int ordenId) {
        return fases.stream().filter(f -> f.getOrdenId() == ordenId).collect(Collectors.toList());
    }

    public void tomarFase(int faseId, int usuarioId) {
        Fase fase = getById(faseId);
        if (fase != null && "DISPONIBLE".equals(fase.getEstado())) {
            fase.setEstado("EN_PROCESO");
            fase.setAsignadaA(usuarioId);
            fase.setFechaInicio(LocalDateTime.now());
        }
    }

    public void completarFase(int faseId) {
        Fase fase = getById(faseId);
        if (fase != null && "EN_PROCESO".equals(fase.getEstado())) {
            fase.setEstado("COMPLETADA");
            fase.setFechaFin(LocalDateTime.now());
            Integer siguienteId = fase.getFaseSiguiente();
            Fase siguiente = getById(siguienteId);
            if (siguiente != null) {
                siguiente.setEstado("DISPONIBLE");
            }
        }
    }

    public Fase getById(int id) {
        return fases.stream().filter(f -> f.getId() == id).findFirst().orElse(null);
    }

    public List<Fase> getAll() {
        return fases;
    }
}
