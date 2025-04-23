package src.modelos;

import src.enums.CategoriaRecurso;
import src.enums.EstadoRecurso;
import src.interfaces.Prestable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Audiolibro extends RecursoDigital implements Prestable {
    private String idioma;
    private int duracionMinutos;
    private Usuario usuarioPrestamo;
    private LocalDateTime fechaDevolucion;

    public Audiolibro(String titulo, String autor, String idioma, int duracionMinutos) {
        super(titulo, autor);
        this.idioma = idioma;
        this.duracionMinutos = duracionMinutos;
    }

    //getters y setters
    public void setIdioma(String idioma) {
        if (idioma == null || idioma.isEmpty()) {
            throw new IllegalArgumentException("El audiolibro tiene que tener un idioma");
        }
        this.idioma = idioma;
    }
    public String getIdioma() {
        return idioma;
    }

    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos < 0) {
            throw new IllegalArgumentException("El audiolibro tiene que tener una duración mayor que 0");
        }
        this.duracionMinutos = duracionMinutos;
    }
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    //credor
    public static Audiolibro crearAudiolibro() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el titulo: ");
        String titulo = sc.nextLine();
        System.out.println("Ingrese el autor: ");
        String autor = sc.nextLine();
        System.out.println("Introduzca el idioma: ");
        String idioma = sc.nextLine();
        System.out.println("Introduzca el duración en minutos: ");
        int duracionMinutos = sc.nextInt();
        return new Audiolibro(titulo,
                autor,
                idioma,
                duracionMinutos);
    }


    //categoria
    @Override
    public List<CategoriaRecurso> getCategoria() {
        return List.of(
                CategoriaRecurso.AUDIOLIBRO,
                CategoriaRecurso.PRESTABLE
        );
    }

    //Prestar
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
            System.out.println("El Audiolibro fue prestado");
        }else {
            System.out.println("El Audiolibro no esta disponible");
        }
    }
    public boolean devolver() {
        if (estado == EstadoRecurso.PRESTADO) {
            usuarioPrestamo = null;
            fechaDevolucion = null;
            estado = EstadoRecurso.DISPONIBLE;
            System.out.println("El Audiolibro fue devuelto");
            return true;
        }
        return false;
    }

    //String
    @Override
    public String toString() {
        return super.toString() +
                "\nIdioma: " + idioma +
                "\nDuracion minutos: " + duracionMinutos;
    }
}
