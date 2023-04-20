import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class MinhaAnimacao extends JPanel implements Runnable {

    private BufferedImage[] imagens = new BufferedImage[4];
    private int indice = 0;

    public MinhaAnimacao() {
        try {
            imagens[0] = ImageIO.read(new File("images/morte1.png"));
            imagens[1] = ImageIO.read(new File("images/morte2.png"));
            imagens[2] = ImageIO.read(new File("images/morte3.png"));
            imagens[3] = ImageIO.read(new File("images/morte4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(imagens[indice], 0, 0, this);
    }

    public void run() {
        while (true) {
            indice = (indice + 1) % imagens.length;
            repaint();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Minha animação");
        MinhaAnimacao animacao = new MinhaAnimacao();
        frame.add(animacao);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Thread thread = new Thread(animacao);
        thread.start();
    }
}
