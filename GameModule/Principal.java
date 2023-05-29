package GameModule;

import Controller.Conexao;
import Controller.UsuarioController;
import View.TelaLogin;
import View.TelaMensagem;
import View.TelaRanking;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Principal {
    public static final int LARGURA_TELA = 800;
    public static final int ALTURA_TELA = 600;

    //CONSTRUTOR
    public Principal() {
        JFrame janela = new JFrame("Space Invaders");
        Game game = new Game();
        game.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
        janela.getContentPane().add(game);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLocation(100, 100);
        janela.setVisible(true);
        janela.pack();
    }

    public static void main(String[] args) throws SQLException {

       // Connection conexao = new Conexao().solicitaConnection();
       //TelaLogin login = new TelaLogin();
       //login.setVisible(true);
        new Principal();
       //TelaRanking ranking = new TelaRanking();
       //TelaMensagem mensagem = new TelaMensagem("Fodasse");
    }
}
