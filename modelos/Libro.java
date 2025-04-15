package modelos;


import java.util.Scanner;

public class Libro extends RecursoDigital {
    private String genero;
    private String saga;

    public Libro(String titulo, String autor, String genero, String saga) {
        super(titulo, autor);
        this.genero = genero;
        this.saga = saga;
    }

    public String getGenero() {
        return genero;
    }
    public String getSaga() {
        return saga;
    }

    public void setGenero(String genero) {
        if (genero == null || genero.isEmpty()) {
            throw new IllegalArgumentException("El libro tiene que tener un genero");
        }
        this.genero = genero;
    }
    public void setSaga(String saga) {
        this.saga = saga;
    }
    @Override
    public String toString() {
        return super.toString() +
                "\nGenero: " + genero +
                "\nSaga: " + saga;
    }
    public static Libro crearLibro() {
        Scanner sc = new Scanner(System.in);
        Object[] datos = Libro.datosBasicos();
        String titulo = (String) datos[0];
        String autor = (String) datos[1];

        System.out.println("Introduzca el genero: ");
        String genero = sc.nextLine();
        System.out.println("Introduzca el saga: ");
        String saga = sc.nextLine();
        return new Libro(titulo,
                        autor,
                        genero,
                        saga);
    }
}
