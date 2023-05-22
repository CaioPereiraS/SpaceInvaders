package GameModule;

import Controller.UsuarioController;

import javax.swing.*;
import java.awt.*;
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

//    public static void main(String[] args) throws SQLException {
//        UsuarioController Controlador = new UsuarioController();
//        Controlador.cadastrarUsuario();
//    }
}
