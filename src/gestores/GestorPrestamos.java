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
import java.util.Map;
import java.util.Scanner;

import java.time.LocalDate;
import java.util.stream.Collectors;

public class GestorPrestamos {
    private List<Prestamos> prestamos = new ArrayList<>();
    private GestorReservas gestorReservas;
    private GestorNotificaciones notificaciones;

    public GestorPrestamos(List<Prestamos> prestamos) {
        this.prestamos = prestamos;
        this.gestorReservas = new GestorReservas();
        this.notificaciones = new GestorNotificaciones();
    }
    public List<Prestamos> getPrestamos() {
        return prestamos;
    }
    public void addPrestamos(Prestamos prestamo) {
        prestamo.getRecurso().setEstado(EstadoRecurso.PRESTADO);
        prestamos.add(prestamo);
    }

    public void prestarRecurso(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        synchronized (recurso) {
            if (recurso instanceof Prestable) {
                Prestable prestable = (Prestable) recurso;
                if (recurso.getEstado().equals(EstadoRecurso.RESERVADO)) {
                    // prestamo con reserva
                    Reserva siguiente = recurso.getReservas().peek();
                    if (!siguiente.getUsuario().equals(usuario)) {
                        throw new RecursoNoDisponibleException("El recurso esta reservado");
                    } else {
                        gestorReservas.procesarReserva(recurso);
                        recurso.setEstado(EstadoRecurso.DISPONIBLE);
                        prestable.prestar(usuario);
                        Prestamos prestamo = new Prestamos(usuario, recurso, prestable.getFechaDevolucion());
                        prestamos.add(prestamo);
                        System.out.println("\nDatos del prestamo: \n" + prestamo);
                        String mensaje = "\nDatos del prestamo: \n" + prestamo;
                        notificaciones.enviarNotificacion(usuario, mensaje);
                        recurso.aumentarContadorPrestamos();
                        usuario.aumentarContadorActividad();
                    }
                } else {
                    // prestamo sin reserva
                    gestorReservas.procesarReserva(recurso);
                    prestable.prestar(usuario);
                    Prestamos prestamo = new Prestamos(usuario, recurso, prestable.getFechaDevolucion());
                    prestamos.add(prestamo);
                    System.out.println("\nDatos del prestamo: \n" + prestamo);
                    String mensaje = "\nDatos del prestamo: \n" + prestamo;
                    notificaciones.enviarNotificacion(usuario, mensaje);
                    recurso.aumentarContadorPrestamos();
                    usuario.aumentarContadorActividad();
                }
            }
            System.out.println("\n[HILO: " + Thread.currentThread().getName() + "]");
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

    public void prestamosUsuario (Usuario usuario) {
        prestamos.stream()
                .filter(prestamo -> prestamo.getUsuario().equals(usuario))
                .forEach(System.out::println);
    }
}