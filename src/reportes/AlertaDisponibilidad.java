package src.reportes;

import src.enums.EstadoRecurso;
import src.gestores.GestorPrestamos;
import src.modelos.RecursoDigital;
import src.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlertaDisponibilidad{
    private List<RecursoDigital> recursos;
    private GestorPrestamos gestorPrestamos;

    public AlertaDisponibilidad(List<RecursoDigital> recursos, GestorPrestamos gestorPrestamos) {
        this.recursos = recursos;
        this.gestorPrestamos = gestorPrestamos;
    }


    public List<String> mostrarDisponibilidad() {
        List<String> mensajeAlertas = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        for (RecursoDigital recurso : recursos) {
            if (recurso.getEstado() == EstadoRecurso.RESERVADO) {
                Usuario siguiente = recurso.getReservas().peek().getUsuario();

                String mensaje = "\n ALERTA DE DISPONIBILIDAD \n" +
                        "El recurso " + recurso.getTitulo() + " esta disponible. \n" +
                        "Para el usuario:" + siguiente.getNombre();
                mensajeAlertas.add(mensaje);
                System.out.println("¿Desea pedir el prestamo ahora(s/n?");
                String respuesta = sc.nextLine().trim().toLowerCase();
                if (respuesta.equals("s")) {
                    gestorPrestamos.prestarRecurso(siguiente, recurso);
                }
            }
        }
        return mensajeAlertas;
    }
}
