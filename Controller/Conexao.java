package Controller;

import java.sql.*;
public class Conexao {

    private static final String URL = "jdbc:postgresql://postgres:WIBforr7Dwe0jXTswZ1h@containers-us-west-77.railway.app:5542/railway";
    private static final String USER = "postgres";
    private static final String PASSWORD = "WIBforr7Dwe0jXTswZ1h";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
