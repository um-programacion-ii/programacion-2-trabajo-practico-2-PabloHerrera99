package src.gestores;

import src.enums.CategoriaRecurso;
import src.excepciones.RecursoNoDisponibleException;
import src.modelos.RecursoDigital;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GestorRecursos {
    private List<RecursoDigital> recursoDigital;


    public GestorRecursos(List<RecursoDigital> recursoDigital) {
        this.recursoDigital = recursoDigital;
    }

    public List<RecursoDigital> getRecursoDigital() {
        return recursoDigital;
    }

    public void crearRecurso(RecursoDigital recurso) {
            recursoDigital.add(recurso);
    }
    public void eliminarRecurso() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el titulo del recurso que desea eliminar");
        boolean eliminado = false;
        String titulo = sc.nextLine();
        for (RecursoDigital recurso : recursoDigital) {
            if (recurso.getTitulo().equals(titulo)) {
                recursoDigital.remove(recurso);
                System.out.println("El recurso eliminado");
                eliminado = true;
                break;
            }
        }
        if (!eliminado){
            throw new RecursoNoDisponibleException("El recurso llamado " + titulo + " no exite");
        }
    }
    
    public void listarRecursos() throws RecursoNoDisponibleException {
        Scanner sc = new Scanner(System.in);
        if (recursoDigital.isEmpty()) {
            System.out.println("No existen recursos");
            return;
        }
        List<RecursoDigital> filtrados = buscarFiltro(recursoDigital);
        boolean continuar = true;
        while (continuar) {
            if (filtrados.equals(recursoDigital)) {
                continuar = false;
            } else {
                System.out.println("Desea agregar otro filtro(s/n)?");
                String opcion = sc.nextLine();
                if (opcion.equals("s")) {
                    filtrados = buscarFiltro(recursoDigital);
                } else {
                    continuar = false;
                }
            }
        }
        if (filtrados.isEmpty()) {
            throw new RecursoNoDisponibleException("No existe recurso que cumpla cpn esos parametros");
        }
        for (RecursoDigital recurso : filtrados) {
            System.out.println(recurso +
                    "\n------------------------\n");
        }
    }
    public void buscarRecursos() throws RecursoNoDisponibleException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el titulo del recurso que desea buscar:");
        String titulo = sc.nextLine();
        List<RecursoDigital> filtrados = recursoDigital.stream()
                .filter(r -> r.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
        if (filtrados.isEmpty()) {
            throw new RecursoNoDisponibleException("El recurso llamado " + titulo + " no exite");
        }else {
            System.out.println("Recursos coincidentes:");
            for (RecursoDigital recurso : filtrados) {
                System.out.println(recurso +
                        "\n------------------------\n");
            }
        }
    }

    public List<RecursoDigital> buscarFiltro(List<RecursoDigital> lista){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el filtro que desea buscar(libro, revista, audiolibro, prestable o renovable).\n" +
                "Si desea listar todos los recursos escriba (todos)");
        String categoriaString = sc.nextLine().toUpperCase();
        if (categoriaString.equals("TODOS")) {
            return lista;
        }
        try {
            CategoriaRecurso categoria = CategoriaRecurso.valueOf(categoriaString);
            return lista.stream()
                    .filter(r -> r.getCategoria().contains(categoria))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo no valido");
        }
        return null;
    }

    public RecursoDigital buscarPrestamo(String titulo) throws RecursoNoDisponibleException {
        for (RecursoDigital recurso : recursoDigital) {
            if (recurso.getTitulo().equalsIgnoreCase(titulo)) {
                return recurso;
            }
        }
        throw new RecursoNoDisponibleException("El recurso no existe");
    }

    public List<RecursoDigital> buscarReservas() {
        List<RecursoDigital> recursosReservas = new ArrayList<>();
        for (RecursoDigital recurso : recursoDigital) {
            if (recurso.getReservas() != null && !recurso.getReservas().isEmpty()) {
                recursosReservas.add(recurso);
            }
        }
        return recursosReservas;
    }

}
