package src.gestores;

import src.modelos.RecursoDigital;
import src.modelos.Reserva;
import src.modelos.Usuario;

public class GestorReservas {
    public void recervarRecurso(Usuario usuario, RecursoDigital recurso) {
        Reserva reserva = new Reserva(usuario, recurso);
        recurso.getReservas().add(reserva);
        System.out.println("Reserva realizada\n " + reserva + "\n");
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
