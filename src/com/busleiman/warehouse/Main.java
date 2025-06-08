package com.busleiman.warehouse;


import com.busleiman.warehouse.controller.AutenticacionController;
import com.busleiman.warehouse.controller.FaseController;
import com.busleiman.warehouse.controller.MetricasController;
import com.busleiman.warehouse.controller.StockController;
import com.busleiman.warehouse.dao.OrdenDAO;
import com.busleiman.warehouse.model.ProductoOrden;
import com.busleiman.warehouse.ui.LoginUI;

import javax.swing.*;
import java.util.List;


class Main {
    public static void main(String[] args) {
        var usuarioDAO = new com.busleiman.warehouse.dao.UsuarioDAO();
        var productoDAO = new com.busleiman.warehouse.dao.ProductoDAO();
        var faseDAO = new com.busleiman.warehouse.dao.FaseDAO();
        var ordenDAO = new OrdenDAO();

        var auth = new AutenticacionController(usuarioDAO);
        var stockCtrl = new StockController(productoDAO);
        var faseCtrl = new FaseController(faseDAO);
        var metricasCtrl = new MetricasController(faseDAO, productoDAO, usuarioDAO);

        var orden1 = ordenDAO.crearOrden("Orden ejemplo 1", List.of(
                new ProductoOrden(0, 0, 1, 5),
                new ProductoOrden(0, 0, 2, 3)
        ));
        faseDAO.crearFasesParaOrden(orden1.getId());

        var orden2 = ordenDAO.crearOrden("Orden ejemplo 2", List.of(
                new ProductoOrden(0, 0, 1, 2)
        ));
        faseDAO.crearFasesParaOrden(orden2.getId());

        new LoginUI(auth, faseCtrl, stockCtrl, metricasCtrl);
    }
}
