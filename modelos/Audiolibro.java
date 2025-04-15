package modelos;

public class Audiolibro extends RecursoDigital {
    private String idioma;
    private int duracionMinutos;

    public Audiolibro(String titulo, String autor, String idioma, int duracionMinutos) {
        super(titulo, autor);
        this.idioma = idioma;
        this.duracionMinutos = duracionMinutos;
    }

    public String getIdioma() {
        return idioma;
    }
    public int getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setIdioma(String idioma) {
        if (idioma == null || idioma.isEmpty()) {
            throw new IllegalArgumentException("El audiolibro tiene que tener un idioma");
        }
        this.idioma = idioma;
    }
    public void setDuracionMinutos(int duracionMinutos) {
        if (duracionMinutos < 0) {
            throw new IllegalArgumentException("El audiolibro tiene que tener una duración mayor que 0");
        }
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n Idioma: " + idioma +
                "\n Duracion minutos: " + duracionMinutos;
    }
}
