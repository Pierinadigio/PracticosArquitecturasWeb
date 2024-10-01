package TP2.punto1.DTO;

import TP2.punto1.entidades.Direccion;

public class PersonaTurnoDTO {
    private int id;
    private String nombre;
    private int edad;
    private boolean esSocio;


    // Puedes incluir otros campos si es necesario
    // Constructor1
    public PersonaTurnoDTO(int id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.esSocio = esSocio;
    }
    // Constructor2
    public PersonaTurnoDTO(int id, String nombre, int edad,  boolean esSocio) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
        this.esSocio = esSocio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }
    public boolean isEsSocio() {
        return esSocio;
    }

    public void setEsSocio(boolean esSocio) {
        this.esSocio = esSocio;
    }
    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", nombre=" + nombre +
                ", anios='" + edad + '\'' +
                "esSocio=" + esSocio +
                '}';
    }
}
