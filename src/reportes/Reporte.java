package src.reportes;

import src.modelos.Prestamos;
import src.modelos.RecursoDigital;
import src.modelos.Usuario;

import java.util.Comparator;
import java.util.List;

public class Reporte {
    //private List<Prestamos> prestamos;
    private List<RecursoDigital> recursos;
    //private List<Usuario> usuarios;

    public Reporte (List<RecursoDigital> recursos) {
        //this.prestamos = prestamos;
        this.recursos = recursos;
        //this.usuarios = usuarios;
    }

    public void reportePrestamos() {
        System.out.println("Reporte de Prestamos");
        recursos.stream()
                .sorted(Comparator.comparingInt(RecursoDigital::getContadorPrestamos).reversed())
                .limit(5)
                .forEach(r -> System.out.println(" - " + r.getTitulo() + ": " + r.getContadorPrestamos()));
    }
}
