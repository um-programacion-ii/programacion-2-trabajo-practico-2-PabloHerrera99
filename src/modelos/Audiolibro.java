package src.modelos;

import src.enums.CategoriaRecurso;

import java.util.List;
import java.util.Scanner;

public class Audiolibro extends RecursoDigital {
    private String idioma;
    private int duracionMinutos;

    public Audiolibro(String titulo, String autor, String idioma, int duracionMinutos) {
        super(titulo, autor);
        this.idioma = idioma;
        this.duracionMinutos = duracionMinutos;
    }

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

    @Override
    public List<CategoriaRecurso> getCategoria() {
        return List.of(
                CategoriaRecurso.AUDIOLIBRO,
                CategoriaRecurso.PRESTABLE
        );
    }
    @Override
    public String toString() {
        return super.toString() +
                "\nIdioma: " + idioma +
                "\nDuracion minutos: " + duracionMinutos;
    }
}
