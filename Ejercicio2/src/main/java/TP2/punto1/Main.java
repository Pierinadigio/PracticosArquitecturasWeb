package TP2.punto1;
import TP2.factory.DAOfactory;
import TP2.factory.MySQLDAOFactory;
import TP2.punto1.DAO.*;
import TP2.punto1.DTO.PersonaTurnoDTO;
import TP2.punto1.entidades.*;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static <Set> void main(String[] args) {
        DAOfactory daOfactory = new MySQLDAOFactory();

        PersonaDAO personaDAO = daOfactory.getPersonaDAO();
        SocioDAO socioDAO = daOfactory.getSocioDAO();
        TurnoDAO turnoDAO = daOfactory.getTurnoDAO();
        DireccionDAO direccionDAO = daOfactory.getDireccionDAO();


        // Crear y guardar una nueva dirección
        Direccion direccion = new Direccion();
        direccion.setCalle("Casacubrta");
        direccion.setCiudad("Tandil ");
        direccionDAO.save(direccion);

        // Crear y guardar una nueva persona
        Persona persona = new Persona();
        persona.setNombre("Pierina");
        persona.setAnios(30);
        persona.setDireccion(direccion);
        personaDAO.save(persona);
       // Crear y guardar una nueva persona
        Persona persona2 = new Persona();
        persona2.setNombre("Laura");
        persona2.setAnios(30);
        persona2.setDireccion(direccion);
        personaDAO.save(persona2);


        Persona persona3 = new Persona();
        persona3.setNombre("Rosario");
        persona3.setAnios(20);
        persona3.setDireccion(direccion);
        personaDAO.save(persona3);


        // Crear y guardar un nuevo socio
        Socio socio = new Socio();
        socio.setTipo("Premium");
        socio.setPersona(persona2);
        socioDAO.save(socio);

        // Crear y guardar un nuevo turno
        Turno turno = new Turno();
        Date fecha = new Date();
        turno.setFecha(fecha);
        turnoDAO.save(turno);

        // Crear una fecha específica usando Calendar
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.OCTOBER, 18, 0, 0, 0); // Mes en Calendar es 0-based (OCTOBER es el 9)
        calendar.set(Calendar.MILLISECOND, 0); // Opcional: establece los milisegundos a cero

        Date fecha2 = calendar.getTime(); // Obtiene un objeto Date con la fecha especificada

        // Crear y guardar el turno
        Turno turno2 = new Turno();
        turno2.setFecha(fecha2);
        turnoDAO.save(turno2);
        // Asignar personas al turno
        turno.addPersona(persona);
        turno.addPersona(persona2);
        turnoDAO.save(turno);

        System.out.println("Personas asignadas al turno:");
        turnoDAO.getPersonasByTurnoId(turno.getId());

        Turno turno3 = new Turno();
        Date fecha3 = new Date();
        turno3.setFecha(fecha3);
        turnoDAO.save(turno3);

        // Leer y mostrar todas las personas
        System.out.println("Personas en la base de datos:");
        personaDAO.findAll();

        System.out.println("Personas que es socio");
        turnoDAO.findPersonasConSocioByTurnoId(1);
/*
        // Actualizar una persona
        Persona personaToUpdate = personaDAO.find(1);
        if (personaToUpdate != null) {
            personaToUpdate.setNombre("Juan Pérez Actualizado");
            personaDAO.update(personaToUpdate);
        }

        // Borrar una persona
        personaDAO.delete(1);*/
/*
        // Verificar eliminación
        Persona deletedPersona = personaDAO.find(1);
        System.out.println("Persona eliminada: " + (deletedPersona == null ? "Sí" : "No"));*/
    }
}
