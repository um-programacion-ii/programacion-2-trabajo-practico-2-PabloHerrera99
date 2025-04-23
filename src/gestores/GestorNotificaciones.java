package src.gestores;

import src.servicios.NotificacionesEmail;
import src.servicios.NotificacionesSMS;
import src.servicios.ServicioNotificaciones;
import src.interfaces.Notificacion;
import src.modelos.Usuario;

public class GestorNotificaciones {
    private ServicioNotificaciones servicioNotificaciones;
    private Usuario usuario;

    public GestorNotificaciones() {
        this.servicioNotificaciones = new ServicioNotificaciones();
        this.usuario = usuario;
    }

    public void enviarNotificacion(Usuario usuario, String mensaje) {
        Notificacion notificacionEmail = new NotificacionesEmail(usuario, mensaje);
        Notificacion notificacionSms = new NotificacionesSMS(usuario, mensaje);
        servicioNotificaciones.enviarNotificacion(notificacionEmail);
        servicioNotificaciones.enviarNotificacion(notificacionSms);
    }
}
