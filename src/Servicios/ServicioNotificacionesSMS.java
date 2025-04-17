package src.Servicios;

import src.interfaces.ServicioNotificaciones;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    public void enviarNotificacion(String mensaje) {
        System.out.println("Notificación por SMS enviada:\n" +
                mensaje);
    }
}
