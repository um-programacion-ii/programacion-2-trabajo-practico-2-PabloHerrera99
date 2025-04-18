package src.modelos;

import src.enums.CategoriaRecurso;

import java.util.List;
import java.util.Scanner;

public class Revista extends RecursoDigital {
    private String tipo;
    private int edicion;

    public Revista(String titulo, String autor, String tipo, int edicion) {
        super(titulo, autor);
        this.tipo = tipo;
        this.edicion = edicion;
    }

    public void setTipo(String tipo) {
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Las revista tiene que tener una tipo");
        }
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
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
        System.out.println("Introduzca el tipo: ");
        String tipo = sc.nextLine();
        System.out.println("Introduzca el edición: ");
        int edicion = sc.nextInt();
        return new Revista(titulo,
                autor,
                tipo,
                edicion);
    }

    @Override
    public List<CategoriaRecurso> getCategoria() {
        return List.of(
                CategoriaRecurso.REVISTA,
                CategoriaRecurso.PRESTABLE
        );
    }
    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: " + tipo +
                "\nEdicion: " + edicion;
    }
}
