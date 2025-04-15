package modelos;

import interfaces.InterfazRD;

import java.util.Scanner;

public abstract class RecursoDigital implements InterfazRD {
    private String titulo;
    private String autor;

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

    protected static Object[] datosBasicos() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Titulo: ");
        String titulo = sc.nextLine();
        System.out.println("Autor: ");
        String autor = sc.nextLine();
        return new Object[]{titulo, autor};
    }

    @Override
    public String toString() {
        return "Titulo: " + titulo +
                "\nAutor: " + autor;
    }
}
