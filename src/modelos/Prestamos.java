package src.modelos;

import java.time.LocalDateTime;

public class Prestamos {
    private Usuario usuarioPrestamo;
    private RecursoDigital recursoPrestamo;
    private LocalDateTime fechaDevolucion;

    public Prestamos(Usuario usuarioPrestamo, RecursoDigital recursoPrestamo, LocalDateTime fechaDevolucion) {
        this.usuarioPrestamo = usuarioPrestamo;
        this.recursoPrestamo = recursoPrestamo;
        this.fechaDevolucion = fechaDevolucion;
    }

    public Usuario getUsuario() {
        return usuarioPrestamo;
    }
    public void setUsuario(Usuario usuario) {
        this.usuarioPrestamo = usuario;
    }

    public RecursoDigital getRecurso() {
        return recursoPrestamo;
    }
    public void setRecurso(RecursoDigital recurso) {
        this.recursoPrestamo = recurso;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Recurso prestado: " + recursoPrestamo.getTitulo() +
                "\nUsuario: " + usuarioPrestamo.getNombre() +
                "\nFecha de devolucion: " + fechaDevolucion;
    }
}
