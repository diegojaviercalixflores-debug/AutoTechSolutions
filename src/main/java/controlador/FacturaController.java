/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Factura;
import modelo.OrdenTrabajo;
import repositorio.RepositorioGenerico;
import excepciones.PagoInsuficienteException;
import java.util.*;

/**
 *
 * @author Diego Calix
 */
public class FacturaController {

    private RepositorioGenerico<Factura> repositorio;
    private OrdenTrabajoController ordenController;

    public FacturaController(
            RepositorioGenerico<Factura> repositorio,
            OrdenTrabajoController ordenController) {

        this.repositorio = repositorio;
        this.ordenController = ordenController;
    }

    public void generarFactura(String numeroFactura,
            String numeroOrden, double impuesto, double pago)
            throws PagoInsuficienteException {

        OrdenTrabajo orden = ordenController.buscarOrden(numeroOrden);

        if (orden == null) {
            throw new IllegalArgumentException("La orden no existe");
        }

        if (!orden.getEstado().equals("Finalizada")) {
            throw new IllegalArgumentException(
                    "Solo se pueden facturar órdenes finalizadas");
        }

        for (Factura factura : repositorio.obtenerTodos()) {
            if (factura.getOrden().getNumero().equals(numeroOrden)) {
                throw new IllegalArgumentException(
                        "Esta orden ya tiene una factura registrada");
            }
        }

        Factura factura = new Factura(
                numeroFactura, new Date(), orden, impuesto, pago);

        repositorio.agregar(numeroFactura, factura);
    }

    public Factura buscarFactura(String numero) {
        return repositorio.buscarPorClave(numero);
    }

    public List<Factura> listarFacturas() {
        return repositorio.obtenerTodos();
    }
}
