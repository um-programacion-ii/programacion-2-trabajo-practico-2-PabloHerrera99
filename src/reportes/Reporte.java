package src.reportes;

import src.modelos.*;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Reporte {
    private final List<RecursoDigital> recursos;
    private final Map<Integer, Usuario> usuarios;
    private final ExecutorService executor;

    public Reporte (List<RecursoDigital> recursos, Map<Integer, Usuario> usuarios) {
        this.recursos = recursos;
        this.usuarios = usuarios;
        this.executor = Executors.newSingleThreadExecutor();
    }

    public void generarReporteAsync(String tipo) {
        Future<?> future = executor.submit(() -> {
            System.out.println("Generando reporte..." + tipo + "...");
            try {
                Thread.sleep(1000);
                switch (tipo.toLowerCase()) {
                    case "prestamos": reportePrestamos(); break;
                    case "usuarios": reporteUsuarios(); break;
                    case "categorias": reporteCategorias(); break;
                    default: System.out.println("Tipo de reporte incorrecto");
                }
                System.out.println("Reporte finalizado");
            } catch (InterruptedException e) {
                System.out.println("Reporte Interrumpido");
                Thread.currentThread().interrupt();
            }
        });
    }



    public synchronized void reportePrestamos() {
        System.out.println("\nReporte de Prestamos");
        recursos.stream()
                .sorted(Comparator.comparingInt(RecursoDigital::getContadorPrestamos).reversed())
                .limit(5)
                .forEach(r -> System.out.println(" - " + r.getTitulo() + ": " + r.getContadorPrestamos()));
    }
    public synchronized void reporteUsuarios() {
        System.out.println("\nReporte de Usuarios");
        usuarios.values().stream()
                .sorted(Comparator.comparingInt(Usuario::getContadorActividad).reversed())
                .limit(5)
                .forEach(u -> System.out.println(" - " + u.getNombre() + ": " + u.getContadorActividad()));
    }
    public synchronized void reporteCategorias() {
        System.out.println("\nReporte de Categorias");
        Map<String, Integer> cantidadCategorias = new HashMap<>();
        for (RecursoDigital r : recursos) {
            String categoria = null;
            if (r instanceof Libro) {
                categoria = "Libro";
            } else if (r instanceof Revista) {
                categoria = "Revista";
            } else if (r instanceof Audiolibro) {
                categoria = "Audiolibro";
            }
            int cantidad = r.getContadorPrestamos();
            cantidadCategorias.put(categoria, cantidadCategorias.getOrDefault(categoria,0) + cantidad);
        }
        cantidadCategorias.forEach((categoria,total) ->
                System.out.println(" - " + categoria + ": " + total));
    }
    public void shutdown() {
        executor.shutdown();
    }
}
