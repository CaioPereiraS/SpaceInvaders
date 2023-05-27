package Controller;

import Model.UsuarioModelo;
import java.sql.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class UsuarioController {

    public UsuarioController(){

    }

    public void cadastrarUsuario() throws SQLException {
        String Sentenca = "";
        Connection conexao = new Conexao().solicitaConnection();
        PreparedStatement statement = conexao.prepareStatement(Sentenca);
        statement.execute();
        conexao.close();
    }

    public void loggar(UsuarioModelo $modelo) throws SQLException, NoSuchAlgorithmException {

        // EXECUTA A QUERY
        String Sentenca = "SELECT us_nick, us_senha FROM tb_usuario WHERE us_nick = "+"'"+ $modelo.getNick()+"'" ;
        Connection conexao = new Conexao().solicitaConnection();
        PreparedStatement statement = conexao.prepareStatement(Sentenca);
        statement.setString(1, $modelo.getNick());
        ResultSet resultSet = statement.executeQuery();

        UsuarioModelo autenticador = new UsuarioModelo();

        if (resultSet.next()) {
            // Colocando os valores da senteça nas variaveis
            autenticador.setSenha(resultSet.getString("us_senha"));
            autenticador.setNick(resultSet.getString("us_nick"));

            //criptografando a senha que veio da tela
            $modelo.setSenha(criptorgrafarSenha($modelo.getSenha()));

            //coparando as senha
            if ($modelo.getSenha() == autenticador.getSenha()){
                // entrar

            }else{
                //
            }
        //else do resultado da consulta
        }else {

        }
        conexao.close();




    }

    public void obterLista() throws SQLException {
        String Sentenca = "";
        Connection conexao = new Conexao().solicitaConnection();
        PreparedStatement statement = conexao.prepareStatement(Sentenca);
        statement.execute();
        conexao.close();
    }

    public static String criptorgrafarSenha(String senha) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] encodedHash = digest.digest(senha.getBytes(StandardCharsets.UTF_8));

        // Converter o hash em formato hexadecimal
        StringBuilder hexString = new StringBuilder();
        for (byte b : encodedHash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}
