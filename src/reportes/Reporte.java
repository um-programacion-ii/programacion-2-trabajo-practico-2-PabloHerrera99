package src.reportes;

import src.modelos.Prestamos;
import src.modelos.RecursoDigital;
import src.modelos.Usuario;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Reporte {
    //private List<Prestamos> prestamos;
    private List<RecursoDigital> recursos;
    private Map<Integer, Usuario> usuarios;

    public Reporte (List<RecursoDigital> recursos, Map<Integer, Usuario> usuarios) {
        //this.prestamos = prestamos;
        this.recursos = recursos;
        this.usuarios = usuarios;
    }

    public void reportePrestamos() {
        System.out.println("\nReporte de Prestamos");
        recursos.stream()
                .sorted(Comparator.comparingInt(RecursoDigital::getContadorPrestamos).reversed())
                .limit(5)
                .forEach(r -> System.out.println(" - " + r.getTitulo() + ": " + r.getContadorPrestamos()));
    }

    public void reporteUsuarios() {
        System.out.println("\nReporte de Usuarios");
        usuarios.values().stream()
                .sorted(Comparator.comparingInt(Usuario::getContadorActividad).reversed())
                .limit(5)
                .forEach(u -> System.out.println(" - " + u.getNombre() + ": " + u.getContadorActividad()));
    }
}
