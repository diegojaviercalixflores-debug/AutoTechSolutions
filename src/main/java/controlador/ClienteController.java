/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.Cliente;
import excepciones.ClienteNoEncontradoException;
import repositorio.RepositorioGenerico;
import java.util.*;

/**
 *
 * @author Diego Calix
 */
public class ClienteController {

    private RepositorioGenerico<Cliente> repositorio;

    public ClienteController(RepositorioGenerico<Cliente> repositorio) {
        this.repositorio = repositorio;
    }

    public void registrarCliente(String codigo, String nombre,
            String identidad, String telefono, String direccion) {

        Cliente cliente = new Cliente(codigo, nombre, identidad,
                telefono, direccion);

        repositorio.agregar(codigo, cliente);
    }

    public Cliente buscarCliente(String codigo)
            throws ClienteNoEncontradoException {

        Cliente cliente = repositorio.buscarPorClave(codigo);

        if (cliente == null) {
            throw new ClienteNoEncontradoException("El cliente no existe");

        }

        return cliente;
    }

    public List<Cliente> listarClientes() {
        return repositorio.obtenerTodos();
    }

    public void modificarCliente(String codigo, String nombre,
            String identidad, String telefono, String direccion)
            throws ClienteNoEncontradoException {

        Cliente cliente = buscarCliente(codigo);

        cliente.setNombreCompleto(nombre);
        cliente.setIdentidad(identidad);
        cliente.setTelefono(telefono);
        cliente.setDireccion(direccion);
    }

    public void eliminarCliente(String codigo) throws ClienteNoEncontradoException {
        Cliente cliente = buscarCliente(codigo);

        if (cliente == null) {
            throw new ClienteNoEncontradoException("Cliente no encontrado");
        }

        repositorio.eliminar(codigo);

    }

    public Cliente deshacerUltimo() {
        return repositorio.deshacerUltimo();
    }
}
