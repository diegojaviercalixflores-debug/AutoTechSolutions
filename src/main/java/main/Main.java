/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import repositorio.RepositorioGenerico;
import modelo.*;
import controlador.*;
import vista.VentanaPrincipal;
import controlador.VehiculoController;

/**
 *
 * @author Diego Calix
 */
public class Main {

    public static void main(String[] args) {

        RepositorioGenerico<Cliente> repoClientes
                = new RepositorioGenerico<>();

        RepositorioGenerico<Vehiculo> repoVehiculos
                = new RepositorioGenerico<>();

        RepositorioGenerico<Servicio> repoServicios
                = new RepositorioGenerico<>();

        RepositorioGenerico<Repuesto> repoRepuestos
                = new RepositorioGenerico<>();

        RepositorioGenerico<OrdenTrabajo> repoOrdenes
                = new RepositorioGenerico<>();

        RepositorioGenerico<Factura> repoFacturas
                = new RepositorioGenerico<>();

        ClienteController clienteController
                = new ClienteController(repoClientes);

        VehiculoController vehiculoController
                = new VehiculoController(repoVehiculos, clienteController);

        ServicioController servicioController
                = new ServicioController(repoServicios);

        RepuestoController repuestoController
                = new RepuestoController(repoRepuestos);

        OrdenTrabajoController ordenController
                = new OrdenTrabajoController(
                        repoOrdenes,
                        clienteController,
                        vehiculoController,
                        servicioController,
                        repuestoController);

        FacturaController facturaController
                = new FacturaController(repoFacturas, ordenController);

        VentanaPrincipal ventana = new VentanaPrincipal(
                clienteController,
                vehiculoController,
                servicioController,
                repuestoController,
                ordenController,
                facturaController
        );
        ventana.setVisible(true);
    }
}
