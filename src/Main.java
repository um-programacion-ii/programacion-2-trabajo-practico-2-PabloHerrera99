package src;
import src.reportes.*;
import src.gestores.*;
import src.modelos.*;

import java.util.*;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Usuario> usuarios = new HashMap<>();
        List<RecursoDigital> recursos = new ArrayList<>();
        List<Prestamos> prestamos = new ArrayList<>();

        GestorUsuarios gestorUsuarios = new GestorUsuarios(usuarios);
        GestorRecursos gestorRecursos = new GestorRecursos(recursos);
        GestorPrestamos gestorPrestamos = new GestorPrestamos(prestamos);
        GestorReservas gestorReservas = new GestorReservas();
        Reporte reporte = new Reporte(recursos, usuarios);
        AlertaDisponibilidad disponibilidad = new AlertaDisponibilidad(recursos,gestorPrestamos);
        AlertaVencimiento vencimiento = new AlertaVencimiento(prestamos,gestorPrestamos);
        GestorAlertas gestorAlertas = new GestorAlertas(disponibilidad,vencimiento);


        Consola consola = new Consola(gestorUsuarios,gestorRecursos,gestorPrestamos,
                gestorReservas,reporte,gestorAlertas);

        consola.menuPrincipal();
    }
}
