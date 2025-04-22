package src.reportes;

import src.gestores.GestorPrestamos;
import src.interfaces.Renovable;
import src.modelos.Prestamos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AlertaVencimiento{
    private List<Prestamos> prestamos;
    private GestorPrestamos gestorPrestamos;

    public AlertaVencimiento(List<Prestamos> prestamos, GestorPrestamos gestorPrestamos) {
        this.prestamos = prestamos;
        this.gestorPrestamos =  gestorPrestamos;
    }

    public List<String> mostrarVencimiento() {
        List<String> mensajeAlerta =  new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        List<String> mensajes = new ArrayList<>();
        for (Prestamos prestamo : prestamos) {
            LocalDateTime vencimiento = prestamo.getFechaDevolucion();
            long dias = ChronoUnit.DAYS.between(LocalDateTime.now(), vencimiento);

            if (dias == 1 || dias == 0) {
                String dia = dias == 1 ? "Vence en 1 día" : "Vence hoy";
                String mensaje= "\nALERTA DE VENCIMIENTO:" + dia + prestamo;
                System.out.println(mensaje);
                mensajeAlerta.add(mensaje);

                if (prestamo.getRecurso() instanceof Renovable) {
                    System.out.println("¿Desea renovar este recurso? (s/n): ");
                    String respuesta = sc.nextLine().trim().toLowerCase();

                    if (respuesta.equals("s")) {
                        gestorPrestamos.renovarPrestamo(prestamo.getRecurso());
                    }
                }
            }
        }
        return mensajeAlerta;
    }
}