package Controller;

import java.sql.*;
public class Conexao {

    private static final String URL = "jdbc:postgresql://containers-us-west-10.railway.app:5441/railway";
    private static final String USER = "postgres";
    private static final String PASSWORD = "HX8Zoi9Ur9XCaWxE6Acg";

    public Connection solicitaConnection() {

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Erro de Drive " + e);
            System.exit(1);
        }

        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Erro de conexao " + e);
            System.exit(2);
            return null;
        }

    }
}
