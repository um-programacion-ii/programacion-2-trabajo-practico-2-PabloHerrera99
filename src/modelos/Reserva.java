package src.modelos;

import java.time.LocalDateTime;

public class Reserva implements Comparable<Reserva> {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDateTime fechaReserva;

    public Reserva(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDateTime.now();
    }

    //setter y getter
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public RecursoDigital getRecurso() {
        return recurso;
    }
    public void setRecurso(RecursoDigital recurso) {
        this.recurso = recurso;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }
    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    //comparacion fecha
    @Override
    public int compareTo(Reserva o) {
        return this.fechaReserva.compareTo(o.fechaReserva);
    }

    //String
    public String toString() {
        return "Recurso reservado: " + recurso.toString() +
                "\nUsuario: " + usuario.getNombre() +
                "\nFecha: " + fechaReserva + "\n";
    }
}
