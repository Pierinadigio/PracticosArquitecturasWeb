package TP1;

import java.sql.*;

public class BaseDatosMysql {

    public static void main(String[] args) {
        // Nombre del driver MySQL
        String driver = "com.mysql.cj.jdbc.Driver";

        try {
            // Cargar la clase del driver
            Class.forName(driver);
            System.out.println("Driver cargado exitosamente.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver no encontrado", e);
        }

        // URL de conexión a la base de datos MySQL
        String url = "jdbc:mysql://localhost:3306/Mysql";
        String user = "root";
        String password = "";
        // Asegúrate de ajustar el nombre de la base de datos y otros parámetros según tu configuración

        try {            // Realiza operaciones con la conexión
            Connection conexion = DriverManager.getConnection(url, user, password);
            // Crear la tabla
            createTable(conexion, "nuevatabla22");

            // Insertar valores
            addValues(conexion, "nuevatabla22", 1, "prueba1");

            // Consultar y mostrar los valores
            displayValues(conexion, "nuevatabla22");
            conexion.close();
            System.out.println("Conexión establecida y cerrada exitosamente. I love you");

        } catch (SQLException exception) {
            exception.printStackTrace();
            System.out.println("No se conecto.");
        }
      //  finally {
            // Cerrar la conexión
    //        try {
    //            if (conexion != null && !conexion.isClosed()) {

       //         }
    //        } catch (SQLException e) {
     //           e.printStackTrace();
      //      }
     //   }
    }
    private static void createTable(Connection conexion, String tableName) throws SQLException {
        String createTableSQL = "CREATE TABLE " + tableName + " (id INT PRIMARY KEY, name VARCHAR(255))";
        try (Statement stmt = conexion.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Tabla " + tableName + " creada exitosamente.");
        }
    }

    private static void addValues(Connection conexion, String tableName, int id, String name) throws SQLException {
        String insertSQL = "INSERT INTO " + tableName + " VALUES (?, ?)";
        try (Statement stmt = conexion.createStatement()) {
            // Usar PreparedStatement para evitar inyecciones SQL y manejar parámetros
            try (java.sql.PreparedStatement pstmt = conexion.prepareStatement(insertSQL)) {
                pstmt.setInt(1, id);
                pstmt.setString(2, name);
                pstmt.executeUpdate();
                System.out.println("Valor agregado exitosamente a la tabla " + tableName + ".");
            }
        }
    }

    private static void displayValues(Connection conexion, String tableName) throws SQLException {
        String selectSQL = "SELECT * FROM " + tableName;
        try (Statement stmt = conexion.createStatement(); ResultSet res = stmt.executeQuery(selectSQL)) {
            while (res.next()) {
                System.out.println("ID: " + res.getInt("id") + ", Name: " + res.getString("name"));
            }
        }
    }
}
