package src.Servicios;

import src.interfaces.Notificacion;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServicioNotificaciones {
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    public void enviarNotificacion(Notificacion notification) {
        executor.submit(() -> notification.enviarNotificacion());
    }
    public void cerrar() {
        executor.shutdown();
    }
}
