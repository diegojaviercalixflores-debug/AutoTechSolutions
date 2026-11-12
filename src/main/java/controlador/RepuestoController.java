/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Repuesto;
import repositorio.RepositorioGenerico;
import java.util.*;

/**
 *
 * @author Diego Calix
 */
public class RepuestoController {

    private RepositorioGenerico<Repuesto> repositorio;

    public RepuestoController(RepositorioGenerico<Repuesto> repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarRepuesto(String codigo, String nombre,
            int cantidad, double precioUnitario, int stockMinimo) {

        Repuesto repuesto = new Repuesto(
                codigo, nombre, cantidad, precioUnitario, stockMinimo);

        repositorio.agregar(codigo, repuesto);
    }

    public Repuesto buscarRepuesto(String codigo) {
        return repositorio.buscarPorClave(codigo);
    }

    public List<Repuesto> listarRepuestos() {
        return repositorio.obtenerTodos();
    }

    public void modificarRepuesto(String codigo, String nombre,
            int cantidad, double precioUnitario, int stockMinimo) {

        Repuesto repuesto = buscarRepuesto(codigo);

        if (repuesto == null) {
            throw new IllegalArgumentException("El repuesto no existe");
        }

        repuesto.setNombre(nombre);
        repuesto.setCantidad(cantidad);
        repuesto.setPrecioUnitario(precioUnitario);
        repuesto.setStockMinimo(stockMinimo);
    }

    public void eliminarRepuesto(String codigo) {
        Repuesto repuesto = buscarRepuesto(codigo);

        if (repuesto == null) {
            throw new IllegalArgumentException("El repuesto no existe");
        }

        repositorio.eliminar(codigo);
    }

    public void registrarEntrada(String codigo, int cantidad) {
        Repuesto repuesto = buscarRepuesto(codigo);

        if (repuesto == null) {
            throw new IllegalArgumentException("El repuesto no existe");
        }

        if (cantidad <= 0) {
            throw new IllegalArgumentException(
                    "La cantidad de entrada debe ser positiva");
        }

        repuesto.setCantidad(repuesto.getCantidad() + cantidad);
    }
}
