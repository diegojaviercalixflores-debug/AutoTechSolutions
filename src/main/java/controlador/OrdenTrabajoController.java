/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cliente;
import modelo.Servicio;
import modelo.Vehiculo;
import modelo.Repuesto;
import modelo.OrdenTrabajo;
import repositorio.RepositorioGenerico;
import excepciones.*;
import java.util.*;

/**
 *
 * @author Diego Calix
 */
public class OrdenTrabajoController {

    private RepositorioGenerico<OrdenTrabajo> repositorio;
    private ClienteController clienteController;
    private VehiculoController vehiculoController;
    private ServicioController servicioController;
    private RepuestoController repuestoController;

    public OrdenTrabajoController(
            RepositorioGenerico<OrdenTrabajo> repositorio,
            ClienteController clienteController,
            VehiculoController vehiculoController,
            ServicioController servicioController,
            RepuestoController repuestoController) {

        this.repositorio = repositorio;
        this.clienteController = clienteController;
        this.vehiculoController = vehiculoController;
        this.servicioController = servicioController;
        this.repuestoController = repuestoController;
    }

    public void registrarOrden(String numero, String codigoCliente,
            String placa, String diagnostico, String mecanico)
            throws ClienteNoEncontradoException {

        Cliente cliente = clienteController.buscarCliente(codigoCliente);
        Vehiculo vehiculo = vehiculoController.buscarVehiculo(placa);

        if (vehiculo == null) {
            throw new IllegalArgumentException("El vehículo no existe");
        }

        OrdenTrabajo orden = new OrdenTrabajo(
                numero, new Date(), cliente, vehiculo, diagnostico, mecanico);

        repositorio.agregar(numero, orden);
    }

    public OrdenTrabajo buscarOrden(String numero) {
        return repositorio.buscarPorClave(numero);
    }

    public List<OrdenTrabajo> listarOrdenes() {
        return repositorio.obtenerTodos();
    }

    public void agregarServicio(String numeroOrden, String codigoServicio) {
        OrdenTrabajo orden = buscarOrden(numeroOrden);
        Servicio servicio = servicioController.buscarServicio(codigoServicio);

        if (orden == null || servicio == null) {
            throw new IllegalArgumentException("No se encontró la orden o el servicio");

        }

        orden.agregarServicio(servicio);
    }

    public void agregarRepuesto(String numeroOrden, String codigoRepuesto)
            throws StockInsuficienteException {

        OrdenTrabajo orden = buscarOrden(numeroOrden);
        Repuesto repuesto = repuestoController.buscarRepuesto(codigoRepuesto);

        if (orden == null || repuesto == null) {
            throw new IllegalArgumentException(
                    "No se encontró la orden o el repuesto");
        }

        if (!orden.getEstado().equals("Pendiente")
                && !orden.getEstado().equals("En proceso")) {
            throw new IllegalArgumentException(
                    "No se pueden agregar repuestos a esta orden");
        }

        if (repuesto.getCantidad() <= 0) {
            throw new StockInsuficienteException(
                    "No hay existencias de este repuesto");
        }

        orden.agregarRepuesto(repuesto);
    }

    public void finalizarOrden(String numeroOrden)
            throws StockInsuficienteException {

        OrdenTrabajo orden = buscarOrden(numeroOrden);

        if (orden == null) {
            throw new IllegalArgumentException("La orden no existe");
        }

        if (orden.getEstado().equals("Finalizada")
                || orden.getEstado().equals("Cancelada")) {
            throw new IllegalArgumentException(
                    "La orden ya no se puede finalizar");
        }

        HashMap<String, Integer> cantidadesNecesarias = new HashMap<>();
        HashMap<String, Repuesto> repuestosOrden = new HashMap<>();

        for (Repuesto repuesto : orden.getRepuestos()) {
            String codigo = repuesto.getCodigo();

            int cantidad = cantidadesNecesarias.getOrDefault(codigo, 0);
            cantidadesNecesarias.put(codigo, cantidad + 1);

            repuestosOrden.put(codigo, repuesto);
        }

        for (String codigo : cantidadesNecesarias.keySet()) {

            Repuesto repuesto = repuestoController.buscarRepuesto(codigo);
            int cantidadNecesaria = cantidadesNecesarias.get(codigo);

            if (repuesto == null || repuesto.getCantidad() < cantidadNecesaria) {
                throw new StockInsuficienteException(
                        "Existencias insuficientes del repuesto: " + codigo);
            }
        }

        for (String codigo : cantidadesNecesarias.keySet()) {

            Repuesto repuesto = repuestoController.buscarRepuesto(codigo);
            int cantidadNecesaria = cantidadesNecesarias.get(codigo);

            repuesto.setCantidad(
                    repuesto.getCantidad() - cantidadNecesaria);
        }

        orden.setEstado("Finalizada");
    }

    public void cambiarEstado(String numeroOrden, String nuevoEstado) {

        OrdenTrabajo orden = buscarOrden(numeroOrden);

        if (orden == null) {
            throw new IllegalArgumentException("La orden no existe");
        }

        if (!nuevoEstado.equals("Pendiente")
                && !nuevoEstado.equals("En proceso")
                && !nuevoEstado.equals("Cancelada")) {
            throw new IllegalArgumentException("Estado no válido");
        }

        if (orden.getEstado().equals("Finalizada")
                || orden.getEstado().equals("Cancelada")) {
            throw new IllegalArgumentException(
                    "No se puede cambiar el estado de esta orden");
        }

        orden.setEstado(nuevoEstado);
    }
}
