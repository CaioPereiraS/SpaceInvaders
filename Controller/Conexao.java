package Controller;

import java.sql.*;
public class Conexao {

    public Connection getConnection() throws SQLException {
        Connection conexao = DriverManager.getConnection("postgresql://postgres:WIBforr7Dwe0jXTswZ1h@containers-us-west-77.railway.app:5542/railway");
        return conexao;
    }
}
