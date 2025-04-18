package src.gestores;

import src.enums.EstadoRecurso;
import src.excepciones.RecursoNoDisponibleException;
import src.modelos.Prestamos;
import src.modelos.RecursoDigital;
import src.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorPrestamos {
    private List<Prestamos> prestamos = new ArrayList<>();

    public GestorPrestamos(List<Prestamos> prestamos) {
        this.prestamos = prestamos;
    }
    public void prestarRecurso(Usuario usuario, RecursoDigital recurso) throws RecursoNoDisponibleException {
        if (recurso.getEstado() == EstadoRecurso.DISPONIBLE) {
            Prestamos prestamo = new Prestamos(usuario, recurso);
            prestamos.add(prestamo);
            recurso.setEstado(EstadoRecurso.PRESTADO);
            System.out.println("Prestamo realizado");
            System.out.println(prestamo);
        } else {
            throw new RecursoNoDisponibleException("El recurso no esta disponible para ser prestado");
        }
    }
    public void devolverRecurso(RecursoDigital recurso) {
        for (Prestamos prestamo : prestamos) {
            if (prestamo.getRecurso().equals(recurso)) {
                prestamos.remove(prestamo);
                recurso.setEstado(EstadoRecurso.DISPONIBLE);
                System.out.println("El recurso " + recurso.getTitulo() + " ha sido devuelto con exito");
                return;
            }
        }
        System.out.println("Este recurso no esta prestado");
    }

    public void prestamosUsuario (Usuario usuario) {
        prestamos.stream()
                .filter(prestamo -> prestamo.getUsuario().equals(usuario))
                .forEach(System.out::println);
    }
}
