package src.modelos;

import src.enums.TipoRecurso;

import java.util.List;
import java.util.Scanner;

public class Revista extends RecursoDigital {
    private String categoria;
    private int edicion;

    public Revista(String titulo, String autor, String categoria, int edicion) {
        super(titulo, autor);
        this.categoria = categoria;
        this.edicion = edicion;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("Las revista tiene que tener una categoría");
        }
        this.categoria = categoria;
    }
    public String getCategoria() {
        return categoria;
    }

    public void setEdicion(int edicion) {
        if (edicion < 0 || edicion == 0) {
            throw new IllegalArgumentException("Las revista tiene que tener una edición");
        }
        this.edicion = edicion;
    }
    public int getEdicion() {
        return edicion;
    }

    public static Revista crearRevista() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el titulo: ");
        String titulo = sc.nextLine();
        System.out.println("Ingrese el autor: ");
        String autor = sc.nextLine();
        System.out.println("Introduzca la categoría: ");
        String categoria = sc.nextLine();
        System.out.println("Introduzca el edición: ");
        int edicion = sc.nextInt();
        return new Revista(titulo,
                autor,
                categoria,
                edicion);
    }

    @Override
    public List<TipoRecurso> getTipo() {
        return List.of(
                TipoRecurso.REVISTA,
                TipoRecurso.PRESTABLE
        );
    }
    @Override
    public String toString() {
        return super.toString() +
                "\nCategoria: " + categoria +
                "\nEdicion: " + edicion;
    }
}
