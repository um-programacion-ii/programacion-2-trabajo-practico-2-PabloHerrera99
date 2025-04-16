package gestores;

import modelos.Audiolibro;
import modelos.Libro;
import modelos.RecursoDigital;
import modelos.Revista;

import java.util.List;
import java.util.Scanner;

public class GestorRecursos {
    private List<RecursoDigital> recursoDigital;

    public GestorRecursos(List<RecursoDigital> recursoDigital){
        this.recursoDigital = recursoDigital;
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
}
