package TP2.punto1.entidades;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Turno")
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

   @ManyToMany (fetch = FetchType.LAZY)
   @JoinTable(
           name = "Turno_Persona", // Nombre de la tabla intermedia
           joinColumns = @JoinColumn(name = "turnos_id"), // Columna que hace referencia a Turno
           inverseJoinColumns = @JoinColumn(name = "jugadores_id") // Columna que hace referencia a Persona
   )
   private Set<Persona> personasSet = new HashSet<>();;

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Set<Persona> getPersonasSet() {
        return personasSet;
    }

    public void setPersonasSet(Set<Persona> personasSet) {
        this.personasSet = personasSet;
    }

    // Método para agregar una persona
    public void addPersona(Persona persona) {
       if (persona != null) {
            personasSet.add(persona);
            // Si la relación es bidireccional, asegúrate de actualizar la entidad Persona
            // persona.getTurnosSet().add(this); // Asumiendo que hay una colección en Persona para los turnos
        }
    }
    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fecha=" + fecha +
                '}';
    }
}
