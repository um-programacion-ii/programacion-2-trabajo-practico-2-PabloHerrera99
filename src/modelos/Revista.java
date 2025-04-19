package src.modelos;

import src.enums.CategoriaRecurso;
import src.enums.EstadoRecurso;
import src.interfaces.Prestable;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Revista extends RecursoDigital implements Prestable {
    private String tipo;
    private int edicion;
    private Usuario usuarioPrestamo;
    private LocalDateTime fechaDevolucion;

    public Revista(String titulo, String autor, String tipo, int edicion) {
        super(titulo, autor);
        this.tipo = tipo;
        this.edicion = edicion;
    }

    //getters y setters
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

    //Categoria
    @Override
    public List<CategoriaRecurso> getCategoria() {
        return List.of(
                CategoriaRecurso.REVISTA,
                CategoriaRecurso.PRESTABLE
        );
    }

    // Prestamos
    @Override
    public boolean estaDisponible() {
        return estado == EstadoRecurso.DISPONIBLE;
    }
    @Override
    public LocalDateTime getFechaDevolucion() {
        return LocalDateTime.now();
    }
    @Override
    public void prestar(Usuario usuario) {
        if (estaDisponible()) {
            this.usuarioPrestamo = usuario;
            this.fechaDevolucion = LocalDateTime.now().plusDays(7);
            this.estado = EstadoRecurso.PRESTADO;
            System.out.println("La Revista fue prestada");
        }else {
            System.out.println("La Revista no esta disponible");
        }
    }
    public boolean devolver() {
        if (estado == EstadoRecurso.PRESTADO) {
            usuarioPrestamo = null;
            fechaDevolucion = null;
            estado = EstadoRecurso.DISPONIBLE;
            System.out.println("La Revista fue devuelta");
            return true;
        }
        return false;
    }

    // String
    @Override
    public String toString() {
        return super.toString() +
                "\nTipo: " + tipo +
                "\nEdicion: " + edicion;
    }
}
