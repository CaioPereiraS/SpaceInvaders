import javax.imageio.ImageIO;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.awt.Component;
import static com.sun.java.accessibility.util.AWTEventMonitor.addKeyListener;



public class Spaceship extends Thread{
    public int posX;
    public int posY;
    public int velX;
    public int altura;
    public int largura;
    public BufferedImage ship;
    public boolean ativo = true;

    public Spaceship() {
        largura = 60;
        altura = 60;
        posX = 340;
        posY = 520;
        velX = 1;
        try {
            ship = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/nave/nave1.png")));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void anima_nave(Spaceship Nave) {

        for (int i = 1; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                    try {
                        ship = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/nave/nave" + finalI + ".png")));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
            }).start();
        }

    }

//    public void morte(Spaceship Nave){
//        for (int i = 1; i < 10; i++) {
//            int finalI = i;
//            new Thread(() -> {
//                try {
//                    ship = ImageIO.read(Objects.requireNonNull(getClass().getResource("images/morte" + finalI + ".png")));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }).start();
//        }
//    }

    public void testeColisao(Spaceship nave) {
        if (nave.posX + (nave.largura) > Principal.LARGURA_TELA || nave.posX < 0) { // testando colisão horizontal
            nave.posX -= nave.velX; // nega a velx impedidnado que ande
        }
    }


}