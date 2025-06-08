package com.busleiman.warehouse.controller;

import com.busleiman.warehouse.dao.FaseDAO;
import com.busleiman.warehouse.dao.ProductoDAO;
import com.busleiman.warehouse.dao.UsuarioDAO;
import com.busleiman.warehouse.model.Producto;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MetricasController {
    private FaseDAO faseDAO;
    private ProductoDAO productoDAO;
    private UsuarioDAO usuarioDAO;

    public MetricasController(FaseDAO faseDAO, ProductoDAO productoDAO, UsuarioDAO usuarioDAO) {
        this.faseDAO = faseDAO;
        this.productoDAO = productoDAO;
        this.usuarioDAO = usuarioDAO;
    }
/*
    public Map<String, Double> tiemposPromedioPorTipoDeFase() {
        return faseDAO.getAll().stream()
                .filter(f -> "COMPLETADA".equals(f.getEstado()))
                .collect(Collectors.groupingBy(
                        Fase::getTipo,
                        Collectors.averagingLong(f ->
                                java.time.Duration.between(f.getFechaInicio(), f.getFechaFin()).toMinutes())));
    }
*/
    public List<Producto> nivelesStockActual() {
        return productoDAO.getAll().stream()
                .sorted(Comparator.comparingInt(Producto::getStock))
                .collect(Collectors.toList());
    }

    public Map<String, Long> fasesCompletadasPorOperario() {
        return faseDAO.getAll().stream()
                .filter(f -> "COMPLETADA".equals(f.getEstado()) && f.getAsignadaA() != null)
                .collect(Collectors.groupingBy(
                        f -> usuarioDAO.getById(f.getAsignadaA()).getUsuario(),
                        Collectors.counting()));
    }

    public Map<String, Double> tiempoPromedioPorOperario() {
        return faseDAO.getAll().stream()
                .filter(f -> "COMPLETADA".equals(f.getEstado()) && f.getAsignadaA() != null)
                .collect(Collectors.groupingBy(
                        f -> usuarioDAO.getById(f.getAsignadaA()).getUsuario(),
                        Collectors.averagingLong(f ->
                                java.time.Duration.between(f.getFechaInicio(), f.getFechaFin()).toMinutes())));
    }
}
