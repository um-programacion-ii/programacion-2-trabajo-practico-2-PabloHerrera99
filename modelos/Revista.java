package modelos;

import interfaces.InterfazRD;
import interfaces.Prestable;

import java.util.Scanner;

public class Revista extends RecursoDigital implements Prestable {
    private String categoria;
    private int edicion;
    private boolean prestado;

    public Revista(String titulo, String autor, String categoria, int edicion) {
        super(titulo, autor);
        this.categoria = categoria;
        this.edicion = edicion;
    }

    public String getCategoria() {
        return categoria;
    }
    public int getEdicion() {
        return edicion;
    }

    public void setCategoria(String categoria) {
        if (categoria == null || categoria.isEmpty()) {
            throw new IllegalArgumentException("Las revista tiene que tener una categoría");
        }
        this.categoria = categoria;
    }
    public void setEdicion(int edicion) {
        if (edicion < 0 || edicion == 0) {
            throw new IllegalArgumentException("Las revista tiene que tener una edición");
        }
        this.edicion = edicion;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nCategoria: " + categoria +
                "\nEdicion: " + edicion;
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
    public void prestar() {
        if (!prestado) {
            prestado = true;
            System.out.println("La revista fue prestada correctamente");
        }else {
            System.out.println("La revista ya esta prestada");
        }
    }

    @Override
    public void devolver() {
        if (prestado) {
            prestado = false;
            System.out.println("La revista fue devuelta correctamente");
        } else {
            System.out.println("La revista ya esta devuelta");
        }
    }
}
