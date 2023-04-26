import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.*;
import java.util.Objects;

public class Animacao extends JPanel {

    public void animarMorteNave(NaveEspacial Nave){

        for (int i = 1; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Nave.ship = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/morte" + finalI + ".png")));
                    repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }

    }


}
