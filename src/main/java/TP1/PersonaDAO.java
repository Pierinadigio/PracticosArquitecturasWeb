package TP1;
import java.util.List;
public interface PersonaDAO {
    void createTable();
    void addPersona(Persona persona);
    List<Persona> getAllPersonas();
}
