package src;

import src.Servicios.ServicioNotificacionesEmail;
import src.interfaces.ServicioNotificaciones;
import src.modelos.*;
import src.gestores.GestorUsuarios;
import src.gestores.GestorRecursos;

import java.util.*;

public class Consola {
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;

    public Consola() {
        Map<Integer, Usuario> usuarios = new HashMap<>();
        List<RecursoDigital> recursos = new ArrayList<>();
        ServicioNotificaciones notificacion = new ServicioNotificacionesEmail();
        this.gestorUsuarios = new GestorUsuarios(usuarios, notificacion);
        this.gestorRecursos = new GestorRecursos(recursos, notificacion);
    }

    public void menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("---- Menú Principal ---- \n" +
                    "1. Gestión de usuarios \n" +
                    "2. Gestión de recursos \n" +
                    "3. Salir \n" +
                    "Elija una opción: ");
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
            System.out.println("--- Menu De Gestión de Usuarios --- \n" +
                    "1. Crear Usuario \n" +
                    "2. Eliminar Usuario \n" +
                    "3. Listar Usuarios \n" +
                    "4. Buscar Usuario por ID \n" +
                    "5. Volver al Menu Principal \n" +
                    "Elija una opción: ");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    gestorUsuarios.registrarUsuario();
                    break;
                case 2:
                    gestorUsuarios.eliminarUsuario();
                    break;
                case 3:
                    gestorUsuarios.listarUsuarios();
                    break;
                case 4:
                    gestorUsuarios.buscarUsuario();
                    break;
                case 5:
                    continuar = false;
                    break;
            }
        }
    }

    private void menuGestionRecursos() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("--- Menu De Gestion de Recursos --- \n" +
                    "1. Crear Recurso \n" +
                    "2. Eliminar Recurso \n" +
                    "3. Listar Recursos \n" +
                    "4. Buscar Recurso (Por Titulo o Autor) \n" +
                    "5. Gestionar prestamos de recursos \n" +
                    "6. Volver al Menu Principal \n" +
                    "Elija una opción:");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    menuCrearRecurso();
                    break;
                case 2:
                    gestorRecursos.eliminarRecurso();
                    break;
                case 3:
                    gestorRecursos.listarRecursos();
                    break;
                case 4:
                    gestorRecursos.buscarRecursos();
                    break;
                case 5:
                    menuPrestamos();
                    break;
                case 6:
                    continuar = false;
                    break;
            }
        }
    }
    public void menuCrearRecurso() {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("--- Menu Crear Recurso --- \n" +
                    "1. Crear Libro \n" +
                    "2. Crear Revista \n" +
                    "3. Crear Audiolibro \n" +
                    "4.Volver al menu de gestión de recursos \n" +
                    "Elija una opción: ");
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
                case 4:
                    continuar = false;
                    break;
            }
        }
    }
    public void menuPrestamos () {
        Scanner sc = new Scanner(System.in);
        boolean continuar = true;
        while (continuar) {
            System.out.println("---Menu Prestamos---\n" +
                    "1. Pedir prestado un recurso \n" +
                    "2. Renovar recurso \n" +
                    "3. Devolver recurso \n" +
                    "4. Listar recursos prestables \n" +
                    "5. Volver al Menu Principal \n" +
                    "Elija una opción:");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    gestorRecursos.prestarRecurso();
                    break;
                case 2:
                    gestorRecursos.renovarRecurso();
                    break;
                case 3:
                    gestorRecursos.devolverRecurso();
                    break;
                case 4:
                    gestorRecursos.listaPrestables();
                case 5:
                    continuar = false;
                    break;

            }
        }
    }
}