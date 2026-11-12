/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hilos;

/**
 *
 * @author Diego Calix
 */
import modelo.OrdenTrabajo;
import javax.swing.SwingUtilities;

public class ProcesoServicioHilo implements Runnable {

    private OrdenTrabajo orden;
    private ProcesoListener listener;

    public interface ProcesoListener {

        void onInicio();

        void onProgreso(int progreso);

        void onFinalizado();

        void onError(String mensaje);
    }

    public ProcesoServicioHilo(
            OrdenTrabajo orden, ProcesoListener listener) {
        this.orden = orden;
        this.listener = listener;
    }

    @Override
    public void run() {

        synchronized (orden) {
            if (!orden.getEstado().equals("Pendiente")) {
                SwingUtilities.invokeLater(()
                        -> listener.onError(
                                "La orden ya está siendo procesada"));
                return;
            }

            orden.setEstado("En proceso");
        }

        SwingUtilities.invokeLater(() -> listener.onInicio());

        try {
            for (int i = 1; i <= 5; i++) {

                Thread.sleep(1000);

                final int progreso = i * 20;

                SwingUtilities.invokeLater(()
                        -> listener.onProgreso(progreso));
            }

            SwingUtilities.invokeLater(()
                    -> listener.onFinalizado());

        } catch (InterruptedException e) {

            orden.setEstado("Pendiente");

            SwingUtilities.invokeLater(()
                    -> listener.onError(
                            "El proceso fue interrumpido"));
        }
    }
}
