package src.modelos;

import src.enums.CategoriaRecurso;
import src.enums.EstadoRecurso;
import src.interfaces.InterfazRD;
import src.interfaces.Prestable;

import java.time.LocalDate;
import java.util.List;

public abstract class RecursoDigital implements InterfazRD {
    protected String titulo;
    protected String autor;
    protected EstadoRecurso estado;

    public RecursoDigital(String titulo, String autor) {
        setTitulo(titulo);
        setAutor(autor);
        this.estado = EstadoRecurso.DISPONIBLE;
    }
    public abstract List<CategoriaRecurso> getCategoria();

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Titulo no puede estar vacio");
        }
        this.titulo = titulo;
    }
    @Override
    public String getTitulo() {
        return titulo;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("Autor no puede estar vacio");
        }
        this.autor = autor;
    }
    @Override
    public String getAutor() {
        return autor;
    }

    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }
    public EstadoRecurso getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo +
                "\nAutor: " + autor;
    }

}
