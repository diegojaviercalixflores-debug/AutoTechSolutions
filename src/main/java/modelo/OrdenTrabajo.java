/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.*;

/**
 *
 * @author Diego Calix
 */
public class OrdenTrabajo {

    private String numero;
    private Date fecha;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private String diagnostico;
    private String mecanico;
    private ArrayList<Servicio> servicios;
    private ArrayList<Repuesto> repuestos;
    private double total;
    private String estado;

    public OrdenTrabajo(String numero, Date fecha, Cliente cliente,
            Vehiculo vehiculo, String diagnostico, String mecanico) {
        this.numero = numero;
        this.fecha = fecha;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.diagnostico = diagnostico;
        this.mecanico = mecanico;
        this.servicios = new ArrayList<>();
        this.repuestos = new ArrayList<>();
        this.total = 0;
        this.estado = "Pendiente";
    }

    public void agregarServicio(Servicio servicio) {
        servicios.add(servicio);
        total += servicio.getPrecioBase();
    }

    public void agregarRepuesto(Repuesto repuesto) {
        repuestos.add(repuesto);
        total += repuesto.getPrecioUnitario();
    }

    public String getNumero() {
        return numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getMecanico() {
        return mecanico;
    }

    public ArrayList<Servicio> getServicios() {
        return servicios;
    }

    public ArrayList<Repuesto> getRepuestos() {
        return repuestos;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setMecanico(String mecanico) {
        this.mecanico = mecanico;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
