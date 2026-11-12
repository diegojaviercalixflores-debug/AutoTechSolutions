/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author Diego Calix
 */
public class Servicio {

    private String codigo;
    private String descripcion;
    private double precioBase;
    private int tiempoEstimado;

    public Servicio(String codigo, String descripcion,
            double precioBase, int tiempoEstimado) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.precioBase = precioBase;
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getPrecioBase() {
        return precioBase;
    }

    public int getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecioBase(double precioBase) {
        this.precioBase = precioBase;
    }

    public void setTiempoEstimado(int tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }
}
