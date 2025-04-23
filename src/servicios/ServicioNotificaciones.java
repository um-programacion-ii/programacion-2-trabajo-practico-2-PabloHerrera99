package src.servicios;

import src.interfaces.Notificacion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//hecho con chatGPT (según entendí lo que se hace es uqe las notificaciones se manden usando hilos para que
// el usuario no tenga que esperar a que se manden las notificaciones antes de seguir usando la interfaz)
public class ServicioNotificaciones {
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    public void enviarNotificacion(Notificacion notification) {
        executor.submit(() -> notification.enviarNotificacion());
    }
    public void cerrar() {
        executor.shutdown();
    }
}
