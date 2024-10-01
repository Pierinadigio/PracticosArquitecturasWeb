package TP1;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQLPersonaDA implements PersonaDAO{
    private static final String URL = "jdbc:mysql://localhost:3306/Mysql";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    private static final String CSV_FILE_PATH = "clientes.csv"; // Ajusta la ruta si es necesario
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

   @Override
    public void createTable() {
        String createTableSQL = "CREATE TABLE personaMil (idCliente INT PRIMARY KEY, nombre VARCHAR(255), email VARCHAR(255))";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Tabla ppersonaMil creada en MySQL.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPersona(Persona persona) {
        String insertSQL = "INSERT INTO personaMil VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, persona.getId());
            pstmt.setString(2, persona.getNombre());
            pstmt.setString(3, persona.getEmail());
            pstmt.executeUpdate();
            System.out.println("Persona añadida a PersonaMil.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Persona> getAllPersonas() {
        String selectSQL = "SELECT * FROM personaMil" + "";
        List<Persona> personas = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                int id = rs.getInt("idCliente");
                String nombre = rs.getString("nombre");
                String email = rs.getString("email");
                personas.add(new Persona(id, nombre, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }
}
