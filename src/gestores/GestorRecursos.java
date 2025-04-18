package src.gestores;

import src.enums.TipoRecurso;
import src.interfaces.Prestable;
import src.interfaces.Renovable;
import src.interfaces.ServicioNotificaciones;
import src.modelos.Audiolibro;
import src.modelos.Libro;
import src.modelos.RecursoDigital;
import src.modelos.Revista;

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

    public void crearRecurso(RecursoDigital recurso){
        recursoDigital.add(recurso);
    }
    public void eliminarRecurso(){
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
            System.out.println("El recurso no existe");
        }
    }
    
    public void listarRecursos() {
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
            System.out.println("No existen recursos que coincidan con esos filtros");
        }
        for (RecursoDigital recurso : filtrados) {
            System.out.println(recurso +
                    "\n------------------------\n");
        }
    }
    public void buscarRecursos(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el titulo del recurso que desea buscar:");
        String titulo = sc.nextLine();
        List<RecursoDigital> filtrados = recursoDigital.stream()
                .filter(r -> r.getTitulo().equalsIgnoreCase(titulo))
                .collect(Collectors.toList());
        if (filtrados.isEmpty()) {
            System.out.println("El recurso no existe");
        }else {
            System.out.println("Recursos coincidentes:");
            for (RecursoDigital recurso : filtrados) {
                System.out.println(recurso +
                        "\n------------------------\n");
            }
        }
    }

    public void prestarRecurso () {
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
                System.out.println("El recurso ya esta prestado");
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
        String tipoString = sc.nextLine().toUpperCase();
        if (tipoString.equals("TODOS")) {
            return lista;
        }
        try {
            TipoRecurso tipo = TipoRecurso.valueOf(tipoString);
            return lista.stream()
                    .filter(r -> r.getTipo().contains(tipo))
                    .collect(Collectors.toList());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo no valido");
        }
        return null;
    }
}
