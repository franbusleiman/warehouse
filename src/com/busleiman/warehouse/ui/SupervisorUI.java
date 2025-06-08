package com.busleiman.warehouse.ui;
import com.busleiman.warehouse.controller.AutenticacionController;
import com.busleiman.warehouse.controller.FaseController;
import com.busleiman.warehouse.controller.MetricasController;
import com.busleiman.warehouse.controller.StockController;
import com.busleiman.warehouse.model.Producto;


import javax.swing.*;
import java.awt.*;

class SupervisorUI extends JFrame {
    public SupervisorUI(StockController stockController, MetricasController metricasController,
                        AutenticacionController authController, FaseController faseController) {
        setTitle("Supervisor - Gestión");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout());
        JButton verStock = new JButton("Stock");
        JButton verMetricas = new JButton("Métricas");
        JButton logout = new JButton("Cerrar Sesión");

        topPanel.add(verStock);
        topPanel.add(verMetricas);
        topPanel.add(logout);

        JPanel contentPanel = new JPanel(new CardLayout());
        JPanel stockPanel = new JPanel(new BorderLayout());
        JPanel metricasPanel = new JPanel(new BorderLayout());

        DefaultListModel<String> productosModel = new DefaultListModel<>();
        JList<String> productosList = new JList<>(productosModel);
        productosList.setFont(new Font("Monospaced", Font.PLAIN, 13));
        productosList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane productoScroll = new JScrollPane(productosList);
        stockPanel.add(productoScroll, BorderLayout.CENTER);

        JButton reponer = new JButton("Reponer Seleccionado");
        reponer.addActionListener(e -> {
            String seleccionadoStr = productosList.getSelectedValue();
            if (seleccionadoStr != null) {
                String[] parts = seleccionadoStr.split(" - ");
                int productoId = Integer.parseInt(parts[0]);
                String cantStr = JOptionPane.showInputDialog(this, "Cantidad a reponer para " + parts[1]);
                try {
                    int cantidad = Integer.parseInt(cantStr);
                    stockController.reponerStock(productoId, cantidad);
                    JOptionPane.showMessageDialog(this, "Stock repuesto correctamente");
                    productosModel.clear();
                    stockController.listarProductos().forEach(p -> productosModel.addElement(p.getId() + " - " + p.getNombre() + " (Stock: " + p.getStock() + ")"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al reponer stock");
                }
            }
        });
        stockPanel.add(reponer, BorderLayout.SOUTH);

        JTextArea metricasArea = new JTextArea();
        metricasArea.setEditable(false);
        metricasArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        metricasPanel.add(new JScrollPane(metricasArea), BorderLayout.CENTER);

        contentPanel.add(stockPanel, "STOCK");
        contentPanel.add(metricasPanel, "METRICAS");

        verStock.addActionListener(e -> {
            productosModel.clear();
            stockController.listarProductos().forEach(p -> productosModel.addElement(p.getId() + " - " + p.getNombre() + " (Stock: " + p.getStock() + ")"));
            ((CardLayout) contentPanel.getLayout()).show(contentPanel, "STOCK");
        });

        verMetricas.addActionListener(e -> {
            metricasArea.setText("");
            metricasArea.append("Promedio por tipo de fase:\n");
           /* metricasController.tiemposPromedioPorTipoDeFase().forEach((tipo, tiempo) -> {
                metricasArea.append("Fase: " + tipo + ", Promedio: " + tiempo + " minutos\n");
            });*/
            metricasArea.append("\nStock actual:\n");
            metricasController.nivelesStockActual().forEach(p -> {
                metricasArea.append(p.getNombre() + ": " + p.getStock() + "\n");
            });
            metricasArea.append("\nFases completadas por operario:\n");
            metricasController.fasesCompletadasPorOperario().forEach((user, count) -> {
                metricasArea.append(user + ": " + count + "\n");
            });
            metricasArea.append("\nTiempo promedio por operario:\n");
            metricasController.tiempoPromedioPorOperario().forEach((user, tiempo) -> {
                metricasArea.append(user + ": " + tiempo + " minutos\n");
            });
            ((CardLayout) contentPanel.getLayout()).show(contentPanel, "METRICAS");
        });

        logout.addActionListener(e -> {
            dispose();
            new LoginUI(authController, faseController, stockController, metricasController);
        });

        add(topPanel, BorderLayout.NORTH);
        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
