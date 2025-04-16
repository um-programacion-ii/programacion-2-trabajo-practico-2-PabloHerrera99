package modelos;

import interfaces.InterfazRD;
import interfaces.Prestable;

import java.util.Scanner;

public abstract class RecursoDigital implements InterfazRD, Prestable {
    protected String titulo;
    protected String autor;
    protected boolean prestado;

    public RecursoDigital(String titulo, String autor) {
        setTitulo(titulo);
        setAutor(autor);
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getAutor() {
        return autor;
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
            System.out.println("El recurso fue prestado correctamente");
        }else {
            System.out.println("El recurso ya esta prestado");
        }
    }

    @Override
    public void devolver() {
        if (prestado) {
            prestado = false;
            System.out.println("El recurso fue devuelto correctamente");
        } else {
            System.out.println("El recurso ya esta devuelto");
        }
    }
}
