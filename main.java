import modelos.Usuario;
import gestores.GestorUsuarios;

import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) {

        List<Usuario> listaUsuarios = new ArrayList<>();
        GestorUsuarios gestorusuarios = new GestorUsuarios(listaUsuarios);


        gestorusuarios.registrarUsuario();
        gestorusuarios.registrarUsuario();
       // gestorusuarios.registrarUsuario();

        gestorusuarios.buscarUsuario();
        gestorusuarios.eliminarUsuario();
        gestorusuarios.buscarUsuario();

    }
}
