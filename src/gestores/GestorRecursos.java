package src.gestores;

import src.enums.CategoriaRecurso;
import src.excepciones.RecursoNoDisponibleException;
import src.interfaces.Prestable;
import src.interfaces.Renovable;
import src.interfaces.ServicioNotificaciones;
import src.modelos.RecursoDigital;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class GestorRecursos {
    private List<RecursoDigital> recursoDigital;
    private ServicioNotificaciones notificacion;

    public GestorRecursos(List<RecursoDigital> recursoDigital, ServicioNotificaciones notificacion) {
        this.recursoDigital = recursoDigital;
        this.notificacion = notificacion;
    }

    public void crearRecurso(RecursoDigital recurso) throws RecursoNoDisponibleException {
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

    public void prestarRecurso () throws RecursoNoDisponibleException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el titulo del recurso que quiere prestar:");
        String titulo = sc.nextLine();

        for (RecursoDigital recurso : recursoDigital) {
            if (recurso.getTitulo().equals(titulo) && recurso instanceof Prestable && !recurso.getPrestado()) {
                recurso.prestar();
                notificacion.enviarNotificacion("Recurso prestado:"+
                                                recurso +
                                                "\n-----------------------\n" +
                                                "Fecha de entrega:" + recurso.getFechaEntrega());
            } else if (recurso.getPrestado()) {
                throw new RecursoNoDisponibleException("El recurso " + titulo + " ya se encuentra prestado");
            }
        }
    }
    public void renovarRecurso() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el titulo del recurso a renovar:");
        String titulo = sc.nextLine();
        for (RecursoDigital recurso : recursoDigital) {
            if (recurso.getTitulo().equals(titulo) && recurso instanceof Renovable) {
                ((Renovable) recurso).renovar();
                notificacion.enviarNotificacion("Recurso renovado:"+
                                                recurso +
                                                "\n-----------------------\n" +
                                                "Fecha de entrega nueva:" + recurso.getFechaEntrega());;
            }
        }
    }
    public void devolverRecurso() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el titulo del recurso a devolver:");
        String titulo = sc.nextLine();
        for (RecursoDigital recurso : recursoDigital) {
            if (recurso.getTitulo().equals(titulo) && recurso.getPrestado()) {
                recurso.devolver();
                notificacion.enviarNotificacion("El recurso " + recurso.getTitulo() + " ha sido devuelto");
                break;


            }
        }
    }
    public void listaPrestables() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Recursos prestables Disponibles:");
       for (RecursoDigital recurso : recursoDigital) {
           if (!recurso.getPrestado()) {
               System.out.println(recurso +
                       "\n-------------\n");
           }
       }
       System.out.println("ReCursos prestables no Disponibles:");
       for (RecursoDigital recurso : recursoDigital) {
           if (recurso.getPrestado()) {
               System.out.println(recurso +
                       "\n-------------\n");
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
                    .filter(r -> r.getTipo().contains(categoria))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo no valido");
        }
        return null;
    }
}
