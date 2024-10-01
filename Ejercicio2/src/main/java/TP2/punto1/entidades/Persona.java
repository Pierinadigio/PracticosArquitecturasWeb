package TP2.punto1.entidades;

import javax.persistence.*;
import java.util.Set;
import javax.persistence.OneToMany;


@Entity
@Table(name = "Persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "edad")
    private int anios;

    @Column(name = "nombre", length = 255, nullable = false)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "domicilio_id", nullable = false)
    private Direccion direccion;

    public Persona() {
    }

    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.anios = edad;
    }


    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnios() {
        return anios;
    }

    public void setAnios(int edad) {
        this.anios = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Direccion getDireccion() {
        return direccion;
    }


    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }



    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", anios=" + anios +
                ", nombre='" + nombre + '\'' +
                ", direccion=" + direccion +
                '}';
    }
}
