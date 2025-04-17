package src.gestores;

import src.interfaces.Prestable;
import src.interfaces.Renovable;
import src.interfaces.ServicioNotificaciones;
import src.modelos.Audiolibro;
import src.modelos.Libro;
import src.modelos.RecursoDigital;
import src.modelos.Revista;

import java.util.List;
import java.util.Scanner;

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
        System.out.println("Inserte que tipos de recursos desea listar (libros, revistas o audiolibros)\n" +
                "deje la opción el blanco para listar todos los recursos:");
        String tipo = sc.nextLine();

        for (RecursoDigital recurso : recursoDigital) {
            if (tipo.equals("libros") && recurso instanceof Libro) {
                System.out.println(recurso);
            } else if (tipo.equals("revistas") && recurso instanceof Revista) {
                System.out.println(recurso);
            } else if (tipo.equals("audiolibros") && recurso instanceof Audiolibro) {
                System.out.println(recurso);
            } else if (tipo.isEmpty()) {
                System.out.println(recurso);
            }
            System.out.println("\n-----------------------------\n");
        }
    }

    public void buscarRecursos(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el parametro de busqueda (Titulo o Autor):");
        String parametro = sc.nextLine();
        boolean encontrado = false;
        for(RecursoDigital recurso : recursoDigital){
            if(recurso.getTitulo().equalsIgnoreCase(parametro)){
                System.out.println(recurso);
                encontrado = true;
                break;
            }
            if (recurso.getAutor().equalsIgnoreCase(parametro)){
                System.out.println(recurso);
                encontrado = true;
                break;
            }
        }
        if (!encontrado){
            System.out.println("El recurso no existe");
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
            //    if (recurso.getFechaEntrega() ) {
            //        notificacion.enviarNotificacion("El recurso " + recurso.getTitulo() + " ha sido devuelto a tiempo");
            //    }else {
            //        notificacion.enviarNotificacion("El recurso " + recurso.getTitulo() + " no ha sido devuelto con atrasado");
            //    }

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
}
