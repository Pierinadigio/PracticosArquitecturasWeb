package TP1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DerbyPersonaDAO implements PersonaDAO{
    private static final String URL = "jdbc:derby:myDatabase;create=true";
    private static final String DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";

    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createTable() {
        String createTableSQL = "CREATE TABLE persona1 (id INT PRIMARY KEY, nombre VARCHAR(255), apellido VARCHAR(255))";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Tabla persona creada en Derby.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPersona(Persona persona) {
        String insertSQL = "INSERT INTO persona1 VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, persona.getId());
            pstmt.setString(2, persona.getNombre());
            pstmt.setString(3, persona.getEmail());
            pstmt.executeUpdate();
            System.out.println("Persona añadida a Derby.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Persona> getAllPersonas() {
        String selectSQL = "SELECT * FROM persona1";
        List<Persona> personas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                personas.add(new Persona(id, nombre, apellido));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }
}
