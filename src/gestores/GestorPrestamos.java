package src.gestores;

import src.enums.EstadoRecurso;
import src.excepciones.RecursoNoDisponibleException;
import src.interfaces.Prestable;
import src.interfaces.Renovable;
import src.modelos.Prestamos;
import src.modelos.RecursoDigital;
import src.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.time.LocalDate;

public class GestorPrestamos {
    private List<Prestamos> prestamos = new ArrayList<>();

    public GestorPrestamos(List<Prestamos> prestamos) {
        this.prestamos = prestamos;
    }
    public void prestarRecurso(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (recurso instanceof Prestable) {
            Prestable prestable = (Prestable) recurso;
            if (prestable.estaDisponible()) {
                prestable.prestar(usuario);
                prestamos.add(new Prestamos(usuario, recurso, prestable.getFechaDevolucion()));
            } else {
                throw new RecursoNoDisponibleException("El recurso no esta disponible");
            }
        } else {
            throw new RecursoNoDisponibleException("El recurso no se puede prestar");
        }
    }

    public void devolverRecurso(RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (recurso instanceof Prestable) {
            Prestable prestable = (Prestable) recurso;
            if (prestable.devolver()) {
                prestamos.removeIf(p -> p.getRecurso() == recurso);
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
