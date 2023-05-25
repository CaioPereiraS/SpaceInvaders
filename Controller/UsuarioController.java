package Controller;

import Model.Usuario;
import java.sql.*;

public class UsuarioController {

    public UsuarioController(){

    }

    public void cadastrarUsuario() throws SQLException {

        String Sentenca = "INSERT INTO tb_usuario (us_nick, us_senha) VALUES ('Caio','123') ";
        Connection conexao = new Conexao().solicitaConnection();
        PreparedStatement statement = conexao.prepareStatement(Sentenca);
        statement.execute();
        conexao.close();
    }
}
