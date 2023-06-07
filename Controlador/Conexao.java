package Controlador;

import java.sql.*;
public class Conexao {

    private static final String URL = "jdbc:postgresql://containers-us-west-137.railway.app:7621/railway";
    private static final String USER = "postgres";
    private static final String PASSWORD = "hvIVeUVRbQ2eJhhOQ0pR";

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
