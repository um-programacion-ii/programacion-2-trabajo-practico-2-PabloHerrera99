package src.modelos;

import src.enums.CategoriaRecurso;
import src.enums.EstadoRecurso;
import src.interfaces.InterfazRD;
import src.interfaces.Prestable;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public abstract class RecursoDigital implements InterfazRD {
    protected String titulo;
    protected String autor;
    protected EstadoRecurso estado;
    protected PriorityBlockingQueue<Reserva> reservas;

    public RecursoDigital(String titulo, String autor) {
        setTitulo(titulo);
        setAutor(autor);
        this.estado = EstadoRecurso.DISPONIBLE;
        this.reservas = new PriorityBlockingQueue<>();
    }
    public abstract List<CategoriaRecurso> getCategoria();

    // getters y setters
    @Override
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.isEmpty()) {
            throw new IllegalArgumentException("Titulo no puede estar vacio");
        }
        this.titulo = titulo;
    }

    @Override
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        if (autor == null || autor.isEmpty()) {
            throw new IllegalArgumentException("Autor no puede estar vacio");
        }
        this.autor = autor;
    }

    public EstadoRecurso getEstado() {
        return estado;
    }
    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    public PriorityBlockingQueue<Reserva> getReservas() {
        return reservas;
    }
    public void setReservas(PriorityBlockingQueue<Reserva> reservas) {
        this.reservas = reservas;
    }

    //reservas
    public void agregarReserva(Reserva reserva) {
        reservas.offer(reserva);
        System.out.println("Reserva agregada: \n" + reserva);
    }

    public boolean eliminarReserva(Usuario usuario) {
        for (Reserva reserva : reservas) {
            if (reserva.getUsuario().equals(usuario)) {
                reservas.remove(reserva);
                System.out.println("Reserva eliminada: \n" + reserva);
                return true;
            }
        }
        System.out.println("Reserva no encontrada");
        return false;
    }

    public void mostrarReservas() {
        System.out.println("\n---Reservas para " + this.getTitulo() + "---\n");
        for (Reserva reserva : reservas) {
            System.out.println(reserva);
        }
    }
    public void siguienteReserva() {
        Reserva siguiente = reservas.poll();
        if (siguiente != null) {
            System.out.println("\nReserva siguiente:\n" + siguiente);
            this.estado = EstadoRecurso.RESERVADO;
        } else {
            System.out.println("No hay reserva pendiente para " + this.getTitulo());
        }
    }

    //String
    @Override
    public String toString() {
        return "Titulo: " + titulo +
                "\nAutor: " + autor;
    }

}
