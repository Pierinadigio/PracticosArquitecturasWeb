package TP1;

public class Persona {
    private int idCliente;
    private String nombre;
    private String email;

    // Constructor
    public Persona(int id, String nombre, String apellido) {
        this.idCliente = id;
        this.nombre = nombre;
        this.email = apellido;
    }

    // Getters y Setters
    public int getId() {
        return idCliente;
    }

    public void setId(int id) {
        this.idCliente = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Persona{id=" + idCliente + ", nombre='" + nombre + "', email='" + email + "'}";
    }
}
