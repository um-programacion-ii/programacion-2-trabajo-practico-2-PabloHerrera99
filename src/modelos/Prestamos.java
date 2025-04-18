package src.modelos;

import java.time.LocalDate;

public class Prestamos {
    private Usuario usuario;
    private RecursoDigital recurso;
    private LocalDate fechaDevolucion;

    public Prestamos(Usuario usuario, RecursoDigital recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaDevolucion = LocalDate.now().plusDays(7);
    }

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

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    @Override
    public String toString() {
        return "Recurso prestado: " + recurso.getTitulo() +
                "\nUsuario: " + usuario.getNombre() +
                "\nFecha de devolucion: " + fechaDevolucion;
    }
}
