package TP1;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Cambia el DAO según el tipo de base de datos que estés utilizando
     //   PersonaDAO personaDAO = new MySQLPersonaDA(); // O MySQLPersonaDAO
      PersonaDAO personaDAO1 = new DerbyPersonaDAO(); // rsoO derby
  //      PersonaService personaService = new PersonaService(personaDAO);
       PersonaService personaService = new PersonaService(personaDAO1);


        //Crear la tabla
       personaService.createTable();

        // Añadir personas
        personaService.addPersona(new Persona(11, "Pierina", "Di Giorgio"));
        personaService.addPersona(new Persona(22, "Artur", "García"));

        // Obtener y mostrar todas las personas
        List<Persona> personas = personaService.getAllPersonas();
        for (Persona persona : personas) {
            System.out.println(persona);
        }
    }
}
