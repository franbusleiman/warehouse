package com.busleiman.warehouse.ui;

import com.busleiman.warehouse.controller.AutenticacionController;
import com.busleiman.warehouse.controller.FaseController;
import com.busleiman.warehouse.controller.MetricasController;
import com.busleiman.warehouse.controller.StockController;
import com.busleiman.warehouse.dao.ProductoOrdenDAO;
import com.busleiman.warehouse.dao.OrdenDAO;
import com.busleiman.warehouse.model.Fase;
import com.busleiman.warehouse.model.ProductoOrden;
import com.busleiman.warehouse.model.Usuario;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class LoginUI extends JFrame {
    public LoginUI(AutenticacionController autenticacionController,
                   FaseController faseController,
                   StockController stockController,
                   MetricasController metricasController) {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel userLabel = new JLabel("Usuario:");
        JTextField userField = new JTextField();
        JLabel passLabel = new JLabel("Contraseña:");
        JPasswordField passField = new JPasswordField();
        JButton loginButton = new JButton("Ingresar");

        formPanel.add(userLabel);
        formPanel.add(userField);
        formPanel.add(passLabel);
        formPanel.add(passField);
        formPanel.add(new JLabel());
        formPanel.add(loginButton);

        add(formPanel, BorderLayout.CENTER);

        loginButton.addActionListener(e -> {
            String usuario = userField.getText();
            String contraseña = new String(passField.getPassword());
            Usuario user = autenticacionController.autenticar(usuario, contraseña);
            if (user != null) {
                dispose();
                if (user.getRolId() == 1) {
                    new OperarioUI(user, faseController, autenticacionController, stockController, metricasController);
                } else {
                    new SupervisorUI(stockController, metricasController, autenticacionController, faseController);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos");
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }
}