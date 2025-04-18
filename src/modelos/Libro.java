package src.modelos;

import src.enums.CategoriaRecurso;
import src.enums.EstadoRecurso;
import src.interfaces.Renovable;

import java.util.Scanner;
import java.util.List;

public class Libro extends RecursoDigital {
    private String genero;
    private String saga;

    public Libro(String titulo, String autor, String genero, String saga) {
        super(titulo, autor);
        this.genero = genero;
        this.saga = saga;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.isEmpty()) {
            throw new IllegalArgumentException("El libro tiene que tener un genero");
        }
        this.genero = genero;
    }
    public String getGenero() {
        return genero;
    }

    public void setSaga(String saga) {
        this.saga = saga;
    }
    public String getSaga() {
        return saga;
    }

    public static Libro crearLibro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el titulo: ");
        String titulo = sc.nextLine();
        System.out.println("Ingrese el autor: ");
        String autor = sc.nextLine();
        System.out.println("Introduzca el genero: ");
        String genero = sc.nextLine();
        System.out.println("Introduzca el saga: ");
        String saga = sc.nextLine();
        return new Libro(titulo,
                autor,
                genero,
                saga);
    }

    @Override
    public List<CategoriaRecurso> getCategoria() {
        return List.of(
                CategoriaRecurso.LIBRO,
                CategoriaRecurso.PRESTABLE,
                CategoriaRecurso.RENOVABLE
        );
    }
    @Override
    public String toString() {
        return super.toString() +
                "\nGenero: " + genero +
                "\nSaga: " + saga;
    }
}
