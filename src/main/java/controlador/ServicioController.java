/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Servicio;
import repositorio.RepositorioGenerico;
import java.util.*;

/**
 *
 * @author Diego Calix
 */
public class ServicioController {

    private RepositorioGenerico<Servicio> repositorio;

    public ServicioController(RepositorioGenerico<Servicio> repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarServicio(String codigo, String descripcion,
            double precioBase, int tiempoEstimado) {

        Servicio servicio = new Servicio(
                codigo, descripcion, precioBase, tiempoEstimado);

        repositorio.agregar(codigo, servicio);
    }

    public Servicio buscarServicio(String codigo) {
        return repositorio.buscarPorClave(codigo);
    }

    public List<Servicio> listarServicios() {
        return repositorio.obtenerTodos();
    }

    public void modificarServicio(String codigo, String descripcion,
            double precioBase, int tiempoEstimado) {

        Servicio servicio = buscarServicio(codigo);

        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no existe");
        }

        servicio.setDescripcion(descripcion);
        servicio.setPrecioBase(precioBase);
        servicio.setTiempoEstimado(tiempoEstimado);
    }

    public void eliminarServicio(String codigo) {

        Servicio servicio = buscarServicio(codigo);

        if (servicio == null) {
            throw new IllegalArgumentException("El servicio no existe");
        }

        repositorio.eliminar(codigo);
    }
}
