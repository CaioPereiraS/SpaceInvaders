import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Game_over extends JFrame {
    
    public Game_over() {
        setTitle("Minha Nova Tela");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JLabel label = new JLabel(new ImageIcon("background.jpg"));
        getContentPane().add(label, BorderLayout.CENTER);
    }


}