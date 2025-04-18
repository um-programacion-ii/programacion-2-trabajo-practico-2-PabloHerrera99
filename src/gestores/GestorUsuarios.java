package src.gestores;

import src.excepciones.UsuarioNoEncontradoException;
import src.interfaces.ServicioNotificaciones;
import src.modelos.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Map;

public class GestorUsuarios {
    private Map<Integer, Usuario> usuarios;
    private ServicioNotificaciones notificacion;

    public GestorUsuarios(Map<Integer, Usuario> usuarios, ServicioNotificaciones notificacion) {
        this.usuarios = usuarios;
        this.notificacion = notificacion;
    }

    public void addUsuario(Usuario usuario){
        usuarios.put(usuario.getId(), usuario);
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
            for (Usuario usuario : usuarios.values()) {
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
            usuarios.put(usuario.getId(), usuario);

            System.out.println("Usuario registrado");
            notificacion.enviarNotificacion("Usuario registrado, datos de usuario:\n"+
                    usuario);
        }
        catch (IllegalArgumentException error){
            System.out.println(error.getMessage());
            System.out.println("Datos invalidos, intentelo nuevamente");
        }
    }

    public void eliminarUsuario() throws UsuarioNoEncontradoException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del usuario que quiere eliminar:");
        int id = sc.nextInt();
        Usuario usuario = usuarios.get(id);
        if (usuario != null) {
            usuarios.remove(id);
            notificacion.enviarNotificacion("Usuario: " + usuario.getNombre() + " eliminado");
        }else {
            throw new UsuarioNoEncontradoException("El Usuario con el id " + id + " no existe");
        }
    }

    public void buscarUsuario()  throws UsuarioNoEncontradoException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id del usuario buscado:");
        int id = sc.nextInt();
        if (!usuarios.containsKey(id)) {
            throw new UsuarioNoEncontradoException("El Usuario con el id " + id + " no existe");
        }
        System.out.println("Usuario encontrado:\n" +
                usuarios.get(id) +
                "\n--------------------------------");
    }

    public void listarUsuarios() {
        for (Usuario usuario : usuarios.values()) {
            System.out.println(usuario +
                    "\n-----------------\n");
        }
    }
}