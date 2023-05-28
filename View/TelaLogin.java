package View;
import Controller.UsuarioController;
import Model.UsuarioModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Objects;

public class TelaLogin extends JFrame {
    private JPanel panel;
    private JTextField campoNick;
    private JPasswordField campoSenha;
    private JButton botaoEntrar;
    private JButton botaoCadastrar;
    UsuarioController $controlador = new UsuarioController();
    UsuarioModelo $usuario = new UsuarioModelo();
    TelaMensagem $mensagem;

    public TelaLogin () {
        // Configurações da janela
        setTitle("Tela de Login");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // Impede o redimensionamento da janela

        // Criação do painel principal
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carrega a imagem de fundo
                ImageIcon imageIcon = new ImageIcon("images/Views/login.png");
                Image image = imageIcon.getImage();
                // Desenha a imagem de fundo no painel
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Criação dos componentes
        JLabel labelNick = new JLabel("Nick:");
        labelNick.setForeground(new java.awt.Color(255, 255, 255));
        campoNick = new JTextField(15);
        campoNick.setMaximumSize(new Dimension(200, 25));
        campoNick.setBackground(new java.awt.Color(0, 0, 0));
        campoNick.setForeground(new java.awt.Color(255, 255, 255));

        JLabel labelSenha = new JLabel("Senha:");
        labelSenha.setForeground(new java.awt.Color(255, 255, 255));
        campoSenha = new JPasswordField(15);
        campoSenha.setMaximumSize(new Dimension(200, 25));
        campoSenha.setBackground(new java.awt.Color(0, 0, 0));
        campoSenha.setForeground(new java.awt.Color(255, 255, 255));

        botaoEntrar = new JButton("*  Entrar  *");
        botaoEntrar.setMaximumSize(new Dimension(220, 25));
        botaoEntrar.setMinimumSize(new Dimension(220, 25));

        botaoCadastrar = new JButton("Cadastrar");
        botaoCadastrar.setMaximumSize(new Dimension(220, 25));
        botaoCadastrar.setMinimumSize(new Dimension(220, 25));

        // Configuração do layout do painel
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = GridBagConstraints.RELATIVE;
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(5, 0, 5, 20); // Margem esquerda de 20 pixels

        // Adiciona os componentes ao painel
        panel.add(labelNick, constraints);
        panel.add(campoNick, constraints);
        panel.add(labelSenha, constraints);
        panel.add(campoSenha, constraints);
        panel.add(botaoEntrar, constraints);
        panel.add(botaoCadastrar, constraints);

        // Configura o botão de entrar
        botaoEntrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para autenticar o usuário

                $usuario.setNick(campoNick.getText());
                $usuario.setSenha(new String(campoSenha.getPassword()));
                // Chame o método adequado para autenticação do usuário
                try {
                    autenticarUsuario();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (NoSuchAlgorithmException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        // Configura o botão de cadastrar
        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastro cadastro = new TelaCadastro();
                cadastro.setVisible(true);
                setVisible(false);
            }
        });

        // Adiciona o painel à janela
        add(panel);
    }

    public void exibir() {
        setVisible(true);
    }

    private void autenticarUsuario() throws SQLException, NoSuchAlgorithmException {
        if (Objects.equals($usuario.getNick(), "")){
            $mensagem = new TelaMensagem("Verifique o Nick em branco");
            $mensagem.setVisible(true);
        }else if(Objects.equals($usuario.getSenha(), "")){
            $mensagem = new TelaMensagem("Verifique a Senha em branco");
            $mensagem.setVisible(true);
        }else{
           if($controlador.loggar($usuario)){
            TelaRanking ranking = new TelaRanking();
            ranking.setVisible(true);
           }else{
               $mensagem = new TelaMensagem("Usuario ou senha invalidos");
               $mensagem.setVisible(true);
           }
        }

    }

}
