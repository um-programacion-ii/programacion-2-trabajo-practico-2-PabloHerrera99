package Servicios;

import interfaces.ServicioNotificaciones;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {

    public void enviarNotificacion(String mensaje) {
        System.out.println("Notificación por email enviada:\n" +
                            mensaje);
    }
}
