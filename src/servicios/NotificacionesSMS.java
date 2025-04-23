package src.servicios;

import src.interfaces.Notificacion;
import src.modelos.Usuario;

public class NotificacionesSMS implements Notificacion {
    private Usuario destino;
    private String mensaje;

    public NotificacionesSMS(Usuario destino, String mensaje) {
        this.destino = destino;
        this.mensaje = mensaje;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("\n[SMS] A :" + destino.getNombre() + "\n" + mensaje + "\n");
    }
}
