package Controller;

import Model.Usuario;
import java.sql.*;

public class UsuarioController {

    public UsuarioController(){

    }

    public void cadastrarUsuario() throws SQLException {

        String Sentenca = "INSERT INTO TB_USUARIO(US_nick, US_senha) VALUES ('Caio','123') ";
        Connection conexao = new Conexao().getConnection();
        PreparedStatement statement = conexao.prepareStatement(Sentenca);
        statement.execute();
        conexao.close();
    }
}
