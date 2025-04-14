package gestores;

import modelos.RecursoDigital;
import modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class GestorRecursos {
    private List<RecursoDigital> recursoDigital;

    public GestorRecursos(List<RecursoDigital> recursoDigital){
        this.recursoDigital = recursoDigital;
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
