/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cliente;
import modelo.Vehiculo;
import repositorio.RepositorioGenerico;
import excepciones.ClienteNoEncontradoException;
import java.util.*;

/**
 *
 * @author Diego Calix
 */
public class VehiculoController {

    private RepositorioGenerico<Vehiculo> repositorioVehiculos;
    private ClienteController clienteController;

    public VehiculoController(
            RepositorioGenerico<Vehiculo> repositorioVehiculos,
            ClienteController clienteController) {

        this.repositorioVehiculos = repositorioVehiculos;
        this.clienteController = clienteController;
    }

    public void registrarVehiculo(String placa, String marca,
            String modelo, int anio, String color, String codigoCliente)
            throws ClienteNoEncontradoException {

        Cliente propietario = clienteController.buscarCliente(codigoCliente);

        Vehiculo vehiculo = new Vehiculo(placa, marca, modelo,
                anio, color, propietario);

        repositorioVehiculos.agregar(placa, vehiculo);
    }

    public Vehiculo buscarVehiculo(String placa) {
        return repositorioVehiculos.buscarPorClave(placa);
    }

    public List<Vehiculo> listarVehiculos() {
        return repositorioVehiculos.obtenerTodos();
    }

    public List<Vehiculo> listarPorCliente(String codigoCliente)
            throws ClienteNoEncontradoException {

        Cliente cliente = clienteController.buscarCliente(codigoCliente);

        List<Vehiculo> resultado = new ArrayList<>();

        for (Vehiculo vehiculo : repositorioVehiculos.obtenerTodos()) {

            if (vehiculo.getPropietario().getCodigo()
                    .equals(codigoCliente)) {

                resultado.add(vehiculo);
            }
        }

        return resultado;
    }

    public void modificarVehiculo(String placa, String marca,
            String modelo, int anio, String color,
            String codigoCliente) throws ClienteNoEncontradoException {

        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {
            throw new IllegalArgumentException("Vehículo no encontrado");
        }

        Cliente propietario = clienteController.buscarCliente(codigoCliente);

        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        vehiculo.setAnio(anio);
        vehiculo.setColor(color);
        vehiculo.setPropietario(propietario);
    }

    public void eliminarVehiculo(String placa) {
        Vehiculo vehiculo = buscarVehiculo(placa);

        if (vehiculo == null) {
            throw new IllegalArgumentException("Vehículo no encontrado");
        }

        repositorioVehiculos.eliminar(placa);
    }
}
