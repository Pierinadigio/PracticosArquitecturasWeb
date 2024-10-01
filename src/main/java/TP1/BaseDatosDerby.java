package TP1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseDatosDerby {
    public static void main(String[] args) {

        String driver = "org.apache.derby.jdbc.EmbeddedDriver"; // Para el modo embebido
        // URL de conexión para el modo embebido
        String url = "jdbc:derby:myDatabase;create=true";

        try {
            // Cargar el driver
            Class.forName(driver);

            // Establecer conexión con la base de datos
            Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement();

            // Ejecutar una consulta SQL

            stmt.execute("CREATE TABLE test (id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.execute("INSERT INTO test VALUES (1, 'example')");

            ResultSet res = stmt.executeQuery("SELECT * FROM test");
            while (res.next()) {
                System.out.println("ID: " + res.getInt("id") + ", Name: " + res.getString("name"));
            }

            // Cerrar la conexión
            res.close();
            stmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.err.println("Driver no encontrado: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("SQL error: " + e.getMessage());
        }
    }
}
