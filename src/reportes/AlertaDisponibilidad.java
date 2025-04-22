package src.reportes;

import src.enums.EstadoRecurso;
import src.gestores.GestorPrestamos;
import src.gestores.GestorRecursos;
import src.gestores.GestorReservas;
import src.modelos.RecursoDigital;
import src.modelos.Reserva;
import src.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlertaDisponibilidad {
    private List<RecursoDigital> recursos;
    private GestorPrestamos gestorPrestamos;

    public AlertaDisponibilidad(List<RecursoDigital> recursos, GestorPrestamos gestorPrestamos) {
        this.recursos = recursos;
        this.gestorPrestamos = gestorPrestamos;
    }


    public void mostrarDisponibilidad() {
        Scanner sc = new Scanner(System.in);
        System.out.println("hola");
        for (RecursoDigital recurso : recursos) {
            System.out.println("chau");
            if (recurso.getEstado() == EstadoRecurso.RESERVADO) {
                Usuario siguiente = recurso.getReservas().peek().getUsuario();
                System.out.println("\n ALERTA DE DISPONIBILIDAD");
                System.out.println("El recurso " + recurso.getTitulo() + " esta disponible. \n" +
                        "Para el usuario:" + siguiente.getNombre());
                System.out.println("¿Desea pedir el prestamo ahora(s/n?");
                String respuesta = sc.nextLine().trim().toLowerCase();

                if (respuesta.equals("s")) {
                    gestorPrestamos.prestarRecurso(siguiente, recurso);
                }

            }
        }
    }
}
