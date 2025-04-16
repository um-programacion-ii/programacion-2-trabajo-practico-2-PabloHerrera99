package gestores;

import modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestorUsuarios {
    private List<Usuario> usuarios;

    public GestorUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void registrarUsuario(){
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Nuevo Usuario:");
            System.out.println("Ingrese el nombre del nuevo usuario:");
            String nombre = sc.nextLine();

            // Encontrar Id sin usar (revisar)
            int id = 1;
            List<Integer> idUsados = new ArrayList<>();
            for (Usuario usuario : usuarios) {
                idUsados.add(usuario.getId());
            }
            while (idUsados.contains(id)) {
                id++;
            }

            System.out.println("Ingrese el email del nuevo usuario:");
            String email = sc.nextLine();
            System.out.println("Ingrese el telefono del nuevo usuario:");
            int telefono = sc.nextInt();

            Usuario usuario = new Usuario(nombre, id, email, telefono);
            usuarios.add(usuario);

        }
        catch (IllegalArgumentException error){
            System.out.println(error.getMessage());
            System.out.println("Datos invalidos, intentelo nuevamente");
        }
        System.out.println("Usuario registrado");
    }

    public void eliminarUsuario(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del usuario que quiere eliminar:");
        int id = sc.nextInt();
        boolean eliminado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                usuarios.remove(usuario);
                System.out.println("Usuario eliminado");
                eliminado = true;
                break;
            }
        }
        if (!eliminado) {
            System.out.println("El usuario no existe");
        }
    }

    public void buscarUsuario() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del usuario buscado:");
        int id = sc.nextInt();
        boolean encontrado = false;
        for (Usuario usuario : usuarios) {
            if (usuario.getId() == id) {
                System.out.println("Usuario encontrado: \n" + usuario);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("El usuario con el ID: " + id + " no existe en la base de datos");
        }
    }

    public void listarUsuarios() {
        for (Usuario usuario : usuarios) {
            System.out.println(usuario);
            System.out.println("\n------------------------\n");
        }
    }
}