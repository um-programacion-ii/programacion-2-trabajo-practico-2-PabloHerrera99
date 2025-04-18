package src.modelos;

import src.enums.TipoRecurso;
import src.interfaces.InterfazRD;
import src.interfaces.Prestable;

import java.time.LocalDate;
import java.util.List;

public abstract class RecursoDigital implements InterfazRD, Prestable {
    protected String titulo;
    protected String autor;
    protected boolean prestado;
    protected LocalDate fechaEntrega;

    public RecursoDigital(String titulo, String autor) {
        setTitulo(titulo);
        setAutor(autor);
    }
    public abstract List<TipoRecurso> getTipo();

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getAutor() {
        return autor;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public boolean getPrestado() {
        return prestado;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Titulo no puede estar vacio");
        }
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("Autor no puede estar vacio");
        }
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo +
                "\nAutor: " + autor;
    }

    @Override
    public void prestar() {
        if (!prestado) {
            prestado = true;
            fechaEntrega = LocalDate.now().plusDays(7);
        }
    }

    @Override
    public void devolver() {
        if (prestado) {
            prestado = false;
        }
    }
}
