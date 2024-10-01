package TP1;
import java.util.List;

public class PersonaService {
    private PersonaDAO personaDAO;

    public PersonaService(PersonaDAO personaDAO) {
        this.personaDAO = personaDAO;
    }

    public List<Persona> getAllPersonas() {

        return personaDAO.getAllPersonas();
    }

    public void createTable() {
        personaDAO.createTable();
    }

    public void addPersona(Persona persona) {
        personaDAO.addPersona(persona);
    }
}
