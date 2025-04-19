package src;

import src.Servicios.ServicioNotificacionesEmail;
import src.gestores.GestorPrestamos;
import src.interfaces.ServicioNotificaciones;
import src.modelos.*;
import src.gestores.GestorUsuarios;
import src.gestores.GestorRecursos;

import java.util.*;

public class Consola {
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private GestorPrestamos gestorPrestamos;

    public Consola() {
        Map<Integer, Usuario> usuarios = new HashMap<>();
        List<RecursoDigital> recursos = new ArrayList<>();
        List<Prestamos> prestamos = new ArrayList<>();
        ServicioNotificaciones notificacion = new ServicioNotificacionesEmail();
        this.gestorUsuarios = new GestorUsuarios(usuarios, notificacion);
        this.gestorRecursos = new GestorRecursos(recursos, notificacion);
        this.gestorPrestamos = new GestorPrestamos(prestamos);

        // Pre-cargados
        Libro l1 = new Libro("El Hobbit", "Tolkien", "Fantasía", "Saga Tolkien");
        Libro l2 = new Libro("La comunidad del anillo", "Tolkien", "Fantasía", "El señor de los anillos");
        Libro l3 = new Libro("Las dos torres", "Tolkien", "Fantasía", "El señor de los anillos");
        Libro l4 = new Libro("El retorno del rey", "Tolkien", "Fantasía", "El señor de los anillos");

        Revista r1 = new Revista("National Geographic", "NG Media", "Ciencia", 2025);
        Audiolibro a1 = new Audiolibro("1984", "Orwell", "Español", 720);

        gestorRecursos.crearRecurso(l1);
        gestorRecursos.crearRecurso(l2);
        gestorRecursos.crearRecurso(l3);
        gestorRecursos.crearRecurso(l4);
        gestorRecursos.crearRecurso(r1);
        gestorRecursos.crearRecurso(a1);

        Usuario u1 = new Usuario("Ana",1,  "ana@mail.com",123456789);
        Usuario u2 = new Usuario("Pepe",2,  "pepe@mail.com",987654321);
        Usuario u3 = new Usuario("Juan",3,  "juan@mail.com",678912345);
        gestorUsuarios.addUsuario(u1);
        gestorUsuarios.addUsuario(u2);
        gestorUsuarios.addUsuario(u3);

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
                    try {
                        gestorUsuarios.registrarUsuario();
                    } catch (Exception e) {
                        System.out.println("Error al registrar el usuario\n");
                    }
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
                    "4. Buscar Recurso (Por Titulo) \n" +
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
                    try {
                        Libro libro = Libro.crearLibro();
                        gestorRecursos.crearRecurso(libro);
                    } catch (Exception e) {
                        System.out.println("Error al crear libro");
                    }
                    break;
                case 2:
                    try {
                        Revista revista = Revista.crearRevista();
                        gestorRecursos.crearRecurso(revista);
                    } catch (Exception e) {
                        System.out.println("Error al crear la revista \n");
                    }
                    break;
                case 3:
                    try {
                        Audiolibro audiolibro = Audiolibro.crearAudiolibro();
                        gestorRecursos.crearRecurso(audiolibro);
                    } catch (Exception e) {
                        System.out.println("Error al crear el audiolibro");
                    }
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
                    "4. Listar recursos que tiene el usuario \n" +
                    "5. Volver al Menu Principal \n" +
                    "Elija una opción:");
            int opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1:
                    System.out.println("Nombre el titulo del recurso: ");
                    String tituloPrestamo = sc.nextLine();
                    RecursoDigital recurso  = gestorRecursos.buscarPrestamo(tituloPrestamo);
                    System.out.println("Nombre al usuario: ");
                    String nombre = sc.nextLine();
                    Usuario usuario = gestorUsuarios.buscarPrestamo(nombre);
                    gestorPrestamos.prestarRecurso(usuario, recurso);
                    break;
                case 2:
                    System.out.println("Nombre el titulo del recurso: ");
                    String tituloRenovacion = sc.nextLine();
                    RecursoDigital recursoRenovacion = gestorRecursos.buscarPrestamo(tituloRenovacion);
                    gestorPrestamos.renovarPrestamo(recursoRenovacion);
                    break;
                case 3:
                    System.out.println("Nombre el titulo del recurso: ");
                    String tituloDevolver = sc.nextLine();
                    RecursoDigital recursoDevolver  = gestorRecursos.buscarPrestamo(tituloDevolver);
                    gestorPrestamos.devolverRecurso(recursoDevolver);
                    break;
                case 4:
                    System.out.println("Nombre del Usuario: ");
                    String nombreUsuario = sc.nextLine();
                    Usuario usuariolist = gestorUsuarios.buscarPrestamo(nombreUsuario);
                    gestorPrestamos.prestamosUsuario(usuariolist);
                    break;
                case 5:
                    continuar = false;
                    break;

            }
        }
    }
}