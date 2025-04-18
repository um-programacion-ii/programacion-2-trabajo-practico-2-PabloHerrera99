package src.gestores;

import src.enums.EstadoRecurso;
import src.excepciones.RecursoNoDisponibleException;
import src.interfaces.Prestable;
import src.interfaces.Renovable;
import src.modelos.Prestamos;
import src.modelos.RecursoDigital;
import src.modelos.Reserva;
import src.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;

public class GestorPrestamos {
    private List<Prestamos> prestamos = new ArrayList<>();
    private GestorReservas gestorReservas;

    public GestorPrestamos(List<Prestamos> prestamos) {
        this.prestamos = prestamos;
        this.gestorReservas = new GestorReservas();
    }
    public void prestarRecurso(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (recurso instanceof Prestable) {
            Prestable prestable = (Prestable) recurso;
            if (recurso.getEstado().equals(EstadoRecurso.RESERVADO)) {
                Reserva siguiente = recurso.getReservas().peek();
                if (!siguiente.getUsuario().equals(usuario)) {
                    throw new RecursoNoDisponibleException("El recurso esta reservado");
                }else {
                    gestorReservas.procesarReserva(recurso);
                    recurso.setEstado(EstadoRecurso.DISPONIBLE);
                    prestable.prestar(usuario);
                    Prestamos prestamo = new Prestamos(usuario, recurso, prestable.getFechaDevolucion());
                    prestamos.add(prestamo);
                    System.out.println("\nDatos del prestamo: \n" + prestamo);
                    System.out.println("Reserva atendida.");
                }
            } else {
                gestorReservas.procesarReserva(recurso);
                prestable.prestar(usuario);
                Prestamos prestamo = new Prestamos(usuario, recurso, prestable.getFechaDevolucion());
                prestamos.add(prestamo);
                System.out.println("\nDatos del prestamo: \n" + prestamo);
            }
        }
    }

    public void devolverRecurso(RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (recurso instanceof Prestable) {
            Prestable prestable = (Prestable) recurso;
            if (prestable.devolver()) {
                prestamos.removeIf(p -> p.getRecurso() == recurso);
                if (!recurso.getReservas().isEmpty()) {
                    recurso.setEstado(EstadoRecurso.RESERVADO);
                }
            } else {
                throw new RecursoNoDisponibleException("El recurso no esta prestado");
            }
        }
    }

    public void prestamosUsuario (Usuario usuario) {
        prestamos.stream()
                .filter(prestamo -> prestamo.getUsuario().equals(usuario))
                .forEach(System.out::println);
    }

    public void renovarPrestamo(RecursoDigital recurso) {
        if (recurso instanceof Renovable) {
            Renovable renovable = (Renovable) recurso;
            if (!renovable.renovar()) {
                System.out.println("El recurso no se pudo renovar");
            }
        } else {
            System.out.println("El recurso no es renovable");
        }
    }
}
