package src.modelos;

import src.enums.CategoriaRecurso;
import src.enums.EstadoRecurso;
import src.interfaces.Prestable;
import src.interfaces.Renovable;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.List;

public class Libro extends RecursoDigital implements Prestable, Renovable {
    private String genero;
    private String saga;
    private Usuario usuarioPrestamo;
    private LocalDateTime fechaDevolucion;

    public Libro(String titulo, String autor, String genero, String saga) {
        super(titulo, autor);
        this.genero = genero;
        this.saga = saga;
    }

    //getters y setters
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

    //creador
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

    //categoria
    @Override
    public List<CategoriaRecurso> getCategoria() {
        return List.of(
                CategoriaRecurso.LIBRO,
                CategoriaRecurso.PRESTABLE,
                CategoriaRecurso.RENOVABLE
        );
    }

    //prestamos
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
            System.out.println("El libro fue prestado");
        }else {
            System.out.println("El libro no esta disponible");
        }
    }
    public boolean devolver() {
        if (estado == EstadoRecurso.PRESTADO) {
            usuarioPrestamo = null;
            fechaDevolucion = null;
            estado = EstadoRecurso.DISPONIBLE;
            System.out.println("El libro fue devuelto");
            return true;
        }
        return false;
    }
    @Override
    public boolean renovar() {
        if (estado == EstadoRecurso.PRESTADO) {
            fechaDevolucion = LocalDateTime.now().plusDays(7);
            System.out.println("Fecha de devolucion renovada a:" + fechaDevolucion);
            return true;
        }
        return false;
    }

    //string
    @Override
    public String toString() {
        return super.toString() +
                "\nGenero: " + genero +
                "\nSaga: " + saga;
    }
}
