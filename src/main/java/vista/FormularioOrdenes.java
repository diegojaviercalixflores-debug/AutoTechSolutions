/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import hilos.ProcesoServicioHilo;
import java.awt.*;
import javax.swing.*;
import controlador.OrdenTrabajoController;
import controlador.ClienteController;
import controlador.VehiculoController;
import controlador.ServicioController;
import controlador.RepuestoController;
import modelo.OrdenTrabajo;
import modelo.Cliente;
import modelo.Vehiculo;
import modelo.Servicio;
import modelo.Repuesto;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Diego Calix
 */
public class FormularioOrdenes extends javax.swing.JFrame {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioOrdenes.class.getName());

    private OrdenTrabajoController ordenTrabajoController;
    private ClienteController clienteController;
    private VehiculoController vehiculoController;
    private ServicioController servicioController;
    private RepuestoController repuestoController;

    /**
     * Creates new form FormularioOrdenes
     */
    public FormularioOrdenes() {
        initComponents();
        configurarDesplazamiento();
    }

    public FormularioOrdenes(
            OrdenTrabajoController ordenTrabajoController,
            ClienteController clienteController,
            VehiculoController vehiculoController,
            ServicioController servicioController,
            RepuestoController repuestoController) {

        initComponents();

        this.ordenTrabajoController = ordenTrabajoController;
        this.clienteController = clienteController;
        this.vehiculoController = vehiculoController;
        this.servicioController = servicioController;
        this.repuestoController = repuestoController;

        configurarDesplazamiento();
        configurarComponentes();
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);

        lblProgreso.setText("Progreso de finalización: 0%");

        cargarClientes();
        cargarVehiculos();
        cargarServicios();
        cargarRepuestos();

        generarNumeroOrden();
        generarFecha();

        configurarTablaServicios();
        configurarTablaRepuestos();
    }

    private void configurarDesplazamiento() {

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new javax.swing.BoxLayout(
                panelCentral, javax.swing.BoxLayout.Y_AXIS));

        panelCentral.add(panelDatos);
        panelCentral.add(panelServicios);
        panelCentral.add(panelRepuestos);
        panelCentral.add(panelAcciones);

        JScrollPane scroll = new JScrollPane(panelCentral);

        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        JPanel encabezado = new JPanel(new BorderLayout());
        encabezado.setBorder(
                javax.swing.BorderFactory.createEmptyBorder(5, 10, 5, 10));

        encabezado.add(lblTitulo, BorderLayout.CENTER);

        getContentPane().removeAll();
        getContentPane().setLayout(new BorderLayout());

        getContentPane().add(encabezado, BorderLayout.NORTH);
        getContentPane().add(scroll, BorderLayout.CENTER);

        setSize(718, 650);
        setLocationRelativeTo(null);

        revalidate();
        repaint();
    }

    private void configurarComponentes() {

        cmbEstado.removeAllItems();

        cmbEstado.addItem("Pendiente");
        cmbEstado.addItem("En proceso");
        cmbEstado.addItem("Finalizada");
        cmbEstado.addItem("Cancelada");

        cmbEstado.setSelectedItem("Pendiente");

        txtTotal.setEditable(false);
        txtNumero.setEditable(false);
        txtFecha.setEditable(false);
    }

    private void cargarClientes() {

        cmbCliente.removeAllItems();

        for (Cliente cliente : clienteController.listarClientes()) {
            cmbCliente.addItem(cliente.getCodigo());
        }
    }

    private void cargarVehiculos() {

        cmbVehiculo.removeAllItems();

        String codigoCliente = (String) cmbCliente.getSelectedItem();

        if (codigoCliente == null) {
            return;
        }

        try {
            for (Vehiculo vehiculo
                    : vehiculoController.listarPorCliente(codigoCliente)) {

                cmbVehiculo.addItem(vehiculo.getPlaca());
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }

    private void cargarServicios() {

        cmbServicio.removeAllItems();

        for (Servicio servicio : servicioController.listarServicios()) {
            cmbServicio.addItem(servicio.getCodigo());
        }
    }

    private void cargarRepuestos() {

        cmbRepuesto.removeAllItems();

        for (Repuesto repuesto : repuestoController.listarRepuestos()) {

            if (repuesto.getCantidad() > 0) {
                cmbRepuesto.addItem(repuesto.getCodigo());
            }
        }
    }

    private void generarNumeroOrden() {

        int numero = ordenTrabajoController.listarOrdenes().size() + 1;

        txtNumero.setText("OT-" + String.format("%03d", numero));
    }

    private void generarFecha() {

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        txtFecha.setText(formato.format(new Date()));
    }

    private void configurarTablaServicios() {

        javax.swing.table.DefaultTableModel modelo
                = new javax.swing.table.DefaultTableModel(
                        new Object[]{"Código", "Descripción", "Precio"}, 0);

        tablaServicios.setModel(modelo);
    }

    private void configurarTablaRepuestos() {

        javax.swing.table.DefaultTableModel modelo
                = new javax.swing.table.DefaultTableModel(
                        new Object[]{"Código", "Nombre", "Precio unitario"}, 0);

        tablaRepuestos.setModel(modelo);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        panelDatos = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblNumeroOrden = new javax.swing.JLabel();
        lblCliente = new javax.swing.JLabel();
        lblFecha = new javax.swing.JLabel();
        lblDiagnostico = new javax.swing.JLabel();
        lblMecanico = new javax.swing.JLabel();
        lblVehiculo = new javax.swing.JLabel();
        lblEstado = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        txtFecha = new javax.swing.JTextField();
        txtDiagnostico = new javax.swing.JTextField();
        txtMecanico = new javax.swing.JTextField();
        cmbCliente = new javax.swing.JComboBox<>();
        cmbVehiculo = new javax.swing.JComboBox<>();
        cmbEstado = new javax.swing.JComboBox<>();
        panelServicios = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaServicios = new javax.swing.JTable();
        cmbServicio = new javax.swing.JComboBox<>();
        btnAgregarServicio = new javax.swing.JButton();
        panelRepuestos = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        scrollRepuestos = new javax.swing.JScrollPane();
        tablaRepuestos = new javax.swing.JTable();
        cmbRepuesto = new javax.swing.JComboBox<>();
        btnAgregarRepuesto = new javax.swing.JButton();
        panelAcciones = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtTotal = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnConsultar = new javax.swing.JButton();
        btnFinalizar = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();
        progressBar = new javax.swing.JProgressBar();
        lblProgreso = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ordenes");

        lblTitulo.setText("ORDENES DE TRABAJO");

        jLabel2.setText("DATOS DE LA ORDEN");

        lblNumeroOrden.setText("Número de Orden");

        lblCliente.setText("Cliente");

        lblFecha.setText("Fecha");

        lblDiagnostico.setText("Diagnóstico");

        lblMecanico.setText("Mecánico");

        lblVehiculo.setText("Vehículo");

        lblEstado.setText("Estado");

        txtNumero.setEditable(false);
        txtNumero.addActionListener(this::txtNumeroActionPerformed);

        txtFecha.setEditable(false);
        txtFecha.addActionListener(this::txtFechaActionPerformed);

        txtDiagnostico.addActionListener(this::txtDiagnosticoActionPerformed);

        txtMecanico.addActionListener(this::txtMecanicoActionPerformed);

        cmbCliente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cmbCliente.addActionListener(this::cmbClienteActionPerformed);

        cmbVehiculo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout panelDatosLayout = new javax.swing.GroupLayout(panelDatos);
        panelDatos.setLayout(panelDatosLayout);
        panelDatosLayout.setHorizontalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelDatosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addGroup(panelDatosLayout.createSequentialGroup()
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelDatosLayout.createSequentialGroup()
                                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(panelDatosLayout.createSequentialGroup()
                                        .addComponent(lblEstado)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(panelDatosLayout.createSequentialGroup()
                                        .addComponent(lblCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(lblNumeroOrden))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumero))
                            .addGroup(panelDatosLayout.createSequentialGroup()
                                .addComponent(lblDiagnostico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(66, 66, 66)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFecha)
                            .addComponent(lblVehiculo)
                            .addComponent(lblMecanico))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cmbVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(53, 53, 53))
        );
        panelDatosLayout.setVerticalGroup(
            panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelDatosLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNumeroOrden)
                    .addComponent(lblFecha)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(cmbCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCliente)
                    .addComponent(lblVehiculo)
                    .addComponent(cmbVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblDiagnostico)
                    .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblMecanico)
                    .addComponent(txtMecanico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(panelDatosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblEstado)
                    .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9))
        );

        jLabel3.setText("SERVICIOS");

        tablaServicios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tablaServicios);

        cmbServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAgregarServicio.setText("Agregar");
        btnAgregarServicio.addActionListener(this::btnAgregarServicioActionPerformed);

        javax.swing.GroupLayout panelServiciosLayout = new javax.swing.GroupLayout(panelServicios);
        panelServicios.setLayout(panelServiciosLayout);
        panelServiciosLayout.setHorizontalGroup(
            panelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(panelServiciosLayout.createSequentialGroup()
                        .addGroup(panelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(panelServiciosLayout.createSequentialGroup()
                                .addComponent(cmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregarServicio)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelServiciosLayout.setVerticalGroup(
            panelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelServiciosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelServiciosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarServicio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 7, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setText("REPUESTOS");

        tablaRepuestos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollRepuestos.setViewportView(tablaRepuestos);

        cmbRepuesto.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnAgregarRepuesto.setText("Agregar");
        btnAgregarRepuesto.addActionListener(this::btnAgregarRepuestoActionPerformed);

        javax.swing.GroupLayout panelRepuestosLayout = new javax.swing.GroupLayout(panelRepuestos);
        panelRepuestos.setLayout(panelRepuestosLayout);
        panelRepuestosLayout.setHorizontalGroup(
            panelRepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRepuestosLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelRepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollRepuestos, javax.swing.GroupLayout.DEFAULT_SIZE, 632, Short.MAX_VALUE)
                    .addGroup(panelRepuestosLayout.createSequentialGroup()
                        .addGroup(panelRepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(panelRepuestosLayout.createSequentialGroup()
                                .addComponent(cmbRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnAgregarRepuesto)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        panelRepuestosLayout.setVerticalGroup(
            panelRepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelRepuestosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelRepuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbRepuesto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarRepuesto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel5.setText("TOTAL DE LA ORDEN");

        lblTotal.setText("Total:");

        txtTotal.setEditable(false);
        txtTotal.setText("L. 0.00");
        txtTotal.addActionListener(this::txtTotalActionPerformed);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(this::btnGuardarActionPerformed);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnConsultar.setText("Consultar");
        btnConsultar.addActionListener(this::btnConsultarActionPerformed);

        btnFinalizar.setText("Finalizar");
        btnFinalizar.addActionListener(this::btnFinalizarActionPerformed);

        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(this::btnLimpiarActionPerformed);

        btnListar.setText("Listar");
        btnListar.addActionListener(this::btnListarActionPerformed);

        lblProgreso.setText("Progreso de finalización: 0%");

        javax.swing.GroupLayout panelAccionesLayout = new javax.swing.GroupLayout(panelAcciones);
        panelAcciones.setLayout(panelAccionesLayout);
        panelAccionesLayout.setHorizontalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAccionesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelAccionesLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5)))
                    .addGroup(panelAccionesLayout.createSequentialGroup()
                        .addGap(144, 144, 144)
                        .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnCancelar)
                            .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnFinalizar)
                            .addComponent(btnConsultar))
                        .addGap(18, 18, 18)
                        .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnLimpiar)
                            .addComponent(btnListar)))
                    .addGroup(panelAccionesLayout.createSequentialGroup()
                        .addGap(121, 121, 121)
                        .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblProgreso)
                            .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(134, Short.MAX_VALUE))
        );

        panelAccionesLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnConsultar, btnFinalizar, btnGuardar, btnLimpiar, btnListar});

        panelAccionesLayout.setVerticalGroup(
            panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAccionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(9, 9, 9)
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTotal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultar)
                    .addComponent(btnListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAccionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar)
                    .addComponent(btnLimpiar)
                    .addComponent(btnFinalizar))
                .addGap(18, 18, 18)
                .addComponent(lblProgreso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        panelAccionesLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnCancelar, btnConsultar, btnFinalizar, btnGuardar, btnLimpiar, btnListar});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAcciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 651, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(lblTitulo))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelRepuestos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelServicios, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelDatos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelServicios, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelRepuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelAcciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtTotalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalActionPerformed

    private void txtMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMecanicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMecanicoActionPerformed

    private void txtDiagnosticoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiagnosticoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiagnosticoActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    private void txtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumeroActionPerformed

    private void cmbClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbClienteActionPerformed
        // TODO add your handling code here:
        cargarVehiculos();
    }//GEN-LAST:event_cmbClienteActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        String numero = txtNumero.getText();
        String codigoCliente = (String) cmbCliente.getSelectedItem();
        String placa = (String) cmbVehiculo.getSelectedItem();
        String diagnostico = txtDiagnostico.getText().trim();
        String mecanico = txtMecanico.getText().trim();

        if (codigoCliente == null || placa == null
                || diagnostico.isEmpty() || mecanico.isEmpty()) {

            JOptionPane.showMessageDialog(this,
                    "Complete todos los campos de la orden");
            return;
        }

        try {

            ordenTrabajoController.registrarOrden(
                    numero, codigoCliente, placa, diagnostico, mecanico);

            JOptionPane.showMessageDialog(this,
                    "Orden registrada correctamente");

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAgregarServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarServicioActionPerformed
        // TODO add your handling code here:
        String numero = txtNumero.getText();
        String codigo = (String) cmbServicio.getSelectedItem();

        if (codigo == null) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un servicio");
            return;
        }

        OrdenTrabajo orden = ordenTrabajoController.buscarOrden(numero);

        if (orden == null) {
            JOptionPane.showMessageDialog(this,
                    "Primero debe guardar la orden");
            return;
        }

        try {

            Servicio servicio = servicioController.buscarServicio(codigo);

            ordenTrabajoController.agregarServicio(numero, codigo);

            javax.swing.table.DefaultTableModel modelo
                    = (javax.swing.table.DefaultTableModel) tablaServicios.getModel();

            modelo.addRow(new Object[]{
                servicio.getCodigo(),
                servicio.getDescripcion(),
                servicio.getPrecioBase()
            });

            txtTotal.setText(String.format("L. %.2f", orden.getTotal()));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarServicioActionPerformed

    private void btnAgregarRepuestoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarRepuestoActionPerformed
        // TODO add your handling code here:
        String numero = txtNumero.getText();
        String codigo = (String) cmbRepuesto.getSelectedItem();

        if (codigo == null) {
            JOptionPane.showMessageDialog(this,
                    "Seleccione un repuesto");
            return;
        }

        OrdenTrabajo orden = ordenTrabajoController.buscarOrden(numero);

        if (orden == null) {
            JOptionPane.showMessageDialog(this,
                    "Primero debe guardar la orden");
            return;
        }

        try {

            Repuesto repuesto = repuestoController.buscarRepuesto(codigo);

            ordenTrabajoController.agregarRepuesto(numero, codigo);

            javax.swing.table.DefaultTableModel modelo
                    = (javax.swing.table.DefaultTableModel) tablaRepuestos.getModel();

            modelo.addRow(new Object[]{
                repuesto.getCodigo(),
                repuesto.getNombre(),
                repuesto.getPrecioUnitario()
            });

            txtTotal.setText(String.format("L. %.2f", orden.getTotal()));

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }//GEN-LAST:event_btnAgregarRepuestoActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        String numero = txtNumero.getText();

        OrdenTrabajo orden = ordenTrabajoController.buscarOrden(numero);

        if (orden == null) {
            JOptionPane.showMessageDialog(this,
                    "Primero debe consultar una orden registrada");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de cancelar esta orden?",
                "Confirmar cancelación",
                JOptionPane.YES_NO_OPTION);

        if (respuesta == JOptionPane.YES_OPTION) {

            try {

                ordenTrabajoController.cambiarEstado(numero, "Cancelada");

                cmbEstado.setSelectedItem("Cancelada");

                JOptionPane.showMessageDialog(this,
                        "Orden cancelada correctamente");

            } catch (Exception e) {

                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        }
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinalizarActionPerformed
        // TODO add your handling code here:
        String numero = txtNumero.getText().trim();

        OrdenTrabajo orden = ordenTrabajoController.buscarOrden(numero);

        if (orden == null) {
            JOptionPane.showMessageDialog(this,
                    "Primero debe guardar la orden");
            return;
        }

        if (!orden.getEstado().equals("Pendiente")) {
            JOptionPane.showMessageDialog(this,
                    "La orden no está disponible para procesarse");
            return;
        }

        int respuesta = JOptionPane.showConfirmDialog(this,
                "¿Desea finalizar esta orden de trabajo?",
                "Confirmar finalización",
                JOptionPane.YES_NO_OPTION);

        if (respuesta != JOptionPane.YES_OPTION) {
            return;
        }

        progressBar.setValue(0);
        lblProgreso.setText("Progreso de finalización: 0%");
        btnFinalizar.setEnabled(false);

        ProcesoServicioHilo proceso = new ProcesoServicioHilo(
                orden,
                new ProcesoServicioHilo.ProcesoListener() {

            @Override
            public void onInicio() {
                cmbEstado.setSelectedItem("En proceso");
                lblProgreso.setText("Procesando orden...");
            }

            @Override
            public void onProgreso(int progreso) {
                progressBar.setValue(progreso);
                lblProgreso.setText(
                        "Progreso de finalización: " + progreso + "%");
            }

            @Override
            public void onFinalizado() {
                try {
                    ordenTrabajoController.finalizarOrden(numero);

                    cmbEstado.setSelectedItem("Finalizada");
                    cargarRepuestos();

                    JOptionPane.showMessageDialog(
                            FormularioOrdenes.this,
                            "Orden finalizada correctamente");

                } catch (Exception e) {
                    orden.setEstado("Pendiente");
                    cmbEstado.setSelectedItem("Pendiente");

                    progressBar.setValue(0);

                    JOptionPane.showMessageDialog(
                            FormularioOrdenes.this,
                            e.getMessage());
                }

                btnFinalizar.setEnabled(true);
            }

            @Override
            public void onError(String mensaje) {
                btnFinalizar.setEnabled(true);

                JOptionPane.showMessageDialog(
                        FormularioOrdenes.this,
                        mensaje);
            }
        });

        Thread hilo = new Thread(proceso);
        hilo.start();
    }//GEN-LAST:event_btnFinalizarActionPerformed

    private void btnConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarActionPerformed
        // TODO add your handling code here:
        String numero = JOptionPane.showInputDialog(this,
                "Ingrese el número de la orden (OT-001):",
                "Consultar orden",
                JOptionPane.QUESTION_MESSAGE);

        if (numero == null || numero.trim().isEmpty()) {
            return;
        }

        OrdenTrabajo orden = ordenTrabajoController.buscarOrden(numero.trim());

        if (orden == null) {
            JOptionPane.showMessageDialog(this,
                    "No se encontró la orden");
            return;
        }

        txtNumero.setText(orden.getNumero());

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        txtFecha.setText(formato.format(orden.getFecha()));

        txtDiagnostico.setText(orden.getDiagnostico());
        txtMecanico.setText(orden.getMecanico());

        cmbCliente.setSelectedItem(orden.getCliente().getCodigo());
        cmbVehiculo.setSelectedItem(orden.getVehiculo().getPlaca());
        cmbEstado.setSelectedItem(orden.getEstado());

        txtTotal.setText(String.format("L. %.2f", orden.getTotal()));

        javax.swing.table.DefaultTableModel modeloServicios
                = (javax.swing.table.DefaultTableModel) tablaServicios.getModel();

        modeloServicios.setRowCount(0);

        for (Servicio servicio : orden.getServicios()) {
            modeloServicios.addRow(new Object[]{
                servicio.getCodigo(),
                servicio.getDescripcion(),
                servicio.getPrecioBase()
            });
        }

        javax.swing.table.DefaultTableModel modeloRepuestos
                = (javax.swing.table.DefaultTableModel) tablaRepuestos.getModel();

        modeloRepuestos.setRowCount(0);

        for (Repuesto repuesto : orden.getRepuestos()) {
            modeloRepuestos.addRow(new Object[]{
                repuesto.getCodigo(),
                repuesto.getNombre(),
                repuesto.getPrecioUnitario()
            });
        }
    }//GEN-LAST:event_btnConsultarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // TODO add your handling code here:
        List<OrdenTrabajo> lista = ordenTrabajoController.listarOrdenes();

        if (lista.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No hay órdenes registradas");
            return;
        }

        String[] columnas = {
            "Número", "Fecha", "Cliente", "Vehículo",
            "Mecánico", "Total", "Estado"
        };

        javax.swing.table.DefaultTableModel modelo
                = new javax.swing.table.DefaultTableModel(columnas, 0);

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

        for (OrdenTrabajo orden : lista) {

            modelo.addRow(new Object[]{
                orden.getNumero(),
                formato.format(orden.getFecha()),
                orden.getCliente().getCodigo(),
                orden.getVehiculo().getPlaca(),
                orden.getMecanico(),
                String.format("L. %.2f", orden.getTotal()),
                orden.getEstado()
            });
        }

        JTable tabla = new JTable(modelo);

        JScrollPane scroll = new JScrollPane(tabla);

        scroll.setPreferredSize(new Dimension(750, 300));

        JOptionPane.showMessageDialog(this,
                scroll,
                "Órdenes de trabajo registradas",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        // TODO add your handling code here:
        txtDiagnostico.setText("");
        txtMecanico.setText("");
        txtTotal.setText("L. 0.00");

        tablaServicios.setModel(
                new javax.swing.table.DefaultTableModel(
                        new Object[]{"Código", "Descripción", "Precio"}, 0));

        tablaRepuestos.setModel(
                new javax.swing.table.DefaultTableModel(
                        new Object[]{"Código", "Nombre", "Precio unitario"}, 0));

        if (cmbCliente.getItemCount() > 0) {
            cmbCliente.setSelectedIndex(0);
        }

        if (cmbEstado.getItemCount() > 0) {
            cmbEstado.setSelectedItem("Pendiente");
        }

        cargarVehiculos();
        cargarRepuestos();

        generarNumeroOrden();
        generarFecha();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new FormularioOrdenes().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarRepuesto;
    private javax.swing.JButton btnAgregarServicio;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConsultar;
    private javax.swing.JButton btnFinalizar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnListar;
    private javax.swing.JComboBox<String> cmbCliente;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JComboBox<String> cmbRepuesto;
    private javax.swing.JComboBox<String> cmbServicio;
    private javax.swing.JComboBox<String> cmbVehiculo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblCliente;
    private javax.swing.JLabel lblDiagnostico;
    private javax.swing.JLabel lblEstado;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblMecanico;
    private javax.swing.JLabel lblNumeroOrden;
    private javax.swing.JLabel lblProgreso;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblVehiculo;
    private javax.swing.JPanel panelAcciones;
    private javax.swing.JPanel panelDatos;
    private javax.swing.JPanel panelRepuestos;
    private javax.swing.JPanel panelServicios;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JScrollPane scrollRepuestos;
    private javax.swing.JTable tablaRepuestos;
    private javax.swing.JTable tablaServicios;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtMecanico;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtTotal;
    // End of variables declaration//GEN-END:variables
}
