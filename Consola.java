import modelos.*;
import gestores.GestorUsuarios;
import gestores.GestorRecursos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consola {
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;

    public Consola() {
        List<Usuario> usuarios = new ArrayList<>();
        List<RecursoDigital> recursos = new ArrayList<>();
        this.gestorUsuarios = new GestorUsuarios(usuarios);
        this.gestorRecursos = new GestorRecursos(recursos);
    }

    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("----Menú Principal----");
            System.out.println("1. Gestión de usuarios");
            System.out.println("2. Gestión de recursos");
            System.out.println("3. Salir");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    menuGestionUsuario();
                    break;
                case 2:
                    menuGestionRecursos();
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    private void menuGestionUsuario() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("---Menu De Gestion de Usuarios---");
            System.out.println("1. Crear Usuario");
            System.out.println("2. Eliminar Usuario");
            System.out.println("3. Buscar Usuario por ID");
            System.out.println("4. Volver al Menu Principal");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    gestorUsuarios.registrarUsuario();
                    break;
                case 2:
                    gestorUsuarios.eliminarUsuario();
                    break;
                case 3:
                    gestorUsuarios.buscarUsuario();
                    break;
                case 4:
                    continuar = false;
                    break;
            }
        }
    }
    private void menuGestionRecursos() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("---Menu De Gestion de Recursos---");
            System.out.println("1. Crear Recurso");
            System.out.println("2. Eliminar Recurso");
            System.out.println("3. Buscar Recurso (Por Titulo o Autor)");
            System.out.println("4. Volver al Menu Principal");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    menuCrearRecurso();
                    break;
                case 2:
                    gestorRecursos.eliminarRecurso();
                    break;
                case 3:
                    gestorRecursos.buscarRecursos();
                    break;
                case 3:
                    continuar = false;
            }
        }
    }

    public void menuCrearRecurso() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("---Menu Crear Recurso---");
            System.out.println("1. Crear Libro");
            System.out.println("2. Crear Revista");
            System.out.println("3. Crear Audiolibro");
            System.out.println("4.Volver al menu de gestion de recursos");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    Libro libro = Libro.crearLibro();
                    gestorRecursos.crearRecurso(libro);
                    break;
                case 2:
                    Revista revista = Revista.crearRevista();
                    gestorRecursos.crearRecurso(revista);
                    break;
                case 3:
                    Audiolibro audiolibro = Audiolibro.crearAudiolibro();
                    gestorRecursos.crearRecurso(audiolibro);
                    break;
            }
        }
    }
}