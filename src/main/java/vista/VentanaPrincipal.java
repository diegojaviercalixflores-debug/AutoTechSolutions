/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import javax.swing.*;
import controlador.ClienteController;
import controlador.VehiculoController;
import controlador.ServicioController;
import controlador.RepuestoController;
import controlador.OrdenTrabajoController;
import controlador.FacturaController;
import vista.FormularioReportes;
import com.formdev.flatlaf.extras.FlatSVGIcon;

/**
 *
 * @author Diego Calix
 */
public class VentanaPrincipal extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaPrincipal.class.getName());

    /**
     * Creates new form VentanaPrincipal
     */
    private ClienteController clienteController;
    private VehiculoController vehiculoController;
    private ServicioController servicioController;
    private RepuestoController repuestoController;
    private OrdenTrabajoController ordenTrabajoController;
    private FacturaController facturaController;

    public VentanaPrincipal(ClienteController clienteController,
            VehiculoController vehiculoController,
            ServicioController servicioController,
            RepuestoController repuestoController,
            OrdenTrabajoController ordenTrabajoController,
            FacturaController facturaController) {

        initComponents();

        this.clienteController = clienteController;
        this.vehiculoController = vehiculoController;
        this.servicioController = servicioController;
        this.repuestoController = repuestoController;
        this.ordenTrabajoController = ordenTrabajoController;
        this.facturaController = facturaController;
        configurarEstilos();
        setLocationRelativeTo(null);
    }

    private void configurarEstilos() {

        // Configuración de la ventana
        setSize(600, 400);
        setMinimumSize(new java.awt.Dimension(750, 400));

        // Fondo general
        getContentPane().setBackground(
                new java.awt.Color(248, 250, 252)
        );

        // Panel superior
        jPanel1.setBackground(new java.awt.Color(30, 41, 59));

        jLabel1.setForeground(java.awt.Color.WHITE);
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 20));

        jLabel2.setForeground(new java.awt.Color(203, 213, 225));
        jLabel2.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));

        // Paneles de secciones
        java.awt.Color fondoPanel = java.awt.Color.WHITE;

        panelGestion.setBackground(fondoPanel);
        panelOperaciones.setBackground(fondoPanel);
        panelConsultas.setBackground(fondoPanel);

        // Títulos de las secciones
        java.awt.Color colorTitulo = new java.awt.Color(30, 41, 59);

        jLabel3.setForeground(colorTitulo);
        jLabel4.setForeground(colorTitulo);
        jLabel5.setForeground(colorTitulo);

        jLabel3.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jLabel4.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        jLabel5.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));

        // Estilo de botones
        JButton[] botones = {
            BtnClientes, BtnVehiculos, BtnServicios,
            BtnRepuestos, BtnFacturas, BtnOrdenes,
            BtnReportes
        };

        for (JButton boton : botones) {

            boton.setBackground(new java.awt.Color(37, 99, 235));
            boton.setForeground(java.awt.Color.WHITE);
            boton.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 13));
            boton.setFocusPainted(false);
            boton.setCursor(new java.awt.Cursor(
                    java.awt.Cursor.HAND_CURSOR
            ));
            boton.putClientProperty("JButton.buttonType", "roundRect");
        }
        // Iconos de los botones
        BtnClientes.setIcon(new FlatSVGIcon("iconos/clientes.svg", 24, 24));
        BtnVehiculos.setIcon(new FlatSVGIcon("iconos/vehiculos.svg", 24, 24));
        BtnServicios.setIcon(new FlatSVGIcon("iconos/servicios.svg", 24, 24));
        BtnRepuestos.setIcon(new FlatSVGIcon("iconos/repuestos.svg", 24, 24));
        BtnFacturas.setIcon(new FlatSVGIcon("iconos/facturas.svg", 24, 24));
        BtnOrdenes.setIcon(new FlatSVGIcon("iconos/ordenes.svg", 24, 24));
        BtnReportes.setIcon(new FlatSVGIcon("iconos/reportes.svg", 24, 24));
        BtnSalir.setIcon(new FlatSVGIcon("iconos/salir.svg", 20, 20));

        // Posición del icono
        for (JButton boton : botones) {
            boton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
            boton.setIconTextGap(12);
        }

        BtnSalir.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        BtnSalir.setIconTextGap(8);

        // Botón salir
        BtnSalir.setBackground(new java.awt.Color(210, 10, 38));
        BtnSalir.setForeground(java.awt.Color.WHITE);
        BtnSalir.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
        BtnSalir.setFocusPainted(false);
        BtnSalir.setCursor(new java.awt.Cursor(
                java.awt.Cursor.HAND_CURSOR
        ));

        BtnSalir.putClientProperty("JButton.buttonType", "roundRect");

        // Centrar los textos del encabezado
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        configurarMenuPrincipal();
    }

    private void configurarMenuPrincipal() {

        // Panel central del menú
        javax.swing.JPanel menu = new javax.swing.JPanel(
                new java.awt.GridBagLayout()
        );

        menu.setBackground(new java.awt.Color(248, 250, 252));

        // Botones de las primeras tres filas
        JButton[] botones = {
            BtnClientes, BtnVehiculos,
            BtnServicios, BtnRepuestos,
            BtnFacturas, BtnOrdenes
        };

        java.awt.GridBagConstraints gbc
                = new java.awt.GridBagConstraints();

        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.insets = new java.awt.Insets(5, 6, 5, 6);

        // Distribuir los seis botones en dos columnas
        for (int i = 0; i < botones.length; i++) {

            gbc.gridx = i % 2;
            gbc.gridy = i / 2;

            botones[i].setPreferredSize(
                    new java.awt.Dimension(175, 55)
            );

            menu.add(botones[i], gbc);
        }

        // Reportes ocupa las dos columnas
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;

        BtnReportes.setPreferredSize(
                new java.awt.Dimension(175, 55)
        );

        menu.add(BtnReportes, gbc);

        // Panel inferior para salir
        javax.swing.JPanel inferior = new javax.swing.JPanel(
                new java.awt.FlowLayout(
                        java.awt.FlowLayout.RIGHT, 8, 8
                )
        );

        inferior.setBackground(menu.getBackground());

        BtnSalir.setPreferredSize(
                new java.awt.Dimension(130, 35)
        );

        inferior.add(BtnSalir);

        // Distribución general
        getContentPane().removeAll();

        getContentPane().setLayout(
                new java.awt.BorderLayout(8, 8)
        );

        getContentPane().add(jPanel1, java.awt.BorderLayout.NORTH);
        getContentPane().add(menu, java.awt.BorderLayout.CENTER);
        getContentPane().add(inferior, java.awt.BorderLayout.SOUTH);

        setSize(700, 450);
        setMinimumSize(new java.awt.Dimension(700, 450));

        revalidate();
        repaint();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BtnSalir = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panelGestion = new javax.swing.JPanel();
        BtnClientes = new javax.swing.JButton();
        BtnVehiculos = new javax.swing.JButton();
        BtnServicios = new javax.swing.JButton();
        BtnRepuestos = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        panelOperaciones = new javax.swing.JPanel();
        BtnFacturas = new javax.swing.JButton();
        BtnOrdenes = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        panelConsultas = new javax.swing.JPanel();
        BtnReportes = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("AUTOTECH SOLUTIONS");

        BtnSalir.setText("Salir");
        BtnSalir.addActionListener(this::BtnSalirActionPerformed);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("SISTEMA DE GESTIÓN AUTOMOTRIZ");

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("MENÚ PRINCIPAL");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        BtnClientes.setText("Cliente");
        BtnClientes.addActionListener(this::BtnClientesActionPerformed);

        BtnVehiculos.setText("Vehículos");
        BtnVehiculos.addActionListener(this::BtnVehiculosActionPerformed);

        BtnServicios.setText("Servicios");
        BtnServicios.setMaximumSize(new java.awt.Dimension(90, 25));
        BtnServicios.addActionListener(this::BtnServiciosActionPerformed);

        BtnRepuestos.setText("Repuestos");
        BtnRepuestos.setPreferredSize(new java.awt.Dimension(81, 23));
        BtnRepuestos.addActionListener(this::BtnRepuestosActionPerformed);

        jLabel5.setText("GESTIÓN");

        javax.swing.GroupLayout panelGestionLayout = new javax.swing.GroupLayout(panelGestion);
        panelGestion.setLayout(panelGestionLayout);
        panelGestionLayout.setHorizontalGroup(
            panelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGestionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelGestionLayout.createSequentialGroup()
                        .addGroup(panelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnVehiculos)
                            .addComponent(BtnClientes))
                        .addGap(18, 18, 18)
                        .addGroup(panelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelGestionLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BtnClientes, BtnRepuestos, BtnServicios, BtnVehiculos});

        panelGestionLayout.setVerticalGroup(
            panelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGestionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnClientes)
                    .addComponent(BtnServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelGestionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnVehiculos, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        panelGestionLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BtnClientes, BtnRepuestos, BtnServicios, BtnVehiculos});

        BtnFacturas.setText("Facturación");
        BtnFacturas.setPreferredSize(new java.awt.Dimension(81, 23));
        BtnFacturas.addActionListener(this::BtnFacturasActionPerformed);

        BtnOrdenes.setText("Órdenes de trabajo");
        BtnOrdenes.setPreferredSize(new java.awt.Dimension(173, 75));
        BtnOrdenes.addActionListener(this::BtnOrdenesActionPerformed);

        jLabel4.setText("OPERACIONES");

        javax.swing.GroupLayout panelOperacionesLayout = new javax.swing.GroupLayout(panelOperaciones);
        panelOperaciones.setLayout(panelOperacionesLayout);
        panelOperacionesLayout.setHorizontalGroup(
            panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelOperacionesLayout.createSequentialGroup()
                        .addComponent(BtnFacturas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(BtnOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelOperacionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {BtnFacturas, BtnOrdenes});

        panelOperacionesLayout.setVerticalGroup(
            panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOperacionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelOperacionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnFacturas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnOrdenes, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26))
        );

        panelOperacionesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {BtnFacturas, BtnOrdenes});

        BtnReportes.setText("Reportes");
        BtnReportes.setPreferredSize(new java.awt.Dimension(173, 75));
        BtnReportes.addActionListener(this::BtnReportesActionPerformed);

        jLabel3.setText("CONSULTAS");

        javax.swing.GroupLayout panelConsultasLayout = new javax.swing.GroupLayout(panelConsultas);
        panelConsultas.setLayout(panelConsultasLayout);
        panelConsultasLayout.setHorizontalGroup(
            panelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 363, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelConsultasLayout.setVerticalGroup(
            panelConsultasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelConsultasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnReportes, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(panelConsultas, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelOperaciones, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelGestion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 10, Short.MAX_VALUE))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelGestion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelOperaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelConsultas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(BtnSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnRepuestosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnRepuestosActionPerformed
        // TODO add your handling code here:
        FormularioRepuestos formulario = new FormularioRepuestos(repuestoController);
        formulario.setVisible(true);
    }//GEN-LAST:event_BtnRepuestosActionPerformed

    private void BtnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSalirActionPerformed
        // TODO add your handling code here:
        int respuesta = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea salir?",
                "Confirmar salida",
                JOptionPane.YES_NO_OPTION
        );

        if (respuesta == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_BtnSalirActionPerformed

    private void BtnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnClientesActionPerformed
        // TODO add your handling code here:
        FormularioClientes formulario
                = new FormularioClientes(clienteController);

        formulario.setVisible(true);
    }//GEN-LAST:event_BtnClientesActionPerformed

    private void BtnVehiculosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnVehiculosActionPerformed
        // TODO add your handling code here:
        new FormularioVehiculos(vehiculoController).setVisible(true);
    }//GEN-LAST:event_BtnVehiculosActionPerformed

    private void BtnServiciosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnServiciosActionPerformed
        // TODO add your handling code here:
        FormularioServicios formulario = new FormularioServicios(servicioController);
        formulario.setVisible(true);
    }//GEN-LAST:event_BtnServiciosActionPerformed

    private void BtnOrdenesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOrdenesActionPerformed
        // TODO add your handling code here:
        FormularioOrdenes formulario = new FormularioOrdenes(
                ordenTrabajoController,
                clienteController,
                vehiculoController,
                servicioController,
                repuestoController
        );

        formulario.setVisible(true);
    }//GEN-LAST:event_BtnOrdenesActionPerformed

    private void BtnFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFacturasActionPerformed
        // TODO add your handling code here:
        FormularioFacturas formulario = new FormularioFacturas(
                facturaController,
                ordenTrabajoController
        );

        formulario.setVisible(true);
    }//GEN-LAST:event_BtnFacturasActionPerformed

    private void BtnReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnReportesActionPerformed
        // TODO add your handling code here:
        FormularioReportes formulario = new FormularioReportes(
                ordenTrabajoController
        );
        formulario.setVisible(true);
    }//GEN-LAST:event_BtnReportesActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnClientes;
    private javax.swing.JButton BtnFacturas;
    private javax.swing.JButton BtnOrdenes;
    private javax.swing.JButton BtnReportes;
    private javax.swing.JButton BtnRepuestos;
    private javax.swing.JButton BtnSalir;
    private javax.swing.JButton BtnServicios;
    private javax.swing.JButton BtnVehiculos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panelConsultas;
    private javax.swing.JPanel panelGestion;
    private javax.swing.JPanel panelOperaciones;
    // End of variables declaration//GEN-END:variables

}
