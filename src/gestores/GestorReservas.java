package src.gestores;

import src.enums.EstadoRecurso;
import src.modelos.RecursoDigital;
import src.modelos.Reserva;
import src.modelos.Usuario;

public class GestorReservas {
    private GestorNotificaciones notificaciones;

    public GestorReservas() {
        this.notificaciones = new GestorNotificaciones();
    }

    public void recervarRecurso(Usuario usuario, RecursoDigital recurso) {
        Reserva reserva = new Reserva(usuario, recurso);
        if (recurso.getEstado().equals(EstadoRecurso.DISPONIBLE)) {
            recurso.setEstado(EstadoRecurso.RESERVADO);
        }
        recurso.getReservas().add(reserva);
        String mensaje = "Reserva realizada\n " + reserva + "\n";
        System.out.println(mensaje);
        notificaciones.enviarNotificacion(usuario, mensaje);
        usuario.aumentarContadorActividad();

    }
    public void cancelarReserva(Usuario usuario, RecursoDigital recurso) {
        recurso.eliminarReserva(usuario);

    }

    public void procesarReserva(RecursoDigital recurso) {
        recurso.siguienteReserva();
    }

    public void mostrarReservas(RecursoDigital recurso) {
        recurso.mostrarReservas();
    }
}