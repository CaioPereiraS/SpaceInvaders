package Controller;

import Model.UsuarioModelo;

import java.sql.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class UsuarioController {


    public UsuarioController() {

    }

    public boolean cadastrarUsuario(UsuarioModelo $modelo) throws SQLException, NoSuchAlgorithmException {

        $modelo.setSenha(criptorgrafarSenha($modelo.getSenha()));

        try {
            String Sentenca = "insert into public.tb_usuario (us_nick, us_senha, us_melhorpontuacao) values ('" + $modelo.getNick() + "','" + $modelo.getSenha() + "',0)";
            Connection conexao = new Conexao().solicitaConnection();
            PreparedStatement statement = conexao.prepareStatement(Sentenca);
            statement.execute();
            conexao.close();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean loggar(UsuarioModelo $modelo) throws SQLException, NoSuchAlgorithmException {

        // EXECUTA A QUERY

        try {
            String Sentenca = "SELECT us_nick, us_senha FROM tb_usuario WHERE us_nick = " + "'" + $modelo.getNick() + "'";
            Connection conexao = new Conexao().solicitaConnection();
            PreparedStatement statement = conexao.prepareStatement(Sentenca);
            ResultSet resultSet = statement.executeQuery();


            if (resultSet.next()) {
                // Colocando os valores da senteça nas variaveis
                String autenticador = resultSet.getString("us_senha");

                conexao.close();
                //criptografando a senha que veio da tela
                $modelo.setSenha(criptorgrafarSenha($modelo.getSenha()));
                //coparando as senha
                if (Objects.equals($modelo.getSenha(), autenticador)) {
                    return true;
                }
                //else do resultado da consulta
            }

        } catch (SQLException e) {
        }
        return false;
    }

    public UsuarioModelo[] obterLista() throws SQLException {

        UsuarioModelo[] usuarios = null;
        try {
            String Sentenca = "SELECT us_nick, us_melhorpontuacao FROM tb_usuario ORDER BY us_melhorpontuacao DESC LIMIT 10";
            Connection conexao = new Conexao().solicitaConnection();
            PreparedStatement statement = conexao.prepareStatement(Sentenca);
            ResultSet resultSet = statement.executeQuery();


            // Criar o array de usuários com o tamanho adequado
            usuarios = new UsuarioModelo[10];
            // Percorrer o resultado e adicionar os usuários ao array
            int i = 0;
            while (resultSet.next()) {
                String nick = resultSet.getString("us_nick");
                int maximaPontuacao = resultSet.getInt("us_melhorpontuacao");

                UsuarioModelo usuario = new UsuarioModelo(nick, maximaPontuacao);
                usuarios[i] = usuario;
                i++;
            }
            // Fechar as conexões
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }

    public boolean validarNick(String $nick) throws SQLException {
        try {
            String Sentenca = "SELECT us_nick FROM tb_usuario WHERE us_nick = " + "'" + $nick + "'";
            Connection conexao = new Conexao().solicitaConnection();
            PreparedStatement statement = conexao.prepareStatement(Sentenca);
            ResultSet resultSet = statement.executeQuery();
            int rowCount = 0;
            while (resultSet.next()) {
                rowCount++;
            }
            if (rowCount < 1) {
                return true;
            }

        } catch (SQLException e) {

        }
        return false;
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
