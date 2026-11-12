/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.*;
import excepciones.PagoInsuficienteException;

/**
 *
 * @author Diego Calix
 */
public class Factura {

    private String numero;
    private Date fecha;
    private OrdenTrabajo orden;
    private double subtotalServicios;
    private double subtotalRepuestos;
    private double impuesto;
    private double total;
    private double pago;
    private double cambio;

    public Factura(String numero, Date fecha, OrdenTrabajo orden,
            double impuesto, double pago)
            throws PagoInsuficienteException {
        this.numero = numero;
        this.fecha = fecha;
        this.orden = orden;
        this.impuesto = impuesto;
        this.pago = pago;

        subtotalServicios = 0;
        subtotalRepuestos = 0;

        for (Servicio servicio : orden.getServicios()) {
            subtotalServicios += servicio.getPrecioBase();
        }

        for (Repuesto repuesto : orden.getRepuestos()) {
            subtotalRepuestos += repuesto.getPrecioUnitario();
        }

        double subtotal = subtotalServicios + subtotalRepuestos;
        total = subtotal + (subtotal * impuesto / 100);

        if (pago < total) {
            throw new PagoInsuficienteException(
                    "El pago no es suficiente");
        }

        cambio = pago - total;
    }

    public String getNumero() {
        return numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public OrdenTrabajo getOrden() {
        return orden;
    }

    public double getSubtotalServicios() {
        return subtotalServicios;
    }

    public double getSubtotalRepuestos() {
        return subtotalRepuestos;
    }

    public double getImpuesto() {
        return impuesto;
    }

    public double getTotal() {
        return total;
    }

    public double getPago() {
        return pago;
    }

    public double getCambio() {
        return cambio;
    }
}
