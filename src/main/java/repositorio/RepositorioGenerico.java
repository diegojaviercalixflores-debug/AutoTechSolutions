/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repositorio;

import java.util.*;

/**
 *
 * @author Diego Calix
 */
public class RepositorioGenerico<T> {

    private List<T> listaElementos = new ArrayList<>();
    private Map<String, T> mapaElementos = new HashMap<>();
    private Stack<T> historialOperaciones = new Stack<>();
    private Stack<String> clavesHistorial = new Stack<>();

    public void agregar(String clave, T elemento) {

        if (clave == null || clave.trim().isEmpty() || elemento == null) {
            throw new IllegalArgumentException("Datos no validos");
        }

        if (mapaElementos.containsKey(clave)) {
            throw new IllegalArgumentException("La clave ya existe");
        }

        listaElementos.add(elemento);
        mapaElementos.put(clave, elemento);
        historialOperaciones.push(elemento);
        clavesHistorial.push(clave);
    }

    public List<T> obtenerTodos() {
        return Collections.unmodifiableList(listaElementos);
    }

    public T buscarPorClave(String clave) {
        return mapaElementos.get(clave);
    }

    public T deshacerUltimo() {

        if (!historialOperaciones.isEmpty()) {

            T ultimo = historialOperaciones.pop();
            String clave = clavesHistorial.pop();

            listaElementos.remove(listaElementos.size() - 1);
            mapaElementos.remove(clave);

            return ultimo;
        }

        return null;
    }
    
    public void eliminar(String clave) {
    T elemento = mapaElementos.remove(clave);

    if (elemento != null) {
        listaElementos.remove(elemento);
    }
}
}
