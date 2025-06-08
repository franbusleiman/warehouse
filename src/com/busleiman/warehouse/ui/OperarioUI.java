package com.busleiman.warehouse.ui;

import com.busleiman.warehouse.controller.AutenticacionController;
import com.busleiman.warehouse.controller.FaseController;
import com.busleiman.warehouse.controller.MetricasController;
import com.busleiman.warehouse.controller.StockController;
import com.busleiman.warehouse.model.Fase;
import com.busleiman.warehouse.model.Usuario;

import javax.swing.*;
import java.awt.*;
class OperarioUI extends JFrame {
    private final DefaultListModel<Fase> disponiblesModel = new DefaultListModel<>();
    private final DefaultListModel<Fase> asignadasModel = new DefaultListModel<>();
    private final JList<Fase> disponiblesList = new JList<>(disponiblesModel);
    private final JList<Fase> asignadasList = new JList<>(asignadasModel);

    public OperarioUI(Usuario usuario, FaseController faseController,
                      AutenticacionController authController,
                      StockController stockController,
                      MetricasController metricasController) {
        setTitle("Operario - Gestión de Fases");
        setSize(1000, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        disponiblesList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        asignadasList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        disponiblesList.setFont(new Font("Monospaced", Font.PLAIN, 13));
        asignadasList.setFont(new Font("Monospaced", Font.PLAIN, 13));

        disponiblesList.setCellRenderer(new FaseRenderer());
        asignadasList.setCellRenderer(new FaseRenderer());

        JPanel listPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        listPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        listPanel.add(new JScrollPane(disponiblesList));
        listPanel.add(new JScrollPane(asignadasList));

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        JButton tomar = new JButton("Tomar Fase");
        JButton completar = new JButton("Completar Fase");
        JButton refrescar = new JButton("Refrescar");
        JButton logout = new JButton("Cerrar Sesión");

        buttonsPanel.add(tomar);
        buttonsPanel.add(completar);
        buttonsPanel.add(refrescar);
        buttonsPanel.add(logout);

        tomar.addActionListener(e -> {
            Fase seleccionada = disponiblesList.getSelectedValue();
            if (seleccionada != null) {
                try {
                    faseController.tomarFase(seleccionada.getId(), usuario.getId());
                    JOptionPane.showMessageDialog(this, "Fase tomada correctamente");
                    cargarListas(usuario, faseController);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al tomar la fase");
                }
            }
        });

        completar.addActionListener(e -> {
            Fase seleccionada = asignadasList.getSelectedValue();
            if (seleccionada != null) {
                try {
                    faseController.completarFase(seleccionada.getId());
                    JOptionPane.showMessageDialog(this, "Fase completada correctamente");
                    cargarListas(usuario, faseController);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al completar la fase");
                }
            }
        });

        refrescar.addActionListener(e -> cargarListas(usuario, faseController));

        logout.addActionListener(e -> {
            dispose();
            new LoginUI(authController, faseController, stockController, metricasController);
        });

        add(listPanel, BorderLayout.CENTER);
        add(buttonsPanel, BorderLayout.SOUTH);

        cargarListas(usuario, faseController);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void cargarListas(Usuario usuario, FaseController faseController) {
        disponiblesModel.clear();
        asignadasModel.clear();
        faseController.obtenerFasesDisponibles().forEach(disponiblesModel::addElement);
        faseController.obtenerTodasLasFases().stream()
                .filter(f -> "EN_PROCESO".equals(f.getEstado()) && usuario.getId() == f.getAsignadaA())
                .forEach(asignadasModel::addElement);
    }

    private static class FaseRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Fase fase) {
                label.setText("Orden #" + fase.getOrdenId() + " - " + fase.getTipo() +
                        (fase.getEstado() != null ? " [" + fase.getEstado() + "]" : ""));
            }
            return label;
        }
    }
}
