package src.interfaces;

import java.time.LocalDate;

public interface Prestable {
    void prestar();
    void devolver();
    LocalDate getFechaEntrega();
    boolean getPrestado();
}
