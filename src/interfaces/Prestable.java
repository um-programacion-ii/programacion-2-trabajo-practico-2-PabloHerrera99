package src.interfaces;

import src.modelos.RecursoDigital;
import src.modelos.Usuario;

import java.time.LocalDateTime;

public interface Prestable {
    boolean estaDisponible();
    LocalDateTime getFechaDevolucion();
    void prestar(Usuario usuario);
    boolean devolver();
}
