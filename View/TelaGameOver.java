package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaGameOver extends JFrame {
    private JPanel panel;
    private JTextField mensagem;
    private JButton botaoOk;

    public TelaGameOver(int pontuacao) {
        setTitle("Game Over");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);

        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imageIcon = new ImageIcon("images/Views/mensagem.png");
                Image image = imageIcon.getImage();
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        };
        panel.setLayout(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        String mensagem= "";
        if(pontuacao==48){
            mensagem = "Você obteve a pontuação maxima \n Sua pontuação: " + pontuacao;
        }else{
            mensagem = "Fim de jogo \n Sua pontuação: " + pontuacao;
        }

        JLabel messageLabel = new JLabel(mensagem);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);

        botaoOk = new JButton("OK");
        botaoOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.insets = new Insets(0, 0, 20, 0); // Margem inferior de 20 pixels

        panel.add(messageLabel, constraints);

        constraints.gridy = 1;
        constraints.insets = new Insets(0, 0, 0, 0); // Sem margem inferior
        panel.add(botaoOk, constraints);

        add(panel);
    }
}
