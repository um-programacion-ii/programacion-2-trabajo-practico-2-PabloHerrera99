package src.modelos;

public class Usuario {
    private String nombre;
    private int id;
    private String email;
    private int telefono;

    public Usuario(String nombre, int id, String email, int telefono) {
        setNombre(nombre);
        setId(id);
        setEmail(email);
        setTelefono(telefono);
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacio");
            }
        this.nombre = nombre;
        }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (id < 0) {
            throw new IllegalArgumentException("El id es invalido");
        }
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("El email no puede estar vacio");
        }
        this.email = email;
    }

    public int getTelefono() {
        return telefono;
    }
    public void setTelefono(int telefono) {
        if (telefono < 0) {
            throw new IllegalArgumentException("El telefono no puede estar vacio");
        }
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return "Id: " + id +
                "\nUsuario: "+ nombre +
                "\nEmail: " + email +
                "\nTelefono: " + telefono;
    }
}