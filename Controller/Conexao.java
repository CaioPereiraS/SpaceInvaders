package Controller;

import java.sql.*;
public class Conexao {

    private static final String URL = "jdbc:postgresql://localhost:5432/spaceinvaders_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "2246";

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
