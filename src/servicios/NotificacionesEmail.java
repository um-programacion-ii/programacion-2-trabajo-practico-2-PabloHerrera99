package src.servicios;

import src.interfaces.Notificacion;
import src.modelos.Usuario;

public class NotificacionesEmail implements Notificacion {
    private Usuario destino;
    private String mensaje;

    public NotificacionesEmail(Usuario destino, String mensaje) {
        this.destino = destino;
        this.mensaje = mensaje;
    }

    @Override
    public void enviarNotificacion() {
        System.out.println("\n[EMAIL] A :" + destino.getNombre() + "\n" + mensaje + "\n");
    }
}
