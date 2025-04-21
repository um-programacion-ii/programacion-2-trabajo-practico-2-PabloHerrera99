package src.reportes;

import src.interfaces.Renovable;
import src.modelos.Prestamos;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

public class AlertaVencimiento implements Runnable {
    private List<Prestamos> prestamos;

    public AlertaVencimiento(List<Prestamos> prestamos) {
        this.prestamos = prestamos;
    }

    public void run() {
        for (Prestamos prestamo : prestamos) {
            LocalDateTime vencimiento = prestamo.getFechaDevolucion();
            long dias = ChronoUnit.DAYS.between(vencimiento, LocalDateTime.now());

            if (dias == 1) {
                String mensaje = "Falta 1 dia";
                alerta(prestamo, mensaje);
            } else if (dias == 0) {
                String mensaje = "Falta menos de 1 dia";
                alerta(prestamo, mensaje);
            }
        }
    }

    private void alerta(Prestamos prestamo, String mensaje) {
        Scanner sc = new Scanner(System.in);
        System.out.println("\n ALERTA DE VENCIMIENTO" + "(" + mensaje + ")\n");
        System.out.println(prestamo + "\n");

        // me rompe el programa porque escanner no esta pensado para funcionar en hilos y un scanner
        //se pisa con el otro (el de la consola y este)

        //if (prestamo.getRecurso() instanceof Renovable) {
        //    System.out.println("¿Desea renovar el prestamo(s/n)?");
        //    sc.nextLine();
        //    sc.close();
        //    String opcion = sc.nextLine();
        //    sc.nextLine();
        //    if (opcion.equals("s")) {
        //        ((Renovable) prestamo.getRecurso()).renovar();
        //    }
        //}
    }
}
