package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaMensagem extends JFrame {

    public TelaMensagem(String message) {
        // Configurações da janela
        setSize(200, 200);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setContentPane(new JLabel(new ImageIcon("background.jpg"))); // Substitua "background.jpg" pelo caminho da imagem de fundo

        // Cria o painel de conteúdo
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(false);

        // Cria o rótulo com a mensagem
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        messageLabel.setForeground(Color.WHITE);
        contentPane.add(messageLabel, BorderLayout.CENTER);

        // Cria o botão "OK"
        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela ao clicar no botão "OK"
            }
        });
        contentPane.add(okButton, BorderLayout.SOUTH);

        // Define o painel de conteúdo na janela
        setContentPane(contentPane);

        // Exibe a janela
        setVisible(true);

    }

}

